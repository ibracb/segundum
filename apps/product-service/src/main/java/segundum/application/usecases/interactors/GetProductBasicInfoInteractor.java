package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.queries.GetProductBasicInfoQuery;
import segundum.application.readmodels.product.ProductBasicInfo;
import segundum.application.finders.ProductFinder;
import segundum.application.usecases.GetProductBasicInfoUseCase;

/**
 * Represents the interactor for obtaining the basic information of a product.
 */
public class GetProductBasicInfoInteractor implements GetProductBasicInfoUseCase {

    /**
     * The repository used to read products.
     */
    private final ProductFinder productFinder;

    /**
     * Constructs a new GetProductBasicInfoInteractor with the given repository.
     *
     * @param productFinder the product read repository
     */
    public GetProductBasicInfoInteractor(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    @Override
    public Optional<ProductBasicInfo> execute(GetProductBasicInfoQuery query) {
        return productFinder.findBasicInfoById(query.getProductId());
    }

}
