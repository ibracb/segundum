package segundum.domain.exceptions.sale.status;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the sale is not owned by the purchaser.
 */
@SuppressWarnings("serial")
public class SaleNotOwnedByPurchaserException extends DomainException {

/**
 * Constructs a new SaleNotOwnedByPurchaserException.
 */
public SaleNotOwnedByPurchaserException() {
        super("Sale is not owned by this purchaser");
    }

}
