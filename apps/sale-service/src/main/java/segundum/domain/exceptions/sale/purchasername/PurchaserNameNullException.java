package segundum.domain.exceptions.sale.purchasername;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser name is null.
 */
@SuppressWarnings("serial")
public class PurchaserNameNullException extends DomainException {

/**
 * Constructs a new PurchaserNameNullException.
 */
public PurchaserNameNullException() {
        super("Purchaser name is null");
    }

}
