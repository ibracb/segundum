package segundum.domain.exceptions.pickup.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a pickup location description is blank or empty.
 */
@SuppressWarnings("serial")
public class DescriptionBlankException extends DomainException {

	/**
	 * Constructs a new DescriptionBlankException with a default message.
	 */
	public DescriptionBlankException() {
		super("Pickup location description cannot be blank");
	}

}
