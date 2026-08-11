package segundum.domain.models.product;

import segundum.domain.models.category.CategoryId;
import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.seller.SellerId;

/**
 * Factory for creating Product objects.
 */
public class ProductFactory {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private ProductFactory() {
	}

	/**
	 * Creates a new Product with the given parameters.
	 * The sale status is set to DRAFT.
	 * The views counter is initialized to 0.
	 * The pickup location is not set (use assignPickupLocation after creation).
	 *
	 * @param title the title of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param status the condition status of the product
	 * @param categoryId the category of the product
	 * @param shippingAvailable whether shipping is available for the product
	 * @param sellerId the seller of the product
	 * @return a new Product object
	 */
	public static Product create(Title title, Description description, Price price,
			ConditionStatus status, CategoryId categoryId, boolean shippingAvailable,
			SellerId sellerId) {
		return new Product(title, description, price, status, categoryId,
				shippingAvailable, sellerId);
	}

	/**
	 * Reconstitutes a Product from persistence.
	 *
	 * @param productId the unique identifier of the product
	 * @param title the title of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param publicationDate the publication date and time of the product
	 * @param status the condition status of the product
	 * @param saleStatus the sale status of the product
	 * @param categoryId the category of the product
	 * @param shippingAvailable whether shipping is available for the product
	 * @param pickupLocation the pickup location for the product (nullable)
	 * @param sellerId the seller of the product
	 * @param views the number of views for the product
	 * @return a reconstituted Product object
	 */
	public static Product reconstitute(ProductId productId, Title title, Description description,
			Price price, PublicationDate publicationDate, ConditionStatus status, SaleStatus saleStatus,
			CategoryId categoryId, boolean shippingAvailable, PickupLocation pickupLocation,
			SellerId sellerId, long views) {
		return new Product(productId, title, description, price, publicationDate, status,
				saleStatus, categoryId, shippingAvailable, pickupLocation, sellerId, views);
	}

}
