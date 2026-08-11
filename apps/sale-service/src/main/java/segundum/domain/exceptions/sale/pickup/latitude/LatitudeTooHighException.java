package segundum.domain.exceptions.sale.pickup.latitude;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the latitude is too high.
 */
@SuppressWarnings("serial")
public class LatitudeTooHighException extends DomainException {

/**
 * Constructs a new LatitudeTooHighException.
 */
public LatitudeTooHighException() {
        super("Latitude must be at most 90");
    }

}
