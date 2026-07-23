package segundum.infrastructure.rest.product.errorhandlers.longitude;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.pickup.longitude.LongitudeTooHighException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for LongitudeTooHighException.
 */
@RestControllerAdvice
public class LongitudeTooHighExceptionMapper {

	@ExceptionHandler(LongitudeTooHighException.class)
	public ResponseEntity<ErrorResponse> handle(LongitudeTooHighException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
