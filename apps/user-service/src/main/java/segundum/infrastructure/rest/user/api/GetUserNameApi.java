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
import segundum.infrastructure.rest.user.responses.UserNameResponse;

/**
 * Represents the REST API for retrieving the name of a user.
 */
@Tag(name = "Users", description = "User management endpoints")
@Path("/users")
public interface GetUserNameApi {

    @GET
    @Path("/{id}/name")
    @Operation(summary = "Get user name and surname by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User name found",
                    content = @Content(schema = @Schema(implementation = UserNameResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserName(@PathParam("id") @Parameter(description = "User ID") String id);

}
