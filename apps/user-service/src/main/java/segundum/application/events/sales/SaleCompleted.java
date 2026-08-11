package segundum.application.events.sales;

import java.util.UUID;

/**
 * DTO representing the incoming event of a sale being completed.
 */
public class SaleCompleted {

	/**
	 * The unique identifier of the purchaser.
	 */
	private final UUID purchaserId;
	
	/**
	 * The unique identifier of the seller.
	 */
	private final UUID sellerId;

	/**
	 * Constructs a new SaleCompleted with the given parameters.
	 *
	 * @param purchaserId the unique identifier of the purchaser
	 * @param sellerId the unique identifier of the seller
	 */
	public SaleCompleted(UUID purchaserId, UUID sellerId) {
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
