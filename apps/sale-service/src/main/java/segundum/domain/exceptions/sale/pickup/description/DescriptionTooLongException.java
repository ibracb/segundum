package segundum.domain.exceptions.sale.pickup.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the description is too long.
 */
@SuppressWarnings("serial")
public class DescriptionTooLongException extends DomainException {

/**
 * Constructs a new DescriptionTooLongException.
 */
public DescriptionTooLongException() {
        super("Description must not exceed 500 characters");
    }

}
