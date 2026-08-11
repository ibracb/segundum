package segundum.application.usecases.interactors;

import segundum.application.queries.SearchProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.SearchProductsUseCase;

/**
 * Represents the interactor for searching products.
 */
public class SearchProductsInteractor implements SearchProductsUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductReadRepository productReadRepository;

	/**
	 * Constructs a new SearchProductsInteractor with the given repository.
	 *
	 * @param productReadRepository the product read repository
	 */
	public SearchProductsInteractor(ProductReadRepository productReadRepository) {
		this.productReadRepository = productReadRepository;
	}

	@Override
	public Page<ProductSearchResult> execute(SearchProductsQuery query) {
		return productReadRepository.search(
				query.getCategoryId(),
				query.getDescriptionText(),
				query.getStatus(),
				query.getMaxPrice(),
				query.getPageNumber(),
				query.getPageSize());
	}

}
