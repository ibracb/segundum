package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.notificationhandlers.SaleNotificationHandler;
import segundum.application.notifications.sales.SaleCancelledNotification;
import segundum.application.notifications.sales.SaleCompletedNotification;
import segundum.application.notifications.sales.SaleReservedNotification;

/**
 * Represents the transaction boundary for the sales bounded context event handlers.
 */
@Component
public class SaleNotificationFacade {

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
	 * Handles a sale reserved event within a single transaction.
	 *
	 * @param event the sale reserved event
	 */
	@Transactional
	public void onSaleReservedNotification(SaleReservedNotification event) {
		handler.onSaleReservedNotification(event);
	}

	/**
	 * Handles a sale completed event within a single transaction.
	 *
	 * @param event the sale completed event
	 */
	@Transactional
	public void onSaleCompletedNotification(SaleCompletedNotification event) {
		handler.onSaleCompletedNotification(event);
	}

	/**
	 * Handles a sale cancelled event within a single transaction.
	 *
	 * @param event the sale cancelled event
	 */
	@Transactional
	public void onSaleCancelledNotification(SaleCancelledNotification event) {
		handler.onSaleCancelledNotification(event);
	}

}
