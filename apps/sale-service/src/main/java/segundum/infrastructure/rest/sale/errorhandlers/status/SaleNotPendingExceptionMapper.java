package segundum.infrastructure.rest.sale.errorhandlers.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.sale.status.SaleNotPendingException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for SaleNotPendingException.
 */
@RestControllerAdvice
public class SaleNotPendingExceptionMapper {

    @ExceptionHandler(SaleNotPendingException.class)
    public ResponseEntity<ErrorResponse> handle(SaleNotPendingException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage()));
    }

}
