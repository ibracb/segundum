package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.queries.GetProductDetailQuery;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetProductDetailUseCase;

/**
 * Represents the interactor for obtaining the detail of a product.
 */
public class GetProductDetailInteractor implements GetProductDetailUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductReadRepository productReadRepository;

	/**
	 * Constructs a new GetProductDetailInteractor with the given repository.
	 *
	 * @param productReadRepository the product read repository
	 */
	public GetProductDetailInteractor(ProductReadRepository productReadRepository) {
		this.productReadRepository = productReadRepository;
	}

	@Override
	public Optional<ProductDetail> execute(GetProductDetailQuery query) {
		return productReadRepository.findById(query.getProductId());
	}

}
