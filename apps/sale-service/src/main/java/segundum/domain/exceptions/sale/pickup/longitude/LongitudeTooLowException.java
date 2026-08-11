package segundum.domain.exceptions.sale.pickup.longitude;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the longitude is too low.
 */
@SuppressWarnings("serial")
public class LongitudeTooLowException extends DomainException {

/**
 * Constructs a new LongitudeTooLowException.
 */
public LongitudeTooLowException() {
        super("Longitude must be at least -180");
    }

}
