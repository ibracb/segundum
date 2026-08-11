package segundum.domain.exceptions.sale.title;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the title is too long.
 */
@SuppressWarnings("serial")
public class TitleTooLongException extends DomainException {

/**
 * Constructs a new TitleTooLongException.
 */
public TitleTooLongException() {
        super("Title must not exceed 200 characters");
    }

}
