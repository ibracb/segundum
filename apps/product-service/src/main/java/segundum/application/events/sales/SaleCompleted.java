package segundum.application.events.sales;

import java.util.UUID;

/**
 * DTO representing the incoming event of a sale being completed.
 */
public class SaleCompleted {

	/**
	 * The unique identifier of the product.
	 */
	private final UUID productId;

	/**
	 * Constructs a new SaleCompleted with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 */
	public SaleCompleted(UUID productId) {
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
