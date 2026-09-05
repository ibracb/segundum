package segundum.infrastructure.facades;

import segundum.application.notificationhandlers.SaleNotificationHandler;
import segundum.application.notifications.sales.SaleCompletedNotification;

/**
 * Represents the transaction boundary for the sales bounded context event handlers.
 */
public final class SaleNotificationFacade {

	/**
	 * The sale event handler.
	 */
	private final SaleNotificationHandler handler;

	/**
	 * Constructs a new SaleNotificationFacade with the given handler.
	 *
	 * @param handler the sale event handler
	 */
	public SaleNotificationFacade(SaleNotificationHandler handler) {
		this.handler = handler;
	}

	/**
	 * Handles a sale completed event within a single transaction.
	 *
	 * @param event the sale completed event
	 */
	public void onSaleCompletedNotification(SaleCompletedNotification event) {
		UnitOfWork.runVoid(() -> handler.onSaleCompletedNotification(event));
	}

}
