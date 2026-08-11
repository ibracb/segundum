package segundum.infrastructure.rest.sale.errorhandlers.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.sale.status.SaleNotOwnedByPurchaserException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for SaleNotOwnedByPurchaserException.
 */
@RestControllerAdvice
public class SaleNotOwnedByPurchaserExceptionMapper {

    @ExceptionHandler(SaleNotOwnedByPurchaserException.class)
    public ResponseEntity<ErrorResponse> handle(SaleNotOwnedByPurchaserException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage()));
    }

}
