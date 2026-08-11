package segundum.domain.exceptions.sale.purchaserid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser id is null.
 */
@SuppressWarnings("serial")
public class PurchaserIdNullException extends DomainException {

/**
 * Constructs a new PurchaserIdNullException.
 */
public PurchaserIdNullException() {
        super("Purchaser ID cannot be null");
    }

}
