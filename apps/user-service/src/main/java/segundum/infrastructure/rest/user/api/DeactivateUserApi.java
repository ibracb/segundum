package segundum.infrastructure.rest.user.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Represents the REST API for deactivating a user.
 */
@Tag(name = "Users", description = "User management endpoints")
@Path("/users")
public interface DeactivateUserApi {

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Deactivate a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "User deactivated"),
			@ApiResponse(responseCode = "404", description = "User not found",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "409", description = "User is not active",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	Response deactivateUser(@PathParam("id") @Parameter(description = "User ID") String id);

}
