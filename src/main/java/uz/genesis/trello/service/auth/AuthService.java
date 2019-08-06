package uz.genesis.trello.service.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.GrantType;
import freemarker.template.TemplateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.criterias.settings.OrganizationSettingsCriteria;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.*;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.enums.Types;
import uz.genesis.trello.exception.GenericRuntimeException;
import uz.genesis.trello.property.ServerProperties;
import uz.genesis.trello.repository.auth.IUserOtpRepository;
import uz.genesis.trello.repository.auth.UserRepository;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.repository.settings.ITypeRepository;
import uz.genesis.trello.service.message.IOtpHelperService;
import uz.genesis.trello.service.settings.IOrganizationSettingsService;
import uz.genesis.trello.service.settings.ITypeService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.auth.AuthUtils;
import uz.genesis.trello.utils.pkcs.PKCSChecker;
import uz.genesis.trello.utils.validators.auth.UserServiceValidator;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Service
public class AuthService implements IAuthService {


    public static String OAUTH_AUTH_URL = "/oauth/token";
    private static String SERVER_URL;
    /**
     * Common logger for use in subclasses.
     */
    private final Log logger = LogFactory.getLog(getClass());
    private final IOrganizationSettingsService organizationSettingsService;
    private final TokenStore tokenStore;
    private final ITypeService typeService;
    private final PasswordEncoder userPasswordEncoder;
    private final PKCSChecker pkcsChecker;
    private final BaseUtils utils;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final IAuthTryService authTryService;
    private final IUserLastLoginService userLastLoginService;
    private final AuthUtils authUtils;
    private final IErrorRepository errorRepository;

    private final ITypeRepository typeRepository;
    private final IUserOtpRepository userOtpRepository;
    private final IOtpHelperService otpHelperService;
    private final UserServiceValidator userServiceValidator;
    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;
    @Resource(name = "tokenServices")
    AuthorizationServerTokenServices authorizationServerTokenServices;
    @Value("${oauth2.clientId}")
    private String clientId;
    @Value("${oauth2.clientSecret}")
    private String clientSecret;


    @Autowired
    public AuthService(BaseUtils utils, ServerProperties serverProperties, IOrganizationSettingsService organizationSettingsService, TokenStore tokenStore, ITypeService typeService, PasswordEncoder userPasswordEncoder, PKCSChecker pkcsChecker, UserRepository userRepository, IAuthTryService authTryService, IUserLastLoginService userLastLoginService, ITypeRepository typeRepository, IUserOtpRepository userOtpRepository, IOtpHelperService otpHelperService, UserDetailsService userDetailsService, AuthUtils authUtils, IErrorRepository errorRepository, UserServiceValidator userServiceValidator) {
        this.utils = utils;

        SERVER_URL = "http://" + serverProperties.getIp() + ":" + serverProperties.getPort() + "";
        this.organizationSettingsService = organizationSettingsService;
        this.tokenStore = tokenStore;
        this.typeService = typeService;
        this.userPasswordEncoder = userPasswordEncoder;
        this.pkcsChecker = pkcsChecker;
        this.userRepository = userRepository;
        this.authTryService = authTryService;
        this.userLastLoginService = userLastLoginService;
        this.typeRepository = typeRepository;
        this.userOtpRepository = userOtpRepository;
        this.otpHelperService = otpHelperService;
        this.userDetailsService = userDetailsService;
        this.authUtils = authUtils;
        this.errorRepository = errorRepository;
        this.userServiceValidator = userServiceValidator;
        //        SERVER_URL = "https://" + serverProperties.getUrl();
        OAUTH_AUTH_URL = SERVER_URL + OAUTH_AUTH_URL;
    }

