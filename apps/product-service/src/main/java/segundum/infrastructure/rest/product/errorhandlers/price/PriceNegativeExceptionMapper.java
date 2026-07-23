package segundum.infrastructure.rest.product.errorhandlers.price;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.product.price.PriceNegativeException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for PriceNegativeException.
 */
@RestControllerAdvice
public class PriceNegativeExceptionMapper {

	@ExceptionHandler(PriceNegativeException.class)
	public ResponseEntity<ErrorResponse> handle(PriceNegativeException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
