package segundum.domain.exceptions.user.phone;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a phone number is null.
 */
@SuppressWarnings("serial")
public class PhoneNullException extends DomainException {

	/**
	 * Constructs a new PhoneNullException with a message indicating that the phone number cannot be null.
	 */
	public PhoneNullException() {
		super("Phone number cannot be null");
	}

}
