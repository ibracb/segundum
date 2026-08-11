package segundum.domain.exceptions.sale.purchasername;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser name has an invalid format.
 */
@SuppressWarnings("serial")
public class PurchaserNameInvalidFormatException extends DomainException {

/**
 * Constructs a new PurchaserNameInvalidFormatException.
 */
public PurchaserNameInvalidFormatException() {
        super("Purchaser name is not alphabetic");
    }

}
