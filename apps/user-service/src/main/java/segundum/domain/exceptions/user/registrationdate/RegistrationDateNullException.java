package segundum.domain.exceptions.user.registrationdate;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a registration date is null.
 */
@SuppressWarnings("serial")
public class RegistrationDateNullException extends DomainException {

	/**
	 * Constructs a new RegistrationDateNullException with a message indicating that the registration date cannot be null.
	 */
	public RegistrationDateNullException() {
		super("Registration date cannot be null");
	}

}
