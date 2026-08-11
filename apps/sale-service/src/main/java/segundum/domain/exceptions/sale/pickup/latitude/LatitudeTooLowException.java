package segundum.domain.exceptions.sale.pickup.latitude;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the latitude is too low.
 */
@SuppressWarnings("serial")
public class LatitudeTooLowException extends DomainException {

/**
 * Constructs a new LatitudeTooLowException.
 */
public LatitudeTooLowException() {
        super("Latitude must be at least -90");
    }

}
