package segundum.application.eventhandlers.sales;

import segundum.application.events.sales.SaleCompleted;

/**
 * Port interface for handling sale completed events.
 */
public interface SaleCompletedHandler {

	/**
	 * Handles the given sale completed event.
	 *
	 * @param event the sale completed event to handle
	 */
	void handle(SaleCompleted event);

}
