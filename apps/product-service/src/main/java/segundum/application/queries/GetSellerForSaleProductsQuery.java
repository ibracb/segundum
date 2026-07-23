package segundum.application.queries;

import segundum.domain.models.seller.SellerId;

/**
 * Represents a query to retrieve for-sale products for a seller.
 */
public class GetSellerForSaleProductsQuery {

	private final SellerId sellerId;
	private final int pageNumber;
	private final int pageSize;

	/**
	 * Constructs a new GetSellerForSaleProductsQuery.
	 *
	 * @param sellerId   the seller identifier
	 * @param pageNumber the page number (0-indexed)
	 * @param pageSize   the page size
	 */
	public GetSellerForSaleProductsQuery(SellerId sellerId, int pageNumber, int pageSize) {
		this.sellerId = sellerId;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public SellerId getSellerId() { return sellerId; }
	public int getPageNumber() { return pageNumber; }
	public int getPageSize() { return pageSize; }

}
