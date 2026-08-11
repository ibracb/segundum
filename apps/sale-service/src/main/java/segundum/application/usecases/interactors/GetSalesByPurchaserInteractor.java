package segundum.application.usecases.interactors;

import segundum.application.queries.GetSalesByPurchaserQuery;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.common.Page;
import segundum.application.repositories.SaleReadRepository;
import segundum.application.usecases.GetSalesByPurchaserUseCase;

/**
 * Represents the interactor for fetching the sales of a purchaser.
 */
public class GetSalesByPurchaserInteractor implements GetSalesByPurchaserUseCase {

    /**
     * The repository for reading sales from the read store.
     */
    private final SaleReadRepository saleReadRepository;

    /**
     * Constructs a new GetSalesByPurchaserInteractor with the given dependency.
     *
     * @param saleReadRepository the repository for reading sales from the read store
     */
    public GetSalesByPurchaserInteractor(SaleReadRepository saleReadRepository) {
        this.saleReadRepository = saleReadRepository;
    }

    @Override
    public Page<SaleAsPurchaserReadModel> execute(GetSalesByPurchaserQuery query) {
        return saleReadRepository.findByPurchaserId(query.getPurchaserId(), query.getStatus(), query.getPageNumber(), query.getPageSize());
    }

}
