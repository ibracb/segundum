package segundum.application.commands;

import segundum.domain.models.product.ProductId;

/**
 * Command to take a product down from sale.
 */
public class TakeProductDownCommand {

	private final ProductId productId;

	public TakeProductDownCommand(ProductId productId) {
		this.productId = productId;
	}

	public ProductId getProductId() { return productId; }

}
