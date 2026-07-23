package segundum.application.usecases;

import segundum.application.queries.GetMonthlyHistoryQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSummary;

public interface GetMonthlyHistoryUseCase {

	Page<ProductSummary> execute(GetMonthlyHistoryQuery query);

}
