package segundum.infrastructure.rest.seller.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.seller.status.SellerNotActiveException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

@RestControllerAdvice
/**
 * Represents the REST handler that maps SellerNotActiveException to HTTP responses.
 */
public class SellerNotActiveExceptionMapper {

	/**
	 * Handles the given SellerNotActiveException.
	 *
	 * @param ex the exception to handle
	 * @return an HTTP response with the conflict status
	 */
	@ExceptionHandler(SellerNotActiveException.class)
	public ResponseEntity<ErrorResponse> handle(SellerNotActiveException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(
						HttpStatus.CONFLICT.value(),
						HttpStatus.CONFLICT.getReasonPhrase(),
						ex.getMessage()));
	}

}
