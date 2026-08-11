package segundum.infrastructure.rest.user.errorhandlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.domain.exceptions.user.phone.PhoneAlreadyExistsException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Represents a mapper that converts phone already exists exceptions into HTTP responses.
 */
@Provider
public class PhoneAlreadyExistsExceptionMapper implements ExceptionMapper<PhoneAlreadyExistsException> {

	@Override
	public Response toResponse(PhoneAlreadyExistsException exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.CONFLICT.getStatusCode(),
				Response.Status.CONFLICT.getReasonPhrase(),
				exception.getMessage());
		return Response.status(Response.Status.CONFLICT)
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
