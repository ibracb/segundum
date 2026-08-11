package segundum.infrastructure.persistence.fakes;

import java.util.List;

import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.repositories.SaleReadRepository;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SellerId;

/**
 * In-memory implementation of the SaleReadRepository for testing.
 */
public class FakeSaleReadRepository implements SaleReadRepository {

	private Page<SaleAsSellerReadModel> sellerPage = new Page<>(List.of(), 0, 0, 20);
	private Page<SaleAsPurchaserReadModel> purchaserPage = new Page<>(List.of(), 0, 0, 20);

	private SellerId lastSellerId;
	private PurchaserId lastPurchaserId;
	private OrderStatus lastStatus;
	private int lastPageNumber;
	private int lastPageSize;

	@Override
	public Page<SaleAsPurchaserReadModel> findByPurchaserId(PurchaserId purchaserId, OrderStatus status,
			int pageNumber, int pageSize) {
		this.lastPurchaserId = purchaserId;
		this.lastStatus = status;
		this.lastPageNumber = pageNumber;
		this.lastPageSize = pageSize;
		return purchaserPage;
	}

	@Override
	public Page<SaleAsSellerReadModel> findBySellerId(SellerId sellerId, OrderStatus status,
			int pageNumber, int pageSize) {
		this.lastSellerId = sellerId;
		this.lastStatus = status;
		this.lastPageNumber = pageNumber;
		this.lastPageSize = pageSize;
		return sellerPage;
	}

	public void setSellerPage(Page<SaleAsSellerReadModel> sellerPage) {
		this.sellerPage = sellerPage;
	}

	public void setPurchaserPage(Page<SaleAsPurchaserReadModel> purchaserPage) {
		this.purchaserPage = purchaserPage;
	}

	public SellerId getLastSellerId() {
		return lastSellerId;
	}

	public PurchaserId getLastPurchaserId() {
		return lastPurchaserId;
	}

	public OrderStatus getLastStatus() {
		return lastStatus;
	}

	public int getLastPageNumber() {
		return lastPageNumber;
	}

	public int getLastPageSize() {
		return lastPageSize;
	}

}
