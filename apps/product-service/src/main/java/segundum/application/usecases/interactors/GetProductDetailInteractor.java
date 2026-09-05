package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.queries.GetProductDetailQuery;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.finders.ProductFinder;
import segundum.application.usecases.GetProductDetailUseCase;

/**
 * Represents the interactor for obtaining the detail of a product.
 */
public class GetProductDetailInteractor implements GetProductDetailUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductFinder productFinder;

	/**
	 * Constructs a new GetProductDetailInteractor with the given repository.
	 *
	 * @param productFinder the product read repository
	 */
	public GetProductDetailInteractor(ProductFinder productFinder) {
		this.productFinder = productFinder;
	}

	@Override
	public Optional<ProductDetail> execute(GetProductDetailQuery query) {
		return productFinder.findById(query.getProductId());
	}

}
