package segundum.application.usecases;

import segundum.application.queries.GetSalesByPurchaserQuery;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.common.Page;

/**
 * Represents the use case for fetching the sales of a purchaser.
 */
public interface GetSalesByPurchaserUseCase {

    /**
     * Fetches the sales of a purchaser matching the given query.
     *
     * @param query the query containing the purchaser and pagination information
     * @return a page of sales as seen by the purchaser
     */
    Page<SaleAsPurchaserReadModel> execute(GetSalesByPurchaserQuery query);
}
