package segundum.infrastructure.rest.product.errorhandlers.shipping;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.product.shipping.ShippingAlreadyEnabledException;
import segundum.infrastructure.rest.errors.responses.ErrorResponse;

/**
 * Exception mapper for ShippingAlreadyEnabledException.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
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
