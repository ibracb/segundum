package segundum.infrastructure.persistence.mongodb.sale;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Denormalized product information embedded in a sale document.
 */
public class SaleProductDocument {

	@Field("product_id")
	private String productId;

	@Field("title")
	private String title;

	@Field("price")
	private Double price;

	@Field("pickup_location")
	private SalePickupLocationDocument pickupLocation;

	@Field("seller_id")
	private String sellerId;

	public SaleProductDocument() {
	}

	public String getProductId() { return productId; }
	public void setProductId(String productId) { this.productId = productId; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public Double getPrice() { return price; }
	public void setPrice(Double price) { this.price = price; }
	public SalePickupLocationDocument getPickupLocation() { return pickupLocation; }
	public void setPickupLocation(SalePickupLocationDocument pickupLocation) { this.pickupLocation = pickupLocation; }
	public String getSellerId() { return sellerId; }
	public void setSellerId(String sellerId) { this.sellerId = sellerId; }

}
