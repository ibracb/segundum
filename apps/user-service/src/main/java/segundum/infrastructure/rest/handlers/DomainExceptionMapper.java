package segundum.infrastructure.rest.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.domain.exceptions.DomainException;
import segundum.infrastructure.rest.responses.ErrorResponse;

/**
 * Exception mapper that maps domain exceptions without a specific mapper to HTTP 400 Bad Request responses.
 */
@Provider
public class DomainExceptionMapper implements ExceptionMapper<DomainException> {

	/**
	 * Maps a domain exception to a 400 Bad Request response.
	 * 
	 * @param exception the domain exception to map
	 * @return a response with the 400 Bad Request status and the exception message
	 */
	@Override
	public Response toResponse(DomainException exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.BAD_REQUEST.getStatusCode(),
				"Bad Request",
				exception.getMessage());
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
