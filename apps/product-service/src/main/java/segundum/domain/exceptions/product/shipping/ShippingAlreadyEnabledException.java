package segundum.domain.exceptions.product.shipping;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when trying to enable shipping that is already enabled.
 */
@SuppressWarnings("serial")
public class ShippingAlreadyEnabledException extends DomainException {

		/**
	 * Constructs a new ShippingAlreadyEnabledException with a default message.
	 */
	public ShippingAlreadyEnabledException() {
		super("Shipping is already enabled");
	}

}
