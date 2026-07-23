package segundum.infrastructure.rest.product.errorhandlers.pickup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.pickup.description.DescriptionBlankException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for pickup DescriptionBlankException.
 */
@RestControllerAdvice
public class PickupDescriptionBlankExceptionMapper {

	@ExceptionHandler(DescriptionBlankException.class)
	public ResponseEntity<ErrorResponse> handle(DescriptionBlankException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
