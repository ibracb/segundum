package segundum.infrastructure.rest.sale.errorhandlers.creation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.sale.creation.PurchaserCannotBeSellerException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for PurchaserCannotBeSellerException.
 */
@RestControllerAdvice
public class PurchaserCannotBeSellerExceptionMapper {

    @ExceptionHandler(PurchaserCannotBeSellerException.class)
    public ResponseEntity<ErrorResponse> handle(PurchaserCannotBeSellerException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage()));
    }

}
