package segundum.domain.exceptions.name;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a name is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class NameInvalidFormatException extends DomainException {

	/**
	 * Constructs a new NameInvalidFormatException with a default message indicating that the name can only
	 * contain letters and spaces.
	 */
	public NameInvalidFormatException() {
		super("Name can only contain letters and spaces");
	}

}
