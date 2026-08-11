package segundum.application.usecases;

import segundum.application.queries.SearchProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSearchResult;

/**
 * Represents the use case to search products.
 */
public interface SearchProductsUseCase {

	/**
	 * Executes the query to search products.
	 *
	 * @param query the search products query
	 * @return a page of product search results
	 */
	Page<ProductSearchResult> execute(SearchProductsQuery query);

}
