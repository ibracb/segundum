package segundum.infrastructure.rest.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.domain.exceptions.SameValueException;
import segundum.infrastructure.rest.responses.ErrorResponse;

/**
 * Exception mapper that maps SameValueException to HTTP 409 Conflict responses.
 */
@Provider
public class SameValueExceptionMapper implements ExceptionMapper<SameValueException> {

	/**
	 * Maps a SameValueException to a 409 Conflict response.
	 * 
	 * @param exception the exception to map
	 * @return a response with the 409 Conflict status and the exception message
	 */
	@Override
	public Response toResponse(SameValueException exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.CONFLICT.getStatusCode(),
				"Conflict",
				exception.getMessage());
		return Response.status(Response.Status.CONFLICT)
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
