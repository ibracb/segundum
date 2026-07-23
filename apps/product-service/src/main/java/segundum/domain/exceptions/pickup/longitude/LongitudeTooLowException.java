package segundum.domain.exceptions.pickup.longitude;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a longitude value is below the minimum allowed value (-180.0).
 */
@SuppressWarnings("serial")
public class LongitudeTooLowException extends DomainException {

	/**
	 * Constructs a new LongitudeTooLowException with a default message.
	 */
	public LongitudeTooLowException() {
		super("Longitude cannot be lower than -180.0");
	}

}
