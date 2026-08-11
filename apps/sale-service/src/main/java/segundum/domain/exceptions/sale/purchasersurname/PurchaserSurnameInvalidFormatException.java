package segundum.domain.exceptions.sale.purchasersurname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser surname has an invalid format.
 */
@SuppressWarnings("serial")
public class PurchaserSurnameInvalidFormatException extends DomainException {

/**
 * Constructs a new PurchaserSurnameInvalidFormatException.
 */
public PurchaserSurnameInvalidFormatException() {
        super("Purchaser surname is not alphabetic");
    }

}
