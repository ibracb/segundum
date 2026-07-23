package segundum.domain.exceptions.category.name;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category name is blank or empty.
 */
@SuppressWarnings("serial")
public class NameBlankException extends DomainException {

	/**
	 * Constructs a new NameBlankException with a default message.
	 */
	public NameBlankException() {
		super("Category name cannot be blank");
	}

}
