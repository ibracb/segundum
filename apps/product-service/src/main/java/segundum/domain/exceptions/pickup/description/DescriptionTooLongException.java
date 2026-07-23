package segundum.domain.exceptions.pickup.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a pickup location description exceeds the maximum allowed length.
 */
@SuppressWarnings("serial")
public class DescriptionTooLongException extends DomainException {

	/**
	 * Constructs a new DescriptionTooLongException with a default message.
	 */
	public DescriptionTooLongException() {
		super("Pickup location description cannot exceed 500 characters");
	}

}
