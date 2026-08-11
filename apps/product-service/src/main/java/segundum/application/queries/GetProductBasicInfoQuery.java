package segundum.application.queries;

import segundum.domain.models.product.ProductId;

/**
 * Represents a query to obtain the basic information of a product.
 */
public class GetProductBasicInfoQuery {

    /**
     * The identifier of the product.
     */
    private final ProductId productId;

    /**
     * Constructs a new GetProductBasicInfoQuery with the given product identifier.
     *
     * @param productId the product identifier
     */
    public GetProductBasicInfoQuery(ProductId productId) {
        this.productId = productId;
    }

    /**
     * Returns the identifier of the product.
     *
     * @return the product identifier
     */
    public ProductId getProductId() { return productId; }

}
