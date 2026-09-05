package segundum.infrastructure.rest.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.DomainException;

/**
 * Generic exception mapper for domain exceptions not covered by a more specific handler.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class DomainExceptionMapper {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handle(DomainException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        ex.getMessage()));
    }

}
