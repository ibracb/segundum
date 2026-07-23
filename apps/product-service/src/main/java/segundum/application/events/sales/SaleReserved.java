package segundum.application.events.sales;

import java.util.UUID;

/**
 * DTO representing the incoming event of a reservation being created.
 */
public class SaleReserved {

	/**
	 * The unique identifier of the product.
	 */
	private final UUID productId;

	/**
	 * Constructs a new ReservationCreated with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 */
	public SaleReserved(UUID productId) {
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
