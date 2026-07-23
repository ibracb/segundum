package segundum.domain.exceptions.user.name;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a name is blank or empty.
 */
@SuppressWarnings("serial")
public class NameBlankException extends DomainException {

	/**
	 * Constructs a new NameBlankException with a default message.
	 */
	public NameBlankException() {
		super("Name cannot be blank");
	}

}
