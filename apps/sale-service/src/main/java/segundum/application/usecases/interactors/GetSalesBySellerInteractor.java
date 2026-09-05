package segundum.application.usecases.interactors;

import segundum.application.queries.GetSalesBySellerQuery;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.readmodels.common.Page;
import segundum.application.finders.SaleFinder;
import segundum.application.usecases.GetSalesBySellerUseCase;

/**
 * Represents the interactor for fetching the sales of a seller.
 */
public class GetSalesBySellerInteractor implements GetSalesBySellerUseCase {

    /**
     * The repository for reading sales from the read store.
     */
    private final SaleFinder saleFinder;

    /**
     * Constructs a new GetSalesBySellerInteractor with the given dependency.
     *
     * @param saleFinder the repository for reading sales from the read store
     */
    public GetSalesBySellerInteractor(SaleFinder saleFinder) {
        this.saleFinder = saleFinder;
    }

    @Override
    public Page<SaleAsSellerReadModel> execute(GetSalesBySellerQuery query) {
        return saleFinder.findBySellerId(query.getSellerId(), query.getStatus(), query.getPageNumber(), query.getPageSize());
    }

}
