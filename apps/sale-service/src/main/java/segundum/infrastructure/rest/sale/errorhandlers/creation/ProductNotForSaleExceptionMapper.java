package segundum.infrastructure.rest.sale.errorhandlers.creation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.sale.creation.ProductNotForSaleException;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Exception mapper for ProductNotForSaleException.
 */
@RestControllerAdvice
public class ProductNotForSaleExceptionMapper {

    @ExceptionHandler(ProductNotForSaleException.class)
    public ResponseEntity<ErrorResponse> handle(ProductNotForSaleException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage()));
    }

}
