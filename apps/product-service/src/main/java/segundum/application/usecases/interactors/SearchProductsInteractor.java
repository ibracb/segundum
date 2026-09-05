package segundum.application.usecases.interactors;

import segundum.application.queries.SearchProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.finders.ProductFinder;
import segundum.application.usecases.SearchProductsUseCase;

/**
 * Represents the interactor for searching products.
 */
public class SearchProductsInteractor implements SearchProductsUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductFinder productFinder;

	/**
	 * Constructs a new SearchProductsInteractor with the given repository.
	 *
	 * @param productFinder the product read repository
	 */
	public SearchProductsInteractor(ProductFinder productFinder) {
		this.productFinder = productFinder;
	}

	@Override
	public Page<ProductSearchResult> execute(SearchProductsQuery query) {
		return productFinder.search(
				query.getCategoryId(),
				query.getDescriptionText(),
				query.getStatus(),
				query.getMaxPrice(),
				query.getPageNumber(),
				query.getPageSize());
	}

}
