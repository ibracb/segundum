package segundum.infrastructure.messaging.messages;

/**
 * Represents the message sent when a product is updated.
 */
public class ProductUpdatedMessage extends DomainEventMessage {

	/**
	 * The identifier of the product.
	 */
	private final String productId;
	/**
	 * The updated price of the product.
	 */
	private final double price;
	/**
	 * The updated description of the product.
	 */
	private final String description;
	/**
	 * The updated condition status of the product.
	 */
	private final String conditionStatus;

	/**
	 * Constructs a new ProductUpdatedMessage with the given data.
	 *
	 * @param eventId the event identifier
	 * @param type the event type
	 * @param timestamp the event timestamp
	 * @param productId the product identifier
	 * @param price the product price
	 * @param description the product description
	 * @param conditionStatus the product condition status
	 */
	public ProductUpdatedMessage(String eventId, String type, String timestamp,
			String productId, double price, String description, String conditionStatus) {
		super(eventId, type, timestamp);
		this.productId = productId;
		this.price = price;
		this.description = description;
		this.conditionStatus = conditionStatus;
	}

	/**
	 * Returns the identifier of the product.
	 *
	 * @return the product identifier
	 */
	public String getProductId() { return productId; }
	/**
	 * Returns the price of the product.
	 *
	 * @return the product price
	 */
	public double getPrice() { return price; }
	/**
	 * Returns the description of the product.
	 *
	 * @return the product description
	 */
	public String getDescription() { return description; }
	/**
	 * Returns the condition status of the product.
	 *
	 * @return the product condition status
	 */
	public String getConditionStatus() { return conditionStatus; }

}
