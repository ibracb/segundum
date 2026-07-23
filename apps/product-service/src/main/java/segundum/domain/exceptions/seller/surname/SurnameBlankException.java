package segundum.domain.exceptions.seller.surname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller surname is blank or empty.
 */
@SuppressWarnings("serial")
public class SurnameBlankException extends DomainException {

	/**
	 * Constructs a new SurnameBlankException with a default message.
	 */
	public SurnameBlankException() {
		super("Seller surname cannot be blank");
	}

}
