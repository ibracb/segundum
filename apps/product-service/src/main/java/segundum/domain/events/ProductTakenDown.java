package segundum.domain.events;

import segundum.domain.models.product.ProductId;

/**
 * Event published when a product is taken down from sale.
 */
public class ProductTakenDown extends DomainEvent {

	/**
	 * The unique identifier of the product.
	 */
	private final ProductId productId;

	/**
	 * Constructs a new ProductTakenDown event with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 */
	public ProductTakenDown(ProductId productId) {
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
