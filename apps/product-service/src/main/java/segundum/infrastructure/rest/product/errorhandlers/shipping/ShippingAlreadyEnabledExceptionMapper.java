package segundum.infrastructure.rest.product.errorhandlers.shipping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.product.shipping.ShippingAlreadyEnabledException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for ShippingAlreadyEnabledException.
 */
@RestControllerAdvice
public class ShippingAlreadyEnabledExceptionMapper {

	@ExceptionHandler(ShippingAlreadyEnabledException.class)
	public ResponseEntity<ErrorResponse> handle(ShippingAlreadyEnabledException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(
						HttpStatus.CONFLICT.value(),
						HttpStatus.CONFLICT.getReasonPhrase(),
						ex.getMessage()));
	}

}
