package segundum.domain.exceptions.pickup.longitude;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a longitude value exceeds the maximum allowed value (180.0).
 */
@SuppressWarnings("serial")
public class LongitudeTooHighException extends DomainException {

	/**
	 * Constructs a new LongitudeTooHighException with a default message.
	 */
	public LongitudeTooHighException() {
		super("Longitude cannot be higher than 180.0");
	}

}
