package segundum.application.usecases.interactors;

import segundum.application.queries.GetSalesByPurchaserQuery;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.common.Page;
import segundum.application.finders.SaleFinder;
import segundum.application.usecases.GetSalesByPurchaserUseCase;

/**
 * Represents the interactor for fetching the sales of a purchaser.
 */
public class GetSalesByPurchaserInteractor implements GetSalesByPurchaserUseCase {

    /**
     * The repository for reading sales from the read store.
     */
    private final SaleFinder saleFinder;

    /**
     * Constructs a new GetSalesByPurchaserInteractor with the given dependency.
     *
     * @param saleFinder the repository for reading sales from the read store
     */
    public GetSalesByPurchaserInteractor(SaleFinder saleFinder) {
        this.saleFinder = saleFinder;
    }

    @Override
    public Page<SaleAsPurchaserReadModel> execute(GetSalesByPurchaserQuery query) {
        return saleFinder.findByPurchaserId(query.getPurchaserId(), query.getStatus(), query.getPageNumber(), query.getPageSize());
    }

}
