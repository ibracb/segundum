package segundum.application.usecases;

import segundum.application.queries.GetMonthlyHistoryQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSummary;

/**
 * Represents the use case to obtain the monthly history of products.
 */
public interface GetMonthlyHistoryUseCase {

	/**
	 * Executes the query to obtain the monthly history of products.
	 *
	 * @param query the monthly history query
	 * @return a page of product summaries
	 */
	Page<ProductSummary> execute(GetMonthlyHistoryQuery query);

}
