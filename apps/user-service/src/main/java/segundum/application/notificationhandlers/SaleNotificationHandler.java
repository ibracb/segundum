package segundum.application.notificationhandlers;

import segundum.application.notifications.sales.SaleCompletedNotification;

/**
 * Port interface for handling events from the sales bounded context.
 */
public interface SaleNotificationHandler {

	/**
	 * Handles the given sale completed event.
	 *
	 * @param event the sale completed event to handle
	 */
	void onSaleCompletedNotification(SaleCompletedNotification event);

}
