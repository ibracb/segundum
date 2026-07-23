package segundum.infrastructure.rest.product.errorhandlers.longitude;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.pickup.longitude.LongitudeTooLowException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for LongitudeTooLowException.
 */
@RestControllerAdvice
public class LongitudeTooLowExceptionMapper {

	@ExceptionHandler(LongitudeTooLowException.class)
	public ResponseEntity<ErrorResponse> handle(LongitudeTooLowException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
