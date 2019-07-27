package uz.genesis.trello.service.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.GrantType;
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
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.AuthTryCreateDto;
import uz.genesis.trello.dto.auth.AuthUserDto;
import uz.genesis.trello.dto.auth.SessionDto;
import uz.genesis.trello.dto.auth.UserLastLoginCreateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.property.ServerProperties;
import uz.genesis.trello.repository.auth.IUserRepository;
import uz.genesis.trello.utils.BaseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements IAuthService {


    public static String OAUTH_AUTH_URL = "/oauth/token";
    private static String SERVER_URL;

    private final BaseUtils utils;
    private final IUserRepository userRepository;
    private final IAuthTryService authTryService;
    private final IUserLastLoginService userLastLoginService;

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;
    @Value("${oauth2.clientId}")
    private String clientId;
    @Value("${oauth2.clientSecret}")
    private String clientSecret;

    @Autowired
    public AuthService(BaseUtils utils, ServerProperties serverProperties, IUserRepository userRepository, IAuthTryService authTryService, IUserLastLoginService userLastLoginService) {
        this.utils = utils;

        SERVER_URL = "http://" + serverProperties.getIp() + ":" + serverProperties.getPort() + "";
        this.userRepository = userRepository;
        this.authTryService = authTryService;
        this.userLastLoginService = userLastLoginService;
        //        SERVER_URL = "https://" + serverProperties.getUrl();
        OAUTH_AUTH_URL = SERVER_URL + OAUTH_AUTH_URL;
    }


    @Override
    public ResponseEntity<DataDto<SessionDto>> login(AuthUserDto user, HttpServletRequest request) {
        try {

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
            nameValuePairs.add(new BasicNameValuePair("refresh_token", user.getResfreshToken()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.addHeader(HttpHeaders.AUTHORIZATION, getAuthorization());
//            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpResponse response = httpclient.execute(httppost);

            return getAuthDtoDataDto(user, response, false, request);

        } catch (IOException ex) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage())), HttpStatus.OK);
        }
    }

    private ResponseEntity<DataDto<SessionDto>> getAuthDtoDataDto(AuthUserDto user, HttpResponse response, boolean authentication, HttpServletRequest request) throws IOException {
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
            String error_description = json_auth.has("error_description")?json_auth.get("error_description").asText():null;
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
        return "Basic " + utils.encideToBase64(clientId + ":" + clientSecret);
    }

    private void saveUserLastLogin(AuthUserDto user, HttpServletRequest request, String token) {
        UserLastLoginCreateDto lastLogin = UserLastLoginCreateDto.builder().ipAddress(utils.getClientIpAddress(request)).userId(userRepository.find(UserCriteria.childBuilder().userName(user.getUserName()).build()).getId()).sessionToken(token).build();
        userLastLoginService.create(lastLogin);
    }

    private void saveAuthTry(HttpServletRequest request, AuthUserDto userDto, HttpResponse response) {
        int status = response.getStatusLine().getStatusCode();
        long id;
        switch (status) {
            case 400:
                id = 84;
                break;
            case 401:
                id = 85;
                break;
            case 403:
                id = 89;
                break;
            default:
                id = 90;
                break;
        }
        AuthTryCreateDto createDto = AuthTryCreateDto.builder().ipAddress(utils.getClientIpAddress(request)).userName(userDto.getUserName()).resultType(GenericDto.builder().id(id).build()).build();
        authTryService.create(createDto);
    }
}
