package segundum.infrastructure.messaging.messages;

/**
 * Represents the message sent when a product is created.
 */
public class ProductCreatedMessage extends DomainEventMessage {

	/**
	 * The identifier of the product.
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
	private final double price;
	/**
	 * The publication date of the product.
	 */
	private final String publicationDate;
	/**
	 * The condition status of the product.
	 */
	private final String conditionStatus;
	/**
	 * The category identifier of the product.
	 */
	private final String categoryId;
	/**
	 * Whether shipping is available for the product.
	 */
	private final boolean shippingAvailable;
	/**
	 * The seller identifier of the product.
	 */
	private final String sellerId;

	/**
	 * Constructs a new ProductCreatedMessage with the given data.
	 *
	 * @param eventId the event identifier
	 * @param type the event type
	 * @param timestamp the event timestamp
	 * @param productId the product identifier
	 * @param title the product title
	 * @param description the product description
	 * @param price the product price
	 * @param publicationDate the product publication date
	 * @param conditionStatus the product condition status
	 * @param categoryId the product category identifier
	 * @param shippingAvailable whether shipping is available
	 * @param sellerId the seller identifier
	 */
	public ProductCreatedMessage(String eventId, String type, String timestamp,
			String productId, String title, String description, double price,
			String publicationDate, String conditionStatus, String categoryId,
			boolean shippingAvailable, String sellerId) {
		super(eventId, type, timestamp);
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = publicationDate;
		this.conditionStatus = conditionStatus;
		this.categoryId = categoryId;
		this.shippingAvailable = shippingAvailable;
		this.sellerId = sellerId;
	}

	/**
	 * Returns the identifier of the product.
	 *
	 * @return the product identifier
	 */
	public String getProductId() { return productId; }
	/**
	 * Returns the title of the product.
	 *
	 * @return the product title
	 */
	public String getTitle() { return title; }
	/**
	 * Returns the description of the product.
	 *
	 * @return the product description
	 */
	public String getDescription() { return description; }
	/**
	 * Returns the price of the product.
	 *
	 * @return the product price
	 */
	public double getPrice() { return price; }
	/**
	 * Returns the publication date of the product.
	 *
	 * @return the product publication date
	 */
	public String getPublicationDate() { return publicationDate; }
	/**
	 * Returns the condition status of the product.
	 *
	 * @return the product condition status
	 */
	public String getConditionStatus() { return conditionStatus; }
	/**
	 * Returns the category identifier of the product.
	 *
	 * @return the product category identifier
	 */
	public String getCategoryId() { return categoryId; }
	/**
	 * Returns whether shipping is available for the product.
	 *
	 * @return true if shipping is available, false otherwise
	 */
	public boolean isShippingAvailable() { return shippingAvailable; }
	/**
	 * Returns the seller identifier of the product.
	 *
	 * @return the seller identifier
	 */
	public String getSellerId() { return sellerId; }

}
