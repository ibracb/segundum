package segundum.domain.exceptions.sale.title;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the title is null.
 */
@SuppressWarnings("serial")
public class TitleNullException extends DomainException {

/**
 * Constructs a new TitleNullException.
 */
public TitleNullException() {
        super("Title cannot be null");
    }

}
