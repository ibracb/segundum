package segundum.application.usecases;

import segundum.application.queries.SearchProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSearchResult;

public interface SearchProductsUseCase {

	Page<ProductSearchResult> execute(SearchProductsQuery query);

}
