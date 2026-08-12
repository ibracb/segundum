package segundum.infrastructure.facades;

import segundum.application.eventhandlers.SaleEventHandler;
import segundum.application.events.sales.SaleCompleted;

/**
 * Represents the transaction boundary for the sales bounded context event handlers.
 */
public final class SaleEventFacade {

	/**
	 * The sale event handler.
	 */
	private final SaleEventHandler handler;

	/**
	 * Constructs a new SaleEventFacade with the given handler.
	 *
	 * @param handler the sale event handler
	 */
	public SaleEventFacade(SaleEventHandler handler) {
		this.handler = handler;
	}

	/**
	 * Handles a sale completed event within a single transaction.
	 *
	 * @param event the sale completed event
	 */
	public void onSaleCompleted(SaleCompleted event) {
		UnitOfWork.runVoid(() -> handler.onSaleCompleted(event));
	}

}
