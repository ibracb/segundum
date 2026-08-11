package segundum.domain.exceptions.sale.purchasersurname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser surname is null.
 */
@SuppressWarnings("serial")
public class PurchaserSurnameNullException extends DomainException {

/**
 * Constructs a new PurchaserSurnameNullException.
 */
public PurchaserSurnameNullException() {
        super("Purchaser surname is null");
    }

}
