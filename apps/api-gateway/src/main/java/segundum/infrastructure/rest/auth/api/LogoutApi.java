package segundum.infrastructure.rest.auth.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST API for ending an authenticated session.
 */
@Tag(name = "Auth", description = "Authentication endpoints")
@RequestMapping("/auth")
public interface LogoutApi {

    @Operation(summary = "Log out and clear the JWT cookie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Session cookie cleared",
                    content = @Content)
    })
    @PostMapping("/logout")
    ResponseEntity<Void> logout(HttpServletResponse response);

}
