package segundum.infrastructure.persistence.mongodb.sale;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Denormalized read-side representation of a sale, stored in MongoDB.
 */
@Document(collection = "sales")
@CompoundIndexes({
		@CompoundIndex(name = "seller_status", def = "{'seller_id': 1, 'status': 1}"),
		@CompoundIndex(name = "purchaser_status", def = "{'purchaser_id': 1, 'status': 1}"),
		@CompoundIndex(name = "purchaser_seller_status", def = "{'purchaser_id': 1, 'seller_id': 1, 'status': 1}")
})
public class SaleReadDocument {

	@Id
	private String id;

	@Field("status")
	private String status;

	@Field("datetime")
	private String datetime;

	@Field("product")
	private SaleProductDocument product;

	@Field("seller_id")
	@Indexed
	private String sellerId;

	@Field("purchaser_id")
	@Indexed
	private String purchaserId;

	@Field("seller")
	private SaleSellerDocument seller;

	@Field("purchaser")
	private SalePurchaserDocument purchaser;

	public SaleReadDocument() {
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public String getDatetime() { return datetime; }
	public void setDatetime(String datetime) { this.datetime = datetime; }
	public SaleProductDocument getProduct() { return product; }
	public void setProduct(SaleProductDocument product) { this.product = product; }
	public String getSellerId() { return sellerId; }
	public void setSellerId(String sellerId) { this.sellerId = sellerId; }
	public String getPurchaserId() { return purchaserId; }
	public void setPurchaserId(String purchaserId) { this.purchaserId = purchaserId; }
	public SaleSellerDocument getSeller() { return seller; }
	public void setSeller(SaleSellerDocument seller) { this.seller = seller; }
	public SalePurchaserDocument getPurchaser() { return purchaser; }
	public void setPurchaser(SalePurchaserDocument purchaser) { this.purchaser = purchaser; }

}
