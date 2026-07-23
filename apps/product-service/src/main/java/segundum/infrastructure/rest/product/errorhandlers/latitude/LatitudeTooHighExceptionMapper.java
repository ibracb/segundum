package segundum.infrastructure.rest.product.errorhandlers.latitude;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.pickup.latitude.LatitudeTooHighException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for LatitudeTooHighException.
 */
@RestControllerAdvice
public class LatitudeTooHighExceptionMapper {

	@ExceptionHandler(LatitudeTooHighException.class)
	public ResponseEntity<ErrorResponse> handle(LatitudeTooHighException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
