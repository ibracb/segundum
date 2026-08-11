package segundum.domain.exceptions.sale.purchasername;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser name is blank.
 */
@SuppressWarnings("serial")
public class PurchaserNameBlankException extends DomainException {

/**
 * Constructs a new PurchaserNameBlankException.
 */
public PurchaserNameBlankException() {
        super("Purchaser name is blank");
    }

}
