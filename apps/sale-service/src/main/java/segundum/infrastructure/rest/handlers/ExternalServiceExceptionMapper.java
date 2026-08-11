package segundum.infrastructure.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.infrastructure.client.ExternalServiceException;

/**
 * Exception mapper for ExternalServiceException (gateway failures).
 */
@RestControllerAdvice
public class ExternalServiceExceptionMapper {

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorResponse> handle(ExternalServiceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ErrorResponse(
                        HttpStatus.BAD_GATEWAY.value(),
                        HttpStatus.BAD_GATEWAY.getReasonPhrase(),
                        ex.getMessage()));
    }

}
