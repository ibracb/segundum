package segundum.application.queries;

import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a query for searching sales with optional filters.
 */
public class SearchSalesQuery {

    private final PurchaserId purchaserId;
    private final SellerId sellerId;
    private final OrderStatus status;
    private final int pageNumber;
    private final int pageSize;

    /**
     * Constructs a new SearchSalesQuery with the given values.
     *
     * @param purchaserId the optional identifier of the purchaser
     * @param sellerId    the optional identifier of the seller
     * @param status      the optional status used to filter the sales
     * @param pageNumber  the page number to fetch
     * @param pageSize    the number of elements per page
     */
    public SearchSalesQuery(PurchaserId purchaserId, SellerId sellerId,
            OrderStatus status, int pageNumber, int pageSize) {
        this.purchaserId = purchaserId;
        this.sellerId = sellerId;
        this.status = status;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PurchaserId getPurchaserId() { return purchaserId; }
    public SellerId getSellerId() { return sellerId; }
    public OrderStatus getStatus() { return status; }
    public int getPageNumber() { return pageNumber; }
    public int getPageSize() { return pageSize; }

}
