package segundum.infrastructure.rest.category.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.category.categoryid.CategoryIdNullException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for CategoryIdNullException.
 */
@RestControllerAdvice
public class CategoryIdNullExceptionMapper {

	@ExceptionHandler(CategoryIdNullException.class)
	public ResponseEntity<ErrorResponse> handle(CategoryIdNullException ex) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						ex.getMessage()));
	}

}
