package segundum.infrastructure.rest.user.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public interface GetUserProfileApi {

	@GET
	@Path("/{id}/profile")
	@Produces(MediaType.APPLICATION_JSON)
	Response getUserProfile(@PathParam("id") String id);

}
