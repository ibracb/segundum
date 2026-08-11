package segundum.infrastructure.rest.sale.errorhandlers.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.sale.status.SaleNotReservedException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for SaleNotReservedException.
 */
@RestControllerAdvice
public class SaleNotReservedExceptionMapper {

    @ExceptionHandler(SaleNotReservedException.class)
    public ResponseEntity<ErrorResponse> handle(SaleNotReservedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage()));
    }

}
