package segundum.infrastructure.rest.product.errorhandlers.pickup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.pickup.description.DescriptionNullException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for pickup DescriptionNullException.
 */
@RestControllerAdvice
public class PickupDescriptionNullExceptionMapper {

	@ExceptionHandler(DescriptionNullException.class)
	public ResponseEntity<ErrorResponse> handle(DescriptionNullException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
