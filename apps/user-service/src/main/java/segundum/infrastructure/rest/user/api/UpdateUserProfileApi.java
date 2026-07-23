package segundum.infrastructure.rest.user.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import segundum.infrastructure.rest.user.requests.UpdateUserProfileRequest;

@Path("/users")
public interface UpdateUserProfileApi {

	@PATCH
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateUserProfile(@PathParam("id") String id, UpdateUserProfileRequest request);

}
