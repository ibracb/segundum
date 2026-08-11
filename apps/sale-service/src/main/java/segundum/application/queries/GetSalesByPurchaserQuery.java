package segundum.application.queries;

import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;

/**
 * Represents a query for fetching the sales of a purchaser.
 */
public class GetSalesByPurchaserQuery {

    /**
     * The identifier of the purchaser.
     */
    private final PurchaserId purchaserId;

    /**
     * The status used to filter the sales.
     */
    private final OrderStatus status;

    /**
     * The page number to fetch.
     */
    private final int pageNumber;

    /**
     * The number of elements per page.
     */
    private final int pageSize;

    /**
     * Constructs a new GetSalesByPurchaserQuery with the given values.
     *
     * @param purchaserId the identifier of the purchaser
     * @param status      the status used to filter the sales
     * @param pageNumber  the page number to fetch
     * @param pageSize    the number of elements per page
     */
    public GetSalesByPurchaserQuery(PurchaserId purchaserId, OrderStatus status, int pageNumber, int pageSize) {
        this.purchaserId = purchaserId;
        this.status = status;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /**
     * Returns the identifier of the purchaser.
     *
     * @return the identifier of the purchaser
     */
    public PurchaserId getPurchaserId() {
        return purchaserId;
    }

    /**
     * Returns the status used to filter the sales.
     *
     * @return the status used to filter the sales
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Returns the page number to fetch.
     *
     * @return the page number to fetch
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Returns the number of elements per page.
     *
     * @return the number of elements per page
     */
    public int getPageSize() {
        return pageSize;
    }

}
