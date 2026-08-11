package segundum.infrastructure.rest.user.errorhandlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.domain.exceptions.DomainException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Represents a mapper that converts domain exceptions into HTTP responses.
 */
@Provider
public class DomainExceptionMapper implements ExceptionMapper<DomainException> {

	@Override
	public Response toResponse(DomainException exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.BAD_REQUEST.getStatusCode(),
				Response.Status.BAD_REQUEST.getReasonPhrase(),
				exception.getMessage());
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
