package segundum.infrastructure.messaging.messages;

/**
 * Raw message representing a sale completed event.
 */
public class SaleCompletedMessage extends DomainEventMessage {

	private final String saleId;

	private final String productId;

	private final String sellerId;

	private final String purchaserId;

	/**
	 * Constructs a new SaleCompletedMessage with the given parameters.
	 *
	 * @param eventId the unique identifier of the event
	 * @param type the type of the event
	 * @param timestamp the timestamp of the event
	 * @param saleId the unique identifier of the sale
	 * @param productId the unique identifier of the product
	 * @param sellerId the unique identifier of the seller
	 * @param purchaserId the unique identifier of the purchaser
	 */
	public SaleCompletedMessage(String eventId, String type, String timestamp,
			String saleId, String productId, String sellerId, String purchaserId) {
		super(eventId, type, timestamp);
		this.saleId = saleId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.purchaserId = purchaserId;
	}

	public String getSaleId() {
		return saleId;
	}

	/**
	 * Returns the unique identifier of the product.
	 *
	 * @return the unique identifier of the product
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Returns the unique identifier of the seller.
	 *
	 * @return the unique identifier of the seller
	 */
	public String getSellerId() {
		return sellerId;
	}

	/**
	 * Returns the unique identifier of the purchaser.
	 *
	 * @return the unique identifier of the purchaser
	 */
	public String getPurchaserId() {
		return purchaserId;
	}

}
