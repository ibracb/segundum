package segundum.application.usecases.interactors;

import segundum.application.queries.GetSalesBySellerQuery;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.readmodels.common.Page;
import segundum.application.repositories.SaleReadRepository;
import segundum.application.usecases.GetSalesBySellerUseCase;

/**
 * Represents the interactor for fetching the sales of a seller.
 */
public class GetSalesBySellerInteractor implements GetSalesBySellerUseCase {

    /**
     * The repository for reading sales from the read store.
     */
    private final SaleReadRepository saleReadRepository;

    /**
     * Constructs a new GetSalesBySellerInteractor with the given dependency.
     *
     * @param saleReadRepository the repository for reading sales from the read store
     */
    public GetSalesBySellerInteractor(SaleReadRepository saleReadRepository) {
        this.saleReadRepository = saleReadRepository;
    }

    @Override
    public Page<SaleAsSellerReadModel> execute(GetSalesBySellerQuery query) {
        return saleReadRepository.findBySellerId(query.getSellerId(), query.getStatus(), query.getPageNumber(), query.getPageSize());
    }

}
