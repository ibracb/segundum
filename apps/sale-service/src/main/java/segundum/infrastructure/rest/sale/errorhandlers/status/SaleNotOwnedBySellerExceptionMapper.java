package segundum.infrastructure.rest.sale.errorhandlers.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.sale.status.SaleNotOwnedBySellerException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for SaleNotOwnedBySellerException.
 */
@RestControllerAdvice
public class SaleNotOwnedBySellerExceptionMapper {

    @ExceptionHandler(SaleNotOwnedBySellerException.class)
    public ResponseEntity<ErrorResponse> handle(SaleNotOwnedBySellerException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage()));
    }

}
