package segundum.domain.exceptions.sale.title;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the title is blank.
 */
@SuppressWarnings("serial")
public class TitleBlankException extends DomainException {

/**
 * Constructs a new TitleBlankException.
 */
public TitleBlankException() {
        super("Title cannot be blank");
    }

}
