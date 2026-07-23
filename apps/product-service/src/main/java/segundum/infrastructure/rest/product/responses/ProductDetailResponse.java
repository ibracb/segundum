package segundum.infrastructure.rest.product.responses;

public class ProductDetailResponse {

	private String productId;
	private String title;
	private String description;
	private Double price;
	private String publicationDate;
	private String conditionStatus;
	private String categoryName;
	private boolean shippingAvailable;
	private PickupLocationResponse pickupLocation;
	private String sellerId;
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
