package segundum.application.usecases.interactors;

import segundum.application.queries.SearchSalesQuery;
import segundum.application.readmodels.sale.SaleDetailReadModel;
import segundum.application.readmodels.common.Page;
import segundum.application.finders.SaleFinder;
import segundum.application.usecases.SearchSalesUseCase;

/**
 * Represents the interactor for searching sales with optional filters.
 */
public class SearchSalesInteractor implements SearchSalesUseCase {

    private final SaleFinder saleFinder;

    /**
     * Constructs a new SearchSalesInteractor with the given dependency.
     *
     * @param saleFinder the repository for reading sales from the read store
     */
    public SearchSalesInteractor(SaleFinder saleFinder) {
        this.saleFinder = saleFinder;
    }

    @Override
    public Page<SaleDetailReadModel> execute(SearchSalesQuery query) {
        return saleFinder.searchSales(
                query.getPurchaserId(), query.getSellerId(),
                query.getStatus(), query.getPageNumber(), query.getPageSize());
    }

}
