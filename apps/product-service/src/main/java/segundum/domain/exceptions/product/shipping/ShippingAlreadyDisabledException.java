package segundum.domain.exceptions.product.shipping;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when trying to disable shipping that is already disabled.
 */
@SuppressWarnings("serial")
public class ShippingAlreadyDisabledException extends DomainException {

		/**
	 * Constructs a new ShippingAlreadyDisabledException with a default message.
	 */
	public ShippingAlreadyDisabledException() {
		super("Shipping is already disabled");
	}

}
