package segundum.domain.exceptions.seller.name;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller name is null.
 */
@SuppressWarnings("serial")
public class NameNullException extends DomainException {

	/**
	 * Constructs a new NameNullException with a message indicating that the seller name cannot be null.
	 */
	public NameNullException() {
		super("Seller name cannot be null");
	}

}
