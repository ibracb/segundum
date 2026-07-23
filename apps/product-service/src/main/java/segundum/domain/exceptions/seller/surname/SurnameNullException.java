package segundum.domain.exceptions.seller.surname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller surname is null.
 */
@SuppressWarnings("serial")
public class SurnameNullException extends DomainException {

	/**
	 * Constructs a new SurnameNullException with a message indicating that the seller surname cannot be null.
	 */
	public SurnameNullException() {
		super("Seller surname cannot be null");
	}

}
