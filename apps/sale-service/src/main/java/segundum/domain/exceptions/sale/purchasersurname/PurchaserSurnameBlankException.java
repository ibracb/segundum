package segundum.domain.exceptions.sale.purchasersurname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser surname is blank.
 */
@SuppressWarnings("serial")
public class PurchaserSurnameBlankException extends DomainException {

/**
 * Constructs a new PurchaserSurnameBlankException.
 */
public PurchaserSurnameBlankException() {
        super("Purchaser surname is blank");
    }

}
