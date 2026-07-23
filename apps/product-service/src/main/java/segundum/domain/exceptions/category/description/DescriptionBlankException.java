package segundum.domain.exceptions.category.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category description is blank (but not null, as it is nullable).
 */
@SuppressWarnings("serial")
public class DescriptionBlankException extends DomainException {

	/**
	 * Constructs a new DescriptionBlankException with a default message.
	 */
	public DescriptionBlankException() {
		super("Category description cannot be blank");
	}

}
