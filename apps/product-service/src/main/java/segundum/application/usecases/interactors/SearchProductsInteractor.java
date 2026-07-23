package segundum.application.usecases.interactors;

import segundum.application.queries.SearchProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.SearchProductsUseCase;

public class SearchProductsInteractor implements SearchProductsUseCase {

	private final ProductReadRepository productReadRepository;

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
