package segundum.infrastructure.rest.product.errorhandlers.salestatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.product.salestatus.ProductNotReservedException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for ProductNotReservedException.
 */
@RestControllerAdvice
public class ProductNotReservedExceptionMapper {

	@ExceptionHandler(ProductNotReservedException.class)
	public ResponseEntity<ErrorResponse> handle(ProductNotReservedException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(
						HttpStatus.CONFLICT.value(),
						HttpStatus.CONFLICT.getReasonPhrase(),
						ex.getMessage()));
	}

}
