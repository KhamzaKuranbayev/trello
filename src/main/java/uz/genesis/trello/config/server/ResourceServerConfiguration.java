package uz.genesis.trello.config.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import uz.genesis.trello.config.handler.AuthenticationFailureHandler;
import uz.genesis.trello.controller.ApiController;

import static uz.genesis.trello.controller.ApiController.API_PATH;
import static uz.genesis.trello.controller.ApiController.V_1;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/secured/**";
    private static final String AUTH_PATTERN = ApiController.AUTH + "/**";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
        resources.authenticationEntryPoint(new AuthenticationFailureHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       /* http.headers().frameOptions().disable();
        http.requestMatchers()
//                .antMatchers(SECURED_PATTERN).and().authorizeRequests()
//                .antMatchers(AUTH_PATTERN).permitAll()
//                .antMatchers(API_PATH + V_1 + "/users/*").permitAll()
//                .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
//                .anyRequest().access(SECURED_READ_SCOPE)
//                .anyRequest().authenticated()
                .and().cors()
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationFailureHandler());*/

//        http.cors().and().authorizeRequests()
////                .antMatchers(API_PATH + V_1 + "/users").permitAll()
//                .antMatchers("/**/*").denyAll()
//                .anyRequest().access(SECURED_WRITE_SCOPE)
//                .antMatchers(HttpMethod.POST, API_PATH + "/**").access(SECURED_WRITE_SCOPE)
//                .anyRequest().access(SECURED_READ_SCOPE);

        http.
                anonymous().disable()
                .requestMatchers().antMatchers(API_PATH + "/**")
                .and().authorizeRequests()
                .antMatchers(API_PATH + "/**").access(SECURED_WRITE_SCOPE)
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationFailureHandler());

        http.cors().and().csrf().disable();

    }
}
