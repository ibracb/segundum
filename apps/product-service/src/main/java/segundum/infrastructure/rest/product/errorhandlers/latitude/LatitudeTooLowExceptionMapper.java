package segundum.infrastructure.rest.product.errorhandlers.latitude;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.pickup.latitude.LatitudeTooLowException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for LatitudeTooLowException.
 */
@RestControllerAdvice
public class LatitudeTooLowExceptionMapper {

	@ExceptionHandler(LatitudeTooLowException.class)
	public ResponseEntity<ErrorResponse> handle(LatitudeTooLowException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
