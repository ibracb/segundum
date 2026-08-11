package segundum.infrastructure.rest.user.api;

import javax.ws.rs.GET;
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
import segundum.infrastructure.rest.user.responses.UserStatsResponse;

/**
 * Represents the REST API for retrieving the statistics of a user.
 */
@Tag(name = "Users", description = "User management endpoints")
@Path("/users")
public interface GetUserStatsApi {

	@GET
	@Path("/{id}/stats")
	@Operation(summary = "Get user statistics by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User stats found",
					content = @Content(schema = @Schema(implementation = UserStatsResponse.class))),
			@ApiResponse(responseCode = "404", description = "User not found",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@Produces(MediaType.APPLICATION_JSON)
	Response getUserStats(@PathParam("id") @Parameter(description = "User ID") String id);

}
