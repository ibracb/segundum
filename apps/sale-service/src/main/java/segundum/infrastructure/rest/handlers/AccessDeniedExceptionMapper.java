package segundum.infrastructure.rest.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception mapper for access denied exceptions.
 * Returns 403 instead of letting the generic handler return 500.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccessDeniedExceptionMapper {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handle(AccessDeniedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(new ErrorResponse(
						HttpStatus.FORBIDDEN.value(),
						HttpStatus.FORBIDDEN.getReasonPhrase(),
						"Access is denied"));
	}

}
