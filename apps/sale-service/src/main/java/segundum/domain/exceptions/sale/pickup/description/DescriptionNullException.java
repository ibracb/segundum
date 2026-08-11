package segundum.domain.exceptions.sale.pickup.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the description is null.
 */
@SuppressWarnings("serial")
public class DescriptionNullException extends DomainException {

/**
 * Constructs a new DescriptionNullException.
 */
public DescriptionNullException() {
        super("Description cannot be null");
    }

}
