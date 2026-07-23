package segundum.domain.exceptions.user.phone;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a phone number is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class PhoneInvalidFormatException extends DomainException {

	/**
	 * Constructs a new PhoneInvalidFormatException with a default message indicating that the phone number must
	 * be in E.164 format.
	 */
	public PhoneInvalidFormatException() {
		super("Phone number must be in E.164 format");
	}

}
