package segundum.domain.exceptions.sale.creation;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the sale is already proposed.
 */
@SuppressWarnings("serial")
public class SaleAlreadyProposedException extends DomainException {

/**
 * Constructs a new SaleAlreadyProposedException.
 */
public SaleAlreadyProposedException() {
        super("Purchaser already has an open proposal for this product");
    }

}
