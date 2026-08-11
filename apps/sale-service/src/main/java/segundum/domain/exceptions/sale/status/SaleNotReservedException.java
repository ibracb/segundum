package segundum.domain.exceptions.sale.status;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the sale is not reserved.
 */
@SuppressWarnings("serial")
public class SaleNotReservedException extends DomainException {

/**
 * Constructs a new SaleNotReservedException.
 */
public SaleNotReservedException() {
        super("Sale must be in RESERVED status");
    }

}
