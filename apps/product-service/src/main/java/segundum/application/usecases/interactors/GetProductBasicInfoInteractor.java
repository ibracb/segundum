package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.queries.GetProductBasicInfoQuery;
import segundum.application.readmodels.product.ProductBasicInfo;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetProductBasicInfoUseCase;

/**
 * Represents the interactor for obtaining the basic information of a product.
 */
public class GetProductBasicInfoInteractor implements GetProductBasicInfoUseCase {

    /**
     * The repository used to read products.
     */
    private final ProductReadRepository productReadRepository;

    /**
     * Constructs a new GetProductBasicInfoInteractor with the given repository.
     *
     * @param productReadRepository the product read repository
     */
    public GetProductBasicInfoInteractor(ProductReadRepository productReadRepository) {
        this.productReadRepository = productReadRepository;
    }

    @Override
    public Optional<ProductBasicInfo> execute(GetProductBasicInfoQuery query) {
        return productReadRepository.findBasicInfoById(query.getProductId());
    }

}
