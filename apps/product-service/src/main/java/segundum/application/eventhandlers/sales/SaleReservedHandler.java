package segundum.application.eventhandlers.sales;

import segundum.application.events.sales.SaleReserved;

/**
 * Port interface for handling reservation created events.
 */
public interface SaleReservedHandler {

	/**
	 * Handles the given reservation created event.
	 *
	 * @param event the reservation created event to handle
	 */
	void handle(SaleReserved event);

}
