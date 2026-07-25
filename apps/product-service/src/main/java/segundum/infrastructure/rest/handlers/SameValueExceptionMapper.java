package segundum.infrastructure.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.SameValueException;

@RestControllerAdvice
public class SameValueExceptionMapper {

	@ExceptionHandler(SameValueException.class)
	public ResponseEntity<ErrorResponse> handle(SameValueException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(
						HttpStatus.CONFLICT.value(),
						HttpStatus.CONFLICT.getReasonPhrase(),
						ex.getMessage()));
	}

}
