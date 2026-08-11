package segundum.application.readmodels.product;

import java.time.Instant;

/**
 * Represents the detailed information of a product.
 */
public class ProductDetail {

	/**
	 * The unique identifier of the product.
	 */
	private final String productId;
	/**
	 * The title of the product.
	 */
	private final String title;
	/**
	 * The description of the product.
	 */
	private final String description;
	/**
	 * The price of the product.
	 */
	private final Double price;
	/**
	 * The publication date of the product.
	 */
	private final Instant publicationDate;
	/**
	 * The condition status of the product.
	 */
	private final String conditionStatus;
	/**
	 * The name of the category the product belongs to.
	 */
	private final String categoryName;
	/**
	 * Whether shipping is available for the product.
	 */
	private final boolean shippingAvailable;
	/**
	 * The pickup location of the product.
	 */
	private final PickupLocationReadModel pickupLocation;
	/**
	 * The unique identifier of the seller.
	 */
	private final String sellerId;
	/**
	 * The number of views of the product.
	 */
	private final long views;

	/**
	 * Constructs a new ProductDetail with the given values.
	 *
	 * @param productId the product identifier
	 * @param title the product title
	 * @param description the product description
	 * @param price the product price
	 * @param publicationDate the publication date
	 * @param conditionStatus the condition status
	 * @param categoryName the category name
	 * @param shippingAvailable whether shipping is available
	 * @param pickupLocation the pickup location
	 * @param sellerId the seller identifier
	 * @param views the number of views
	 */
	public ProductDetail(String productId, String title, String description, Double price,
			Instant publicationDate, String conditionStatus, String categoryName,
			boolean shippingAvailable, PickupLocationReadModel pickupLocation, String sellerId, long views) {
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = publicationDate;
		this.conditionStatus = conditionStatus;
		this.categoryName = categoryName;
		this.shippingAvailable = shippingAvailable;
		this.pickupLocation = pickupLocation;
		this.sellerId = sellerId;
		this.views = views;
	}

	/**
	 * Returns the product identifier.
	 *
	 * @return the product identifier
	 */
	public String getProductId() { return productId; }
	/**
	 * Returns the product title.
	 *
	 * @return the product title
	 */
	public String getTitle() { return title; }
	/**
	 * Returns the product description.
	 *
	 * @return the product description
	 */
	public String getDescription() { return description; }
	/**
	 * Returns the product price.
	 *
	 * @return the product price
	 */
	public Double getPrice() { return price; }
	/**
	 * Returns the publication date.
	 *
	 * @return the publication date
	 */
	public Instant getPublicationDate() { return publicationDate; }
	/**
	 * Returns the condition status.
	 *
	 * @return the condition status
	 */
	public String getConditionStatus() { return conditionStatus; }
	/**
	 * Returns the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() { return categoryName; }
	/**
	 * Returns whether shipping is available.
	 *
	 * @return true if shipping is available, false otherwise
	 */
	public boolean isShippingAvailable() { return shippingAvailable; }
	/**
	 * Returns the pickup location.
	 *
	 * @return the pickup location
	 */
	public PickupLocationReadModel getPickupLocation() { return pickupLocation; }
	/**
	 * Returns the seller identifier.
	 *
	 * @return the seller identifier
	 */
	public String getSellerId() { return sellerId; }
	/**
	 * Returns the number of views.
	 *
	 * @return the number of views
	 */
	public long getViews() { return views; }

}
