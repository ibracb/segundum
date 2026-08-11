package segundum.infrastructure.messaging.messages;

/**
 * Raw message representing a reservation created event.
 */
public class SaleReservedMessage extends DomainEventMessage {

	private final String saleId;

	private final String productId;

	public SaleReservedMessage(String eventId, String type, String timestamp,
			String saleId, String productId) {
		super(eventId, type, timestamp);
		this.saleId = saleId;
		this.productId = productId;
	}

	public String getSaleId() {
		return saleId;
	}

	public String getProductId() {
		return productId;
	}

}
