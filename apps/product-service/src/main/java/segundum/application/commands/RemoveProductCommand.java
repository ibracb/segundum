package segundum.application.commands;

import segundum.domain.models.product.ProductId;

/**
 * Command to permanently remove a product that is for sale.
 */
public class RemoveProductCommand {

	private final ProductId productId;

	public RemoveProductCommand(ProductId productId) {
		this.productId = productId;
	}

	public ProductId getProductId() { return productId; }

}
