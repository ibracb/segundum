package segundum.domain.exceptions.sale.sellersurname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller surname is null.
 */
@SuppressWarnings("serial")
public class SellerSurnameNullException extends DomainException {

/**
 * Constructs a new SellerSurnameNullException.
 */
public SellerSurnameNullException() {
        super("Seller surname is null");
    }

}
