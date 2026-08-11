package segundum.application.eventhandlers;

import segundum.application.events.sales.SaleCancelled;
import segundum.application.events.sales.SaleCompleted;
import segundum.application.events.sales.SaleReserved;

/**
 * Port interface for handling events from the sales bounded context.
 */
public interface SaleEventHandler {

	/**
	 * Handles the given reservation created event.
	 *
	 * @param event the reservation created event to handle
	 */
	void onSaleReserved(SaleReserved event);

	/**
	 * Handles the given sale completed event.
	 *
	 * @param event the sale completed event to handle
	 */
	void onSaleCompleted(SaleCompleted event);

	/**
	 * Handles the given reservation cancelled event.
	 *
	 * @param event the reservation cancelled event to handle
	 */
	void onSaleCancelled(SaleCancelled event);

}
