package segundum.application.notificationhandlers;

import segundum.application.notifications.sales.SaleCancelledNotification;
import segundum.application.notifications.sales.SaleCompletedNotification;
import segundum.application.notifications.sales.SaleReservedNotification;

/**
 * Port interface for handling events from the sales bounded context.
 */
public interface SaleNotificationHandler {

	/**
	 * Handles the given reservation created event.
	 *
	 * @param event the reservation created event to handle
	 */
	void onSaleReservedNotification(SaleReservedNotification event);

	/**
	 * Handles the given sale completed event.
	 *
	 * @param event the sale completed event to handle
	 */
	void onSaleCompletedNotification(SaleCompletedNotification event);

	/**
	 * Handles the given reservation cancelled event.
	 *
	 * @param event the reservation cancelled event to handle
	 */
	void onSaleCancelledNotification(SaleCancelledNotification event);

}
