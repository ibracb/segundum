package segundum.domain.exceptions.seller.surname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller surname is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class SurnameInvalidFormatException extends DomainException {

	/**
	 * Constructs a new SurnameInvalidFormatException with a default message indicating that the seller surname can only
	 * contain letters and spaces.
	 */
	public SurnameInvalidFormatException() {
		super("Seller surname must contain only letters and spaces");
	}

}
