package segundum.infrastructure.rest.product.errorhandlers.pickup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.pickup.description.DescriptionTooLongException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for pickup DescriptionTooLongException.
 */
@RestControllerAdvice
public class PickupDescriptionTooLongExceptionMapper {

	@ExceptionHandler(DescriptionTooLongException.class)
	public ResponseEntity<ErrorResponse> handle(DescriptionTooLongException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
