package segundum.infrastructure.messaging.messages;

/**
 * Raw message representing a sale cancelled by the seller event.
 */
public class SaleCancelledBySellerMessage extends DomainEventMessage {

	private final String saleId;

	private final String productId;

	public SaleCancelledBySellerMessage(String eventId, String type, String timestamp,
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
