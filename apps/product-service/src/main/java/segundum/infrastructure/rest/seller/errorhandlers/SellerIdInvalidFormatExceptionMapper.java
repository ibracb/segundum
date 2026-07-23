package segundum.infrastructure.rest.seller.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.seller.sellerid.SellerIdInvalidFormatException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for SellerIdInvalidFormatException.
 */
@RestControllerAdvice
public class SellerIdInvalidFormatExceptionMapper {

	@ExceptionHandler(SellerIdInvalidFormatException.class)
	public ResponseEntity<ErrorResponse> handle(SellerIdInvalidFormatException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
