package segundum.infrastructure.rest.product.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detailed product information")
public class ProductDetailResponse {

	@Schema(description = "Product identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String productId;
	@Schema(description = "Product title", example = "iPhone 12")
	private String title;
	@Schema(description = "Product description", example = "Excellent condition")
	private String description;
	@Schema(description = "Product price", example = "299.99")
	private Double price;
	@Schema(description = "Publication date", example = "2024-01-15")
	private String publicationDate;
	@Schema(description = "Condition status", example = "GOOD")
	private String conditionStatus;
	@Schema(description = "Category name", example = "Electronics")
	private String categoryName;
	@Schema(description = "Whether shipping is available", example = "true")
	private boolean shippingAvailable;
	@Schema(description = "Pickup location details")
	private PickupLocationResponse pickupLocation;
	@Schema(description = "Seller identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String sellerId;
	@Schema(description = "Number of views", example = "150")
	private long views;

	public ProductDetailResponse() {
	}

	public ProductDetailResponse(String productId, String title, String description, Double price,
			String publicationDate, String conditionStatus, String categoryName,
			boolean shippingAvailable, PickupLocationResponse pickupLocation, String sellerId, long views) {
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = publicationDate;
		this.conditionStatus = conditionStatus;
		this.categoryName = categoryName;
		this.shippingAvailable = shippingAvailable;
		this.pickupLocation = pickupLocation;
		this.sellerId = sellerId;
		this.views = views;
	}

	public String getProductId() { return productId; }
	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public Double getPrice() { return price; }
	public String getPublicationDate() { return publicationDate; }
	public String getConditionStatus() { return conditionStatus; }
	public String getCategoryName() { return categoryName; }
	public boolean isShippingAvailable() { return shippingAvailable; }
	public PickupLocationResponse getPickupLocation() { return pickupLocation; }
	public String getSellerId() { return sellerId; }
	public long getViews() { return views; }

	public void setProductId(String productId) { this.productId = productId; }
	public void setTitle(String title) { this.title = title; }
	public void setDescription(String description) { this.description = description; }
	public void setPrice(Double price) { this.price = price; }
	public void setPublicationDate(String publicationDate) { this.publicationDate = publicationDate; }
	public void setConditionStatus(String conditionStatus) { this.conditionStatus = conditionStatus; }
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
	public void setShippingAvailable(boolean shippingAvailable) { this.shippingAvailable = shippingAvailable; }
	public void setPickupLocation(PickupLocationResponse pickupLocation) { this.pickupLocation = pickupLocation; }
	public void setSellerId(String sellerId) { this.sellerId = sellerId; }
	public void setViews(long views) { this.views = views; }

}
