package segundum.infrastructure.rest.user.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public interface DeleteUserApi {

	@DELETE
	@Path("/{id}")
	Response deleteUser(@PathParam("id") String id);

}
