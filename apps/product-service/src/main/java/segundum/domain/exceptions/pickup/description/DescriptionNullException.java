package segundum.domain.exceptions.pickup.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a pickup location description is null.
 */
@SuppressWarnings("serial")
public class DescriptionNullException extends DomainException {

	/**
	 * Constructs a new DescriptionNullException with a default message.
	 */
	public DescriptionNullException() {
		super("Pickup location description cannot be null");
	}

}
