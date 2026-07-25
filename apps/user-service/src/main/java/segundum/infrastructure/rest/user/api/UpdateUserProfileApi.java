package segundum.infrastructure.rest.user.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.handlers.ErrorResponse;
import segundum.infrastructure.rest.user.requests.UpdateUserProfileRequest;
import segundum.infrastructure.rest.user.responses.UserProfileResponse;

@Tag(name = "Users", description = "User management endpoints")
@Path("/users")
public interface UpdateUserProfileApi {

	@PATCH
	@Path("/{id}")
	@Operation(summary = "Update user profile")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Profile updated",
					content = @Content(schema = @Schema(implementation = UserProfileResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "User not found",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "409", description = "Phone already exists or same value",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateUserProfile(
			@PathParam("id") @Parameter(description = "User ID") String id,
			@Parameter(description = "Updated profile data") UpdateUserProfileRequest request);

}
