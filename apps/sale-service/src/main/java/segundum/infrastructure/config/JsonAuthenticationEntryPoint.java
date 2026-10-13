package segundum.infrastructure.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Authentication entry point that answers 401 with a JSON body for requests
 * without credentials or with an invalid token, instead of the Spring default
 * of answering 403, which cannot be told apart from an authorization denial.
 */
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(
                "{\"code\":401,\"error\":\"Unauthorized\",\"message\":\"Missing or invalid access token\"}");
    }

}
