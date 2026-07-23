package segundum.infrastructure.persistence.mongodb.product;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MongoDB read model document for products.
 */
@Document(collection = "products")
public class ProductReadDocument {

	@Id
	private String productId;

	@Field("title")
	private String title;

	@Field("description")
	private String description;

	@Field("price")
	private Double price;

	@Field("publication_date")
	private Instant publicationDate;

	@Field("condition_status")
	private String conditionStatus;

	@Field("sale_status")
	private String saleStatus;

	@Field("category_id")
	private String categoryId;

	@Field("category_name")
	private String categoryName;

	@Field("shipping_available")
	private boolean shippingAvailable;

	@Field("pickup_location")
	private PickupLocationDocument pickupLocation;

	@Field("seller_id")
	private String sellerId;

	@Field("views")
	private long views;

	public ProductReadDocument() {
	}

	public ProductReadDocument(String productId, String title, String description,
			Double price, Instant publicationDate, String conditionStatus,
			String saleStatus, String categoryId, String categoryName,
			boolean shippingAvailable, PickupLocationDocument pickupLocation,
			String sellerId, long views) {
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = publicationDate;
		this.conditionStatus = conditionStatus;
		this.saleStatus = saleStatus;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.shippingAvailable = shippingAvailable;
		this.pickupLocation = pickupLocation;
		this.sellerId = sellerId;
		this.views = views;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Instant getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Instant publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getConditionStatus() {
		return conditionStatus;
	}

	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isShippingAvailable() {
		return shippingAvailable;
	}

	public void setShippingAvailable(boolean shippingAvailable) {
		this.shippingAvailable = shippingAvailable;
	}

	public PickupLocationDocument getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(PickupLocationDocument pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

}
