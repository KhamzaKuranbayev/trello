package uz.genesis.trello.config.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

public class AuthenticationFailureHandler implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.addHeader("Content-Type", "application/json");

        /*response.getWriter().print(new Gson().toJson(new DataDto<>(AppErrorDto.builder()
                .friendlyMessage(authException.getLocalizedMessage()).build())));*/

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
