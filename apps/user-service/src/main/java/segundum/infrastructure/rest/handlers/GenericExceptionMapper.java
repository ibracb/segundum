package segundum.infrastructure.rest.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception mapper that maps any unhandled exception to HTTP 500 Internal Server Error responses.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	private static final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);

	/**
	 * Maps an unhandled exception to a 500 Internal Server Error response.
	 * 
	 * @param exception the unhandled exception to map
	 * @return a response with the 500 Internal Server Error status
	 */
	@Override
	public Response toResponse(Throwable exception) {
		log.error("Unhandled exception processing request", exception);
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
