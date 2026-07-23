package segundum.infrastructure.rest.user.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import segundum.infrastructure.rest.user.requests.RegisterUserRequest;

@Path("/users")
public interface RegisterUserApi {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response registerUser(RegisterUserRequest request);

}
