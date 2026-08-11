package segundum.domain.exceptions.sale.pickup.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the description is blank.
 */
@SuppressWarnings("serial")
public class DescriptionBlankException extends DomainException {

/**
 * Constructs a new DescriptionBlankException.
 */
public DescriptionBlankException() {
        super("Description cannot be blank");
    }

}
