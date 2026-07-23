package segundum.application.commands;

import segundum.domain.models.product.ProductId;

/**
 * Command to put a product on sale.
 */
public class PutProductForSaleCommand {

	private final ProductId productId;

	public PutProductForSaleCommand(ProductId productId) {
		this.productId = productId;
	}

	public ProductId getProductId() { return productId; }

}
