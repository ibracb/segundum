package segundum.domain.events;

import segundum.domain.models.product.ProductId;

/**
 * Event published when a product is sold.
 */
public class ProductSold extends DomainEvent {

	/**
	 * The unique identifier of the product.
	 */
	private final ProductId productId;

	/**
	 * Constructs a new ProductSold event with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 */
	public ProductSold(ProductId productId) {
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
