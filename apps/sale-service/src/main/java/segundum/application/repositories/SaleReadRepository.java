package segundum.application.repositories;

import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.readmodels.common.Page;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SellerId;

/**
 * Represents the repository for reading sales from the read store.
 */
public interface SaleReadRepository {

    /**
     * Finds the sales of a purchaser matching the given criteria.
     *
     * @param purchaserId the identifier of the purchaser
     * @param status      the status used to filter the sales
     * @param pageNumber  the page number to fetch
     * @param pageSize    the number of elements per page
     * @return a page of sales as seen by the purchaser
     */
    Page<SaleAsPurchaserReadModel> findByPurchaserId(PurchaserId purchaserId, OrderStatus status, int pageNumber, int pageSize);

    /**
     * Finds the sales of a seller matching the given criteria.
     *
     * @param sellerId   the identifier of the seller
     * @param status     the status used to filter the sales
     * @param pageNumber the page number to fetch
     * @param pageSize   the number of elements per page
     * @return a page of sales as seen by the seller
     */
    Page<SaleAsSellerReadModel> findBySellerId(SellerId sellerId, OrderStatus status, int pageNumber, int pageSize);

}
