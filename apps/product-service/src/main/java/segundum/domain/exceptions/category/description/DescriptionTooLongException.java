package segundum.domain.exceptions.category.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category description exceeds the maximum allowed length.
 */
@SuppressWarnings("serial")
public class DescriptionTooLongException extends DomainException {

	/**
	 * Constructs a new DescriptionTooLongException with a default message.
	 */
	public DescriptionTooLongException() {
		super("Category description cannot exceed 500 characters");
	}

}
