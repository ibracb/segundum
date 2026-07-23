package segundum.domain.exceptions.seller.name;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller name is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class NameInvalidFormatException extends DomainException {

	/**
	 * Constructs a new NameInvalidFormatException with a default message indicating that the seller name can only
	 * contain letters and spaces.
	 */
	public NameInvalidFormatException() {
		super("Seller name can only contain letters and spaces");
	}

}
