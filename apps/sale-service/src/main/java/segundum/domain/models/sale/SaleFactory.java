package segundum.domain.models.sale;

import java.util.List;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleProposed;

/**
 * Represents a factory for creating and reconstructing sale aggregates.
 */
public class SaleFactory {

	/**
	 * Constructs a new SaleFactory. Prevents instantiation.
	 */
    private SaleFactory() {
    }

	/**
	 * Creates a new Sale with the given values.
	 *
	 * @param saleId the sale identifier
	 * @param productId the product identifier
	 * @param sellerId the seller identifier
	 * @param sellerName the seller name
	 * @param sellerSurname the seller surname
	 * @param purchaserId the purchaser identifier
	 * @param purchaserName the purchaser name
	 * @param purchaserSurname the purchaser surname
	 * @param price the price
	 * @param title the title
	 * @param pickupLocation the pickup location
	 * @param datetime the date time
	 * @return a new Sale object
	 */
    public static Sale create(SaleId saleId, ProductId productId, SellerId sellerId, SellerName sellerName,
            SellerSurname sellerSurname, PurchaserId purchaserId, PurchaserName purchaserName,
            PurchaserSurname purchaserSurname, Price price, Title title,
            PickupLocation pickupLocation, DateTime datetime) {
        return new Sale(new SaleProposed(saleId, productId, sellerId, sellerName,
                sellerSurname, purchaserId, purchaserName, purchaserSurname,
                price, title, pickupLocation, datetime));
    }

	/**
	 * Reconstructs a Sale from its event history.
	 *
	 * @param history the list of domain events
	 * @return a new Sale object reconstructed from the history
	 */
    public static Sale loadFromHistory(List<DomainEvent> history) {
        Sale sale = new Sale();
        history.forEach(sale::when);
        return sale;
    }

}
