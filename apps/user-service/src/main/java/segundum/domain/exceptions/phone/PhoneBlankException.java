package segundum.domain.exceptions.phone;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a phone number is blank or empty.
 */
@SuppressWarnings("serial")
public class PhoneBlankException extends DomainException {

	/**
	 * Constructs a new PhoneBlankException with a default message.
	 */
	public PhoneBlankException() {
		super("Phone number cannot be blank");
	}

}
