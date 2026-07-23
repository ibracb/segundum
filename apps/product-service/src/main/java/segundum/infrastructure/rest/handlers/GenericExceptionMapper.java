package segundum.infrastructure.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Generic exception mapper for unhandled exceptions.
 */
@RestControllerAdvice
public class GenericExceptionMapper {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handle(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(
						HttpStatus.INTERNAL_SERVER_ERROR.value(),
						HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
						"An unexpected error occurred."));
	}

}
