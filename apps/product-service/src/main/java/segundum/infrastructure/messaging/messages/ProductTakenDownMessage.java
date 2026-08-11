package segundum.infrastructure.messaging.messages;

/**
 * Represents the message sent when a product is taken down.
 */
public class ProductTakenDownMessage extends DomainEventMessage {

	/**
	 * The identifier of the product.
	 */
	private final String productId;

	/**
	 * Constructs a new ProductTakenDownMessage with the given data.
	 *
	 * @param eventId the event identifier
	 * @param type the event type
	 * @param timestamp the event timestamp
	 * @param productId the product identifier
	 */
	public ProductTakenDownMessage(String eventId, String type, String timestamp, String productId) {
		super(eventId, type, timestamp);
		this.productId = productId;
	}

	/**
	 * Returns the identifier of the product.
	 *
	 * @return the product identifier
	 */
	public String getProductId() { return productId; }

}
