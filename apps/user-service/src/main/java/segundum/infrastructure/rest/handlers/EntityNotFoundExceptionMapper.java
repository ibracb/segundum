package segundum.infrastructure.rest.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.domain.exceptions.EntityNotFoundException;

/**
 * Exception mapper that maps EntityNotFoundException to HTTP 404 Not Found responses.
 */
@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

	/**
	 * Maps an EntityNotFoundException to a 404 Not Found response.
	 * 
	 * @param exception the exception to map
	 * @return a response with the 404 Not Found status and the exception message
	 */
	@Override
	public Response toResponse(EntityNotFoundException exception) {
		ErrorResponse body = new ErrorResponse(
				Response.Status.NOT_FOUND.getStatusCode(),
				"Not Found",
				exception.getMessage());
		return Response.status(Response.Status.NOT_FOUND)
				.entity(body)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
