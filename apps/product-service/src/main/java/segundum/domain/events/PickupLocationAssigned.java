package segundum.domain.events;

import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.product.ProductId;

/**
 * Event published when a pickup location is assigned to a product.
 */
public class PickupLocationAssigned extends DomainEvent {

	/**
	 * The unique identifier of the product.
	 */
	private final ProductId productId;

	/**
	 * The pickup location assigned to the product.
	 */
	private final PickupLocation pickupLocation;

	/**
	 * Constructs a new PickupLocationAssigned event with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 * @param pickupLocation the pickup location assigned to the product
	 */
	public PickupLocationAssigned(ProductId productId, PickupLocation pickupLocation) {
		super();
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
	 * Returns the pickup location assigned to the product.
	 *
	 * @return the pickup location assigned to the product
	 */
	public PickupLocation getPickupLocation() {
		return pickupLocation;
	}

}
