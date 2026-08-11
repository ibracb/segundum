package segundum.domain.exceptions.sale.price;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the price is negative.
 */
@SuppressWarnings("serial")
public class PriceNegativeException extends DomainException {

/**
 * Constructs a new PriceNegativeException.
 */
public PriceNegativeException() {
        super("Price cannot be negative");
    }

}
