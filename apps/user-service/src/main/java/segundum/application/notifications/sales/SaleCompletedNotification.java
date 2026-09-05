package segundum.application.notifications.sales;

import java.util.UUID;

/**
 * DTO representing the incoming event of a sale being completed.
 */
public class SaleCompletedNotification {

	/**
	 * The unique identifier of the purchaser.
	 */
	private final UUID purchaserId;
	
	/**
	 * The unique identifier of the seller.
	 */
	private final UUID sellerId;

	/**
	 * Constructs a new SaleCompletedNotification with the given parameters.
	 *
	 * @param purchaserId the unique identifier of the purchaser
	 * @param sellerId the unique identifier of the seller
	 */
	public SaleCompletedNotification(UUID purchaserId, UUID sellerId) {
		this.purchaserId = purchaserId;
		this.sellerId = sellerId;
	}
	
	/**
	 * Returns the unique identifier of the purchaser.
	 *
	 * @return the unique identifier of the purchaser
	 */
	public UUID getPurchaserId() {
		return purchaserId;
	}

	/**
	 * Returns the unique identifier of the seller.
	 *
	 * @return the unique identifier of the seller
	 */
	public UUID getSellerId() {
		return sellerId;
	}

	

}
