package segundum.domain.exceptions.category.name;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category name is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class NameInvalidFormatException extends DomainException {

	/**
	 * Constructs a new NameInvalidFormatException with a default message indicating that the category name can only
	 * contain letters and spaces.
	 */
	public NameInvalidFormatException() {
		super("Category name can only contain letters and spaces");
	}

}
