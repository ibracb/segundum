package segundum.application.commands;

import segundum.domain.models.product.ProductId;

/**
 * Represents a command to increment the views of a product.
 */
public class IncrementProductViewsCommand {

	/**
	 * The unique identifier of the product.
	 */
	private final ProductId productId;

	/**
	 * Constructs a new IncrementProductViewsCommand with the given product identifier.
	 *
	 * @param productId the unique identifier of the product
	 */
	public IncrementProductViewsCommand(ProductId productId) {
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
