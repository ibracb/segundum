package segundum.domain.events;

import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.Sale;
import segundum.domain.models.sale.SaleId;

/**
 * Represents a domain event related to a sale.
 */
public interface SaleEvent {

	/**
	 * Dispatches this event to the given sale.
	 *
	 * @param sale the sale to dispatch the event to
	 */
    void dispatch(Sale sale);

	/**
	 * Returns the sale identifier.
	 *
	 * @return the sale identifier
	 */
    SaleId getSaleId();

	/**
	 * Returns the product identifier.
	 *
	 * @return the product identifier
	 */
    ProductId getProductId();

}
