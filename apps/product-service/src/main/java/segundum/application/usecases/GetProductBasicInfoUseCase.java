package segundum.application.usecases;

import java.util.Optional;

import segundum.application.queries.GetProductBasicInfoQuery;
import segundum.application.readmodels.product.ProductBasicInfo;

/**
 * Represents the use case to obtain the basic information of a product.
 */
public interface GetProductBasicInfoUseCase {

    /**
     * Executes the query to obtain the basic information of a product.
     *
     * @param query the product basic info query
     * @return the basic information of the product, if it exists
     */
    Optional<ProductBasicInfo> execute(GetProductBasicInfoQuery query);

}
