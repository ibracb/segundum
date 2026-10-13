package segundum.infrastructure.rest.errors.errorhandlers;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.infrastructure.rest.errors.responses.ErrorResponse;

/**
 * Exception mapper that maps unsupported HTTP methods to HTTP 405 Method Not Allowed responses.
 * <p>
 * JAX-RS raises a {@link NotAllowedException} when the path exists but not for the requested
 * HTTP method. Without this mapper that exception falls through to {@link GenericExceptionMapper}
 * and a plain 405 is reported as an internal server error (500).
 * </p>
 */
@Provider
public class NotAllowedExceptionMapper implements ExceptionMapper<NotAllowedException> {

	/**
	 * Maps a request with an unsupported HTTP method to a 405 Method Not Allowed response.
	 *
	 * @param exception the routing exception raised by JAX-RS
	 * @return a response with the 405 Method Not Allowed status
	 */
	@Override
	public Response toResponse(NotAllowedException exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.METHOD_NOT_ALLOWED.getStatusCode(),
				Response.Status.METHOD_NOT_ALLOWED.getReasonPhrase(),
				"The HTTP method is not supported by this resource.");
		return Response.status(Response.Status.METHOD_NOT_ALLOWED)
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
