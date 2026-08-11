package segundum.domain.exceptions.sale.purchaserid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser id is blank.
 */
@SuppressWarnings("serial")
public class PurchaserIdBlankException extends DomainException {

/**
 * Constructs a new PurchaserIdBlankException.
 */
public PurchaserIdBlankException() {
        super("Purchaser ID cannot be blank");
    }

}
