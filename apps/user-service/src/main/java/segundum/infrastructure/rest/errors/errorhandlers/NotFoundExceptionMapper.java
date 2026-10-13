package segundum.infrastructure.rest.errors.errorhandlers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.infrastructure.rest.errors.responses.ErrorResponse;

/**
 * Exception mapper that maps unknown or unmatched request paths to HTTP 404 Not Found responses.
 * <p>
 * JAX-RS raises a {@link NotFoundException} while routing the request. Without this mapper that
 * exception falls through to {@link GenericExceptionMapper} and a plain "not found" is reported
 * as an internal server error (500).
 * </p>
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	/**
	 * Maps an unmatched request path to a 404 Not Found response.
	 *
	 * @param exception the routing exception raised by JAX-RS
	 * @return a response with the 404 Not Found status
	 */
	@Override
	public Response toResponse(NotFoundException exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.NOT_FOUND.getStatusCode(),
				Response.Status.NOT_FOUND.getReasonPhrase(),
				"The requested resource does not exist.");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
