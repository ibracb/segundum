package segundum.infrastructure.rest.user.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.handlers.ErrorResponse;
import segundum.infrastructure.rest.user.requests.RegisterUserRequest;
import segundum.infrastructure.rest.user.responses.UserProfileResponse;

/**
 * Represents the REST API for registering a new user.
 */
@Tag(name = "Users", description = "User management endpoints")
@Path("/users")
public interface RegisterUserApi {

	@POST
	@Operation(summary = "Register a new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User registered",
					content = @Content(schema = @Schema(implementation = UserProfileResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "409", description = "Email already exists or phone already exists",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response registerUser(@RequestBody(description = "User registration data", required = true) RegisterUserRequest request);

}
