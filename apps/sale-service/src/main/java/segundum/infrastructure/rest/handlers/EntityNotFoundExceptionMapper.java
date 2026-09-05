package segundum.infrastructure.rest.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.EntityNotFoundException;

/**
 * Exception mapper for EntityNotFoundException.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EntityNotFoundExceptionMapper {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex.getMessage()));
    }

}
