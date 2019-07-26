package uz.genesis.trello.service.auth;

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
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;
import uz.genesis.trello.property.ServerProperties;
import uz.genesis.trello.repository.auth.IUserRepository;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository repository;
    private final UserDetailsService userDetailsService;
    private final ServerProperties serverProperties;

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;

    private static String SERVER_URL;
    public static String OAUTH_AUTH_URL = "/oauth/token";

    @Autowired
    public AuthService(IUserRepository repository, UserDetailsService userDetailsService, ServerProperties serverProperties) {
        this.repository = repository;
        this.userDetailsService = userDetailsService;
        this.serverProperties = serverProperties;

        SERVER_URL = "http://" + serverProperties.getIp() + ":" + serverProperties.getPort() + "";
        //        SERVER_URL = "https://" + serverProperties.getUrl();
        OAUTH_AUTH_URL = SERVER_URL + OAUTH_AUTH_URL;
    }

    /*@Override
    public DataDto<AuthDto> login(AuthUserDto user) {
        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(OAUTH_AUTH_URL);

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("grant_type", GrantType.PASSWORD.getValue()));
            nameValuePairs.add(new BasicNameValuePair("username", user.getEmail()));
            nameValuePairs.add(new BasicNameValuePair("password", user.getPassword()));
//            nameValuePairs.add(new BasicNameValuePair("client_id", "spring-security-oauth2-read-write-client"));
//            nameValuePairs.add(new BasicNameValuePair("client_secret", "spring-security-oauth2-read-write-client-password1234"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.addHeader(HttpHeaders.AUTHORIZATION, user.getAuthorization());

            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");

            HttpResponse response = httpclient.execute(httppost);
            return getAuthDtoDataDto(user, response, true);

        } catch (Exception ex) {
            return new DataDto<>(null, new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }*/

/*    @Override
    public DataDto<Boolean> logout(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            tokenServices.revokeToken(tokenId);
            return new DataDto<>(true, HttpStatus.OK);
        }

        return new DataDto<>(false, HttpStatus.OK);
    }*/

    /*@Override
    public DataDto<AuthDto> refreshToken(AuthUserDto user) {
        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(OAUTH_AUTH_URL);

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("grant_type", GrantType.REFRESH_TOKEN.getValue()));
            nameValuePairs.add(new BasicNameValuePair("refresh_token", user.getRefreshToken()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httppost.addHeader(HttpHeaders.AUTHORIZATION, user.getAuthorization());
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpResponse response = httpclient.execute(httppost);

            return getAuthDtoDataDto(user, response, false);

        } catch (IOException ex) {
            return new DataDto<>(null, new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }*/

    /*private DataDto<AuthDto> getAuthDtoDataDto(AuthUserDto user, HttpResponse response, boolean authentication) throws IOException {
        JSONObject json_auth = new JSONObject(EntityUtils.toString(response.getEntity()));

        if (!json_auth.has("error")) {
            User userDomain;
            UserDto userDto = null;
            AuthorityDto authorityDto = null;
            if (authentication) {
                //last login enter
            }

            AuthDto authDto = AuthDto.builder().session(AuthUtils.onSuccessSession(json_auth))
                    .authority(authorityDto)
                    .user(userDto).build();
            return new DataDto<>(authDto, HttpStatus.OK.value());
        } else {
            String error_description = json_auth.optString("error_description");
            if (error_description == null || error_description.isEmpty()) {
                error_description = "Username or password is wrong custom message";
            } else if (error_description.contains("NoResultException")) {
                error_description = "Username or password is wrong or this user is not active in this System";
            }
            if (authentication) {

            } else {
                throw new RefreshTokenExpiredException("Refresh token expired or Invalid refresh token");
            }
            return new DataDto<>(null, new AppErrorDto(HttpStatus.UNAUTHORIZED.value(),
                    error_description), HttpStatus.UNAUTHORIZED.value());
        }
    }*/
}
