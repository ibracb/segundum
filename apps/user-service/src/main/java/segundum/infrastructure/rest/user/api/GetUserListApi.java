package segundum.infrastructure.rest.user.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "User management endpoints")
@Path("/users")
public interface GetUserListApi {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get a list of user info")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of user info retrieved successfully"),
			@ApiResponse(responseCode = "401", description = "Missing, invalid or expired authentication token (body: {\"code\": 401, \"error\": \"Unauthorized\", \"message\": \"...\"})",
					content = @Content),
			@ApiResponse(responseCode = "403", description = "Access denied: the endpoint requires the ADMINISTRATOR role (body: {\"code\": 403, \"error\": \"Forbidden\", \"message\": \"Insufficient permissions\"})",
					content = @Content),
	})
	public Response getUserList();

}
