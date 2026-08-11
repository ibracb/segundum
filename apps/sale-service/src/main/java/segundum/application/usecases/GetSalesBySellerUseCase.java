package segundum.application.usecases;

import segundum.application.queries.GetSalesBySellerQuery;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.readmodels.common.Page;

/**
 * Represents the use case for fetching the sales of a seller.
 */
public interface GetSalesBySellerUseCase {

    /**
     * Fetches the sales of a seller matching the given query.
     *
     * @param query the query containing the seller and pagination information
     * @return a page of sales as seen by the seller
     */
    Page<SaleAsSellerReadModel> execute(GetSalesBySellerQuery query);
}
