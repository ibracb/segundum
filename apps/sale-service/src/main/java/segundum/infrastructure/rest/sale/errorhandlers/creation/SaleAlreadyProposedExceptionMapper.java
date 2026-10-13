package segundum.infrastructure.rest.sale.errorhandlers.creation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import segundum.domain.exceptions.sale.creation.SaleAlreadyProposedException;
import segundum.infrastructure.rest.errors.responses.ErrorResponse;

/**
 * Exception mapper for SaleAlreadyProposedException.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SaleAlreadyProposedExceptionMapper {

    @ExceptionHandler(SaleAlreadyProposedException.class)
    public ResponseEntity<ErrorResponse> handle(SaleAlreadyProposedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage()));
    }

}
