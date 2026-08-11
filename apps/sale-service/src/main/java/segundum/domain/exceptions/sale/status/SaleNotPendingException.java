package segundum.domain.exceptions.sale.status;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the sale is not pending.
 */
@SuppressWarnings("serial")
public class SaleNotPendingException extends DomainException {

/**
 * Constructs a new SaleNotPendingException.
 */
public SaleNotPendingException() {
        super("Sale must be in PENDING status");
    }

}
