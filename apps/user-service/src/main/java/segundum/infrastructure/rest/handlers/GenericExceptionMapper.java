package segundum.infrastructure.rest.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.infrastructure.rest.responses.ErrorResponse;

/**
 * Exception mapper that maps any unhandled exception to HTTP 500 Internal Server Error responses.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	/**
	 * Maps an unhandled exception to a 500 Internal Server Error response.
	 * 
	 * @param exception the unhandled exception to map
	 * @return a response with the 500 Internal Server Error status
	 */
	@Override
	public Response toResponse(Throwable exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
				"Internal Server Error",
				"An unexpected error occurred.");
		return Response.serverError()
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
