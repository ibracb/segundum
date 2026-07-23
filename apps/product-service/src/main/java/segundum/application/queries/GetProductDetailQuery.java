package segundum.application.queries;

import segundum.domain.models.product.ProductId;

/**
 * Represents a query to retrieve product detail by ID.
 */
public class GetProductDetailQuery {

	private final ProductId productId;

	/**
	 * Constructs a new GetProductDetailQuery.
	 *
	 * @param productId the product identifier
	 */
	public GetProductDetailQuery(ProductId productId) {
		this.productId = productId;
	}

	public ProductId getProductId() { return productId; }

}
