package segundum.infrastructure.rest.auth.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.LoginCommand;
import segundum.application.readmodels.auth.AuthenticatedUserReadModel;
import segundum.auth.JwtUtils;
import segundum.infrastructure.facades.AuthFacade;
import segundum.infrastructure.rest.auth.api.LoginApi;
import segundum.infrastructure.rest.auth.requests.LoginRequest;
import segundum.infrastructure.rest.auth.responses.AuthResponse;

/**
 * Controller for user authentication.
 */
@RestController
public class LoginController implements LoginApi {

    /**
     * The authentication facade.
     */
    private final AuthFacade authFacade;

    /**
     * The JWT utility.
     */
    private final JwtUtils jwtUtils;

    /**
     * Constructs a new LoginController with the given facade and JWT utility.
     *
     * @param authFacade the authentication facade
     * @param jwtUtils   the JWT utility
     */
    public LoginController(AuthFacade authFacade, JwtUtils jwtUtils) {
        this.authFacade = authFacade;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest request, HttpServletResponse response) {
        LoginCommand command = new LoginCommand(request.getEmail(), request.getPassword());
        AuthenticatedUserReadModel user = authFacade.run(command);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtUtils.generateToken(user.getId(), user.getFullName(), user.getRoles());

        ResponseCookie cookie = ResponseCookie.from("access_token", token)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(3600)
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        AuthResponse authResponse = new AuthResponse(token, user.getId(), user.getFullName(), user.getRoles());
        return ResponseEntity.ok(authResponse);
    }

}
