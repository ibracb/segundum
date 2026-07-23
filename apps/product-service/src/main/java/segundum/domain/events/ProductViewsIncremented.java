package segundum.domain.events;

import segundum.domain.models.product.ProductId;

/**
 * Event published when the views of a product are incremented.
 */
public class ProductViewsIncremented extends DomainEvent {

	/**
	 * The unique identifier of the product.
	 */
	private final ProductId productId;

	/**
	 * Constructs a new ProductViewsIncremented event with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 */
	public ProductViewsIncremented(ProductId productId) {
		super();
		this.productId = productId;
	}

	/**
	 * Returns the unique identifier of the product.
	 *
	 * @return the unique identifier of the product
	 */
	public ProductId getProductId() {
		return productId;
	}

}
