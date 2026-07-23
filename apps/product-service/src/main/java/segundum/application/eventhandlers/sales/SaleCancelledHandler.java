package segundum.application.eventhandlers.sales;

import segundum.application.events.sales.SaleCancelled;

/**
 * Port interface for handling reservation cancelled events.
 */
public interface SaleCancelledHandler {

	/**
	 * Handles the given reservation cancelled event.
	 *
	 * @param event the reservation cancelled event to handle
	 */
	void handle(SaleCancelled event);

}
