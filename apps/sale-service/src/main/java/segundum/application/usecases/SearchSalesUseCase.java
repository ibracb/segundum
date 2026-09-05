package segundum.application.usecases;

import segundum.application.queries.SearchSalesQuery;
import segundum.application.readmodels.sale.SaleDetailReadModel;
import segundum.application.readmodels.common.Page;

/**
 * Represents the use case for searching sales with optional filters.
 */
public interface SearchSalesUseCase {

    /**
     * Searches sales matching the given query.
     *
     * @param query the query containing optional filters and pagination
     * @return a page of sales with full detail
     */
    Page<SaleDetailReadModel> execute(SearchSalesQuery query);
}
