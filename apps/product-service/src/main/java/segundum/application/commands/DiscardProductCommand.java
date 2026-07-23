package segundum.application.commands;

import segundum.domain.models.product.ProductId;

/**
 * Command to discard a draft product.
 */
public class DiscardProductCommand {

	private final ProductId productId;

	public DiscardProductCommand(ProductId productId) {
		this.productId = productId;
	}

	public ProductId getProductId() { return productId; }

}
