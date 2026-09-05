package segundum.application.notifications.sales;

import java.util.UUID;

/**
 * DTO representing the incoming event of a reservation being cancelled.
 */
public class SaleCancelledNotification {

	/**
	 * The unique identifier of the product.
	 */
	private final UUID productId;

	/**
	 * Constructs a new ReservationCancelled with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 */
	public SaleCancelledNotification(UUID productId) {
		this.productId = productId;
	}

	/**
	 * Returns the unique identifier of the product.
	 *
	 * @return the unique identifier of the product
	 */
	public UUID getProductId() {
		return productId;
	}

}
