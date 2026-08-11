package segundum.infrastructure.rest.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.JsonSyntaxException;

/**
 * Exception mapper that maps a malformed or unreadable JSON body to HTTP 400 Bad Request responses.
 */
@Provider
public class JsonSyntaxExceptionMapper implements ExceptionMapper<JsonSyntaxException> {

	/**
	 * Maps a JsonSyntaxException to a 400 Bad Request response.
	 * 
	 * @param exception the exception to map
	 * @return a response with the 400 Bad Request status
	 */
	@Override
	public Response toResponse(JsonSyntaxException exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.BAD_REQUEST.getStatusCode(),
				"Bad Request",
				"Malformed or unreadable request body.");
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
