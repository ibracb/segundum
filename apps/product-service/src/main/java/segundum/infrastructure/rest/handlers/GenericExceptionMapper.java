package segundum.infrastructure.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic exception mapper for unhandled exceptions.
 */
@RestControllerAdvice
public class GenericExceptionMapper {

	private static final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handle(Exception ex) {
		log.error("Unhandled exception processing request", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(
						HttpStatus.INTERNAL_SERVER_ERROR.value(),
						HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
						"An unexpected error occurred."));
	}

}
