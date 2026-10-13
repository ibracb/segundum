package segundum.infrastructure.rest.auth.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.infrastructure.rest.auth.api.LogoutApi;

/**
 * Controller that ends an authenticated session by clearing the JWT cookie.
 */
@RestController
public class LogoutController implements LogoutApi {

    /**
     * Clears the access_token cookie by giving it a Max-Age of zero, which is the
     * only way to remove it since the cookie is HttpOnly and therefore invisible
     * to client-side scripts. The endpoint is idempotent: it always answers 204,
     * with or without a cookie in the request.
     *
     * @param response the HTTP response used to clear the cookie
     * @return 204 No Content once the cookie has been cleared
     */
    @Override
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("access_token", "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.noContent().build();
    }

}
