package segundum.application.queries;

import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a query for fetching the sales of a seller.
 */
public class GetSalesBySellerQuery {

    /**
     * The identifier of the seller.
     */
    private final SellerId sellerId;

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
     * Constructs a new GetSalesBySellerQuery with the given values.
     *
     * @param sellerId   the identifier of the seller
     * @param status     the status used to filter the sales
     * @param pageNumber the page number to fetch
     * @param pageSize   the number of elements per page
     */
    public GetSalesBySellerQuery(SellerId sellerId, OrderStatus status, int pageNumber, int pageSize) {
        this.sellerId = sellerId;
        this.status = status;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /**
     * Returns the identifier of the seller.
     *
     * @return the identifier of the seller
     */
    public SellerId getSellerId() {
        return sellerId;
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
