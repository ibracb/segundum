package segundum.application.commands;

import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.product.ProductId;

/**
 * Represents a command to assign a pickup location to a product.
 */
public class AssignProductPickupLocationCommand {

	/**
	 * The unique identifier of the product.
	 */
	private final ProductId productId;

	/**
	 * The pickup location to assign.
	 */
	private final PickupLocation pickupLocation;

	/**
	 * Constructs a new AssignProductPickupLocationCommand with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 * @param pickupLocation the pickup location to assign
	 */
	public AssignProductPickupLocationCommand(ProductId productId, PickupLocation pickupLocation) {
		this.productId = productId;
		this.pickupLocation = pickupLocation;
	}

	/**
	 * Returns the unique identifier of the product.
	 *
	 * @return the unique identifier of the product
	 */
	public ProductId getProductId() {
		return productId;
	}

	/**
	 * Returns the pickup location to assign.
	 *
	 * @return the pickup location to assign
	 */
	public PickupLocation getPickupLocation() {
		return pickupLocation;
	}

}
