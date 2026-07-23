package segundum.infrastructure.rest.product.errorhandlers.title;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.product.title.TitleTooLongException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for TitleTooLongException.
 */
@RestControllerAdvice
public class TitleTooLongExceptionMapper {

	@ExceptionHandler(TitleTooLongException.class)
	public ResponseEntity<ErrorResponse> handle(TitleTooLongException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
