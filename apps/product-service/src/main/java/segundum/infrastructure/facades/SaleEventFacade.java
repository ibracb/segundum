package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.eventhandlers.SaleEventHandler;
import segundum.application.events.sales.SaleCancelled;
import segundum.application.events.sales.SaleCompleted;
import segundum.application.events.sales.SaleReserved;

/**
 * Represents the transaction boundary for the sales bounded context event handlers.
 */
@Component
public class SaleEventFacade {

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
	 * Handles a sale reserved event within a single transaction.
	 *
	 * @param event the sale reserved event
	 */
	@Transactional
	public void onSaleReserved(SaleReserved event) {
		handler.onSaleReserved(event);
	}

	/**
	 * Handles a sale completed event within a single transaction.
	 *
	 * @param event the sale completed event
	 */
	@Transactional
	public void onSaleCompleted(SaleCompleted event) {
		handler.onSaleCompleted(event);
	}

	/**
	 * Handles a sale cancelled event within a single transaction.
	 *
	 * @param event the sale cancelled event
	 */
	@Transactional
	public void onSaleCancelled(SaleCancelled event) {
		handler.onSaleCancelled(event);
	}

}
