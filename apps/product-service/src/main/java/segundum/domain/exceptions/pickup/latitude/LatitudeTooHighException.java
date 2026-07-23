package segundum.domain.exceptions.pickup.latitude;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a latitude value exceeds the maximum allowed value (90.0).
 */
@SuppressWarnings("serial")
public class LatitudeTooHighException extends DomainException {

	/**
	 * Constructs a new LatitudeTooHighException with a default message.
	 */
	public LatitudeTooHighException() {
		super("Latitude cannot be higher than 90.0");
	}

}
