package segundum.domain.exceptions.sale.pickup.longitude;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the longitude is too high.
 */
@SuppressWarnings("serial")
public class LongitudeTooHighException extends DomainException {

/**
 * Constructs a new LongitudeTooHighException.
 */
public LongitudeTooHighException() {
        super("Longitude must be at most 180");
    }

}
