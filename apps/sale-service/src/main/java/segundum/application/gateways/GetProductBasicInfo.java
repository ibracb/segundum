package segundum.application.gateways;

import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.domain.models.sale.ProductId;

/**
 * Represents a gateway for fetching basic product information.
 */
public interface GetProductBasicInfo {

    /**
     * Fetches the basic information of a product.
     *
     * @param productId the identifier of the product
     * @return the basic information of the product
     */
    ProductBasicInfoReadModel fetch(ProductId productId);
}
