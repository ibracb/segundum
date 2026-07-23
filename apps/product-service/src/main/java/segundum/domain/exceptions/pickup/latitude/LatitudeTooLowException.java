package segundum.domain.exceptions.pickup.latitude;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a latitude value is below the minimum allowed value (-90.0).
 */
@SuppressWarnings("serial")
public class LatitudeTooLowException extends DomainException {

	/**
	 * Constructs a new LatitudeTooLowException with a default message.
	 */
	public LatitudeTooLowException() {
		super("Latitude cannot be lower than -90.0");
	}

}
