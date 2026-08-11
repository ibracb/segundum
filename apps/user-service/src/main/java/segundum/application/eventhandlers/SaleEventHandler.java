package segundum.application.eventhandlers;

import segundum.application.events.sales.SaleCompleted;

/**
 * Port interface for handling events from the sales bounded context.
 */
public interface SaleEventHandler {

	/**
	 * Handles the given sale completed event.
	 *
	 * @param event the sale completed event to handle
	 */
	void onSaleCompleted(SaleCompleted event);

}