    @Override
    public ResponseEntity<DataDto<SessionDto>> login(AuthUserDto user, HttpServletRequest request) {

        userServiceValidator.validateOnAuth(user);
        try {
            clearExistingTokens(user);
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(OAUTH_AUTH_URL);
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("grant_type", GrantType.PASSWORD.getValue()));
            nameValuePairs.add(new BasicNameValuePair("username", user.getUserName()));
            nameValuePairs.add(new BasicNameValuePair("password", user.getPassword()));


            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httppost.addHeader(HttpHeaders.AUTHORIZATION, getAuthorization());
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpResponse response = httpclient.execute(httppost);
            return getAuthDtoDataDto(user, response, true, request);

        } catch (Exception ex) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage())), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> logout(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            tokenServices.revokeToken(tokenId);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DataDto<>(false), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<SessionDto>> refreshToken(AuthUserDto user, HttpServletRequest request) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(OAUTH_AUTH_URL);

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("grant_type", GrantType.REFRESH_TOKEN.getValue()));
            nameValuePairs.add(new BasicNameValuePair("refresh_token", user.getRefreshToken()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.addHeader(HttpHeaders.AUTHORIZATION, getAuthorization());
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpResponse response = httpclient.execute(httppost);

            return getAuthDtoDataDto(user, response, false, request);

        } catch (IOException ex) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage())), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> signInOtp(String username) {
        try {
            otpHelperService.sendOtp(username);
        } catch (TemplateException | IOException | MessagingException e) {
            throw new GenericRuntimeException(e.getMessage(), e);
        }

        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<SessionDto>> otpConfirm(UserOtpConfirmDto dto) {
        userServiceValidator.validateOnOtpConfirm(dto);
        boolean isConfirmed = otpHelperService.confirmOtp(dto.getUsername(), dto.getOtpCode());
        if (isConfirmed) {
            return createSessionForOtpClient(dto.getUsername());
        } else {
            throw new GenericRuntimeException(errorRepository.getErrorMessage(ErrorCodes.OTP_NOT_CONFIRMED, utils.toErrorParams(AuthService.class)));
        }
    }

    private ResponseEntity<DataDto<SessionDto>> getAuthDtoDataDto(AuthUserDto user, HttpResponse response,
                                                                  boolean authentication, HttpServletRequest request) throws IOException {
        JsonNode json_auth = new ObjectMapper().readTree(EntityUtils.toString(response.getEntity()));

        if (!json_auth.has("error")) {
            String token = json_auth.get("access_token").asText();
            if (authentication) saveUserLastLogin(user, request, token);

            SessionDto authDto = SessionDto.builder()
                    .expiresIn(json_auth.get("expires_in").asLong())
                    .sessionToken(token)
                    .refreshToken(json_auth.get("refresh_token").asText())
                    .tokenType(json_auth.get("token_type").asText())
                    .scope(json_auth.get("scope").asText()).build();
            return new ResponseEntity<>(new DataDto<>(authDto), HttpStatus.OK);
        } else {
            String error_description = json_auth.has("error_description") ? json_auth.get("error_description").asText() : null;
            if (error_description == null || error_description.isEmpty()) {
                error_description = "Username or password is wrong custom message";
            } else if (error_description.contains("NoResultException")) {
                error_description = "Username or password is wrong or this user is not active in this System";
            }
            if (authentication) saveAuthTry(request, user, response);
            else {
                throw new RuntimeException("Refresh token expired or Invalid refresh token");
            }
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.UNAUTHORIZED.value(),
                    error_description)), HttpStatus.UNAUTHORIZED);
        }

    }

    private String getAuthorization() {
        return "Basic " + utils.encodeToBase64(clientId + ":" + clientSecret);
    }

    private void saveUserLastLogin(AuthUserDto user, HttpServletRequest request, String token) {
        UserLastLoginCreateDto lastLogin = UserLastLoginCreateDto.builder().ipAddress(utils.getClientIpAddress(request)).userId(userRepository.find(UserCriteria.childBuilder().userName(user.getUserName()).forAuthenticate(true).build()).getId()).sessionToken(token).build();
        userLastLoginService.create(lastLogin);
    }

    private void saveAuthTry(HttpServletRequest request, AuthUserDto userDto, HttpResponse response) {
        int status = response.getStatusLine().getStatusCode();
        Types type;
        switch (status) {
            case 400:
                type = Types.AUTH_TRY_BAD_CREDENTIAL;
                break;
            case 401:
                type = Types.AUTH_TRY_UNAUTHORIZED;
                break;
            case 403:
                type = Types.AUTH_TRY_FORBIDDEN;
                break;
            default:
                type = Types.AUTH_TRY_UNKNOWN;
                break;
        }

        Long typeId = typeService.getIdByValue(type);
        AuthTryCreateDto createDto = AuthTryCreateDto.builder().
                ipAddress(utils.getClientIpAddress(request)).
                userName(userDto.getUserName()).
                resultType(new GenericDto(typeId)).build();

        authTryService.create(createDto);
    }

    private boolean checkUserAuthentication(AuthUserDto userDto) {
        User user = userRepository.find(UserCriteria.childBuilder().userName(userDto.getUserName()).forAuthenticate(true).build());
        if (!utils.isEmpty(user)) {
            if (!userRepository.isAdmin(userDto.getUserName())) {
                OrganizationSettingsDto organizationSettings = organizationSettingsService.getOrganizationSettings(OrganizationSettingsCriteria.childBuilder().organizationId(user.getOrganizationId()).build());
                checkUserLimit(organizationSettings);
            }

            return userPasswordEncoder.matches(userDto.getPassword(), user.getPassword());
        }
        return false;
    }

    private void clearExistingTokens(AuthUserDto userDto) {
        if (checkUserAuthentication(userDto)) {
            Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(clientId, userDto.getUserName());
            if (!utils.isEmpty(tokens) && tokens.size() > 0) {
                OAuth2AccessToken token = (OAuth2AccessToken) tokens.toArray()[0];
                tokenStore.removeAccessToken(token);
            }
        }
    }

    private void checkUserLimit(OrganizationSettingsDto dto) {
        if (utils.isEmpty(dto))
            throw new RuntimeException("Organization settings not found");
        String encodedData = dto.getParams().get("certificate").asText();
        String decodedData = pkcsChecker.decrypt(encodedData);

        if (utils.isEmpty(decodedData))
            throw new RuntimeException("Your certificate is invalid please call instructors!");

        JsonNode decodedParams = utils.fromStringToNode(decodedData);

        if (utils.isEmpty(decodedParams) || !decodedParams.has("organizationId") || !decodedParams.has("token_limit"))
            throw new RuntimeException("Your certificate is invalid please call instructors!");

        if (decodedParams.get("organizationId").asLong() != dto.getOrganizationId()
                || decodedParams.get("token_limit").asInt() <= organizationSettingsService.getCurrentUserCount(dto.getOrganizationId()))
            throw new RuntimeException("You can not add users. Please buy new certificate");


    }

    private ResponseEntity<DataDto<SessionDto>> createSessionForOtpClient(String userName) {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userName);

        Set<String> scopes = new HashSet<>(Arrays.asList("read", "write"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(), userDetails.getAuthorities());
        OAuth2Request oauth2Request = new OAuth2Request(null,
                "spring-security-oauth2-read-write-client", userDetails.getAuthorities(), true, scopes,
                null, null, null, null);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oauth2Request, authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        oAuth2Authentication.setDetails(token);

        SecurityContextHolder.getContext().setAuthentication(oAuth2Authentication);

        return new ResponseEntity<>(new DataDto<>(authUtils.onSuccessSession(token)), HttpStatus.OK);
    }
}
