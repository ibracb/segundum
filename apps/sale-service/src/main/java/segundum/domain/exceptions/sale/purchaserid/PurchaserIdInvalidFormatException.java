package segundum.domain.exceptions.sale.purchaserid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser id has an invalid format.
 */
@SuppressWarnings("serial")
public class PurchaserIdInvalidFormatException extends DomainException {

/**
 * Constructs a new PurchaserIdInvalidFormatException.
 */
public PurchaserIdInvalidFormatException() {
        super("Purchaser ID is not a valid UUID");
    }

}
