package segundum.infrastructure.rest.product.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the response with detailed product information.
 */
@Schema(description = "Detailed product information")
public class ProductDetailResponse {

	/**
	 * The product identifier.
	 */
	@Schema(description = "Product identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String productId;
	/**
	 * The product title.
	 */
	@Schema(description = "Product title", example = "iPhone 12")
	private String title;
	/**
	 * The product description.
	 */
	@Schema(description = "Product description", example = "Excellent condition")
	private String description;
	/**
	 * The product price.
	 */
	@Schema(description = "Product price", example = "299.99")
	private Double price;
	/**
	 * The publication date.
	 */
	@Schema(description = "Publication date", example = "2024-01-15")
	private String publicationDate;
	/**
	 * The condition status.
	 */
	@Schema(description = "Condition status", example = "GOOD")
	private String conditionStatus;
	/**
	 * The category name.
	 */
	@Schema(description = "Category name", example = "Electronics")
	private String categoryName;
	/**
	 * Whether shipping is available.
	 */
	@Schema(description = "Whether shipping is available", example = "true")
	private boolean shippingAvailable;
	/**
	 * The pickup location details.
	 */
	@Schema(description = "Pickup location details")
	private PickupLocationResponse pickupLocation;
	/**
	 * The seller identifier.
	 */
	@Schema(description = "Seller identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String sellerId;
	/**
	 * The number of views.
	 */
	@Schema(description = "Number of views", example = "150")
	private long views;

	/**
	 * Constructs a new ProductDetailResponse with no arguments.
	 */
	public ProductDetailResponse() {
	}

	/**
	 * Constructs a new ProductDetailResponse with the given values.
	 *
	 * @param productId the product identifier
	 * @param title the product title
	 * @param description the product description
	 * @param price the product price
	 * @param publicationDate the publication date
	 * @param conditionStatus the condition status
	 * @param categoryName the category name
	 * @param shippingAvailable whether shipping is available
	 * @param pickupLocation the pickup location details
	 * @param sellerId the seller identifier
	 * @param views the number of views
	 */
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

	/**
	 * Returns the product identifier.
	 *
	 * @return the product identifier
	 */
	public String getProductId() { return productId; }
	/**
	 * Returns the product title.
	 *
	 * @return the product title
	 */
	public String getTitle() { return title; }
	/**
	 * Returns the product description.
	 *
	 * @return the product description
	 */
	public String getDescription() { return description; }
	/**
	 * Returns the product price.
	 *
	 * @return the product price
	 */
	public Double getPrice() { return price; }
	/**
	 * Returns the publication date.
	 *
	 * @return the publication date
	 */
	public String getPublicationDate() { return publicationDate; }
	/**
	 * Returns the condition status.
	 *
	 * @return the condition status
	 */
	public String getConditionStatus() { return conditionStatus; }
	/**
	 * Returns the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() { return categoryName; }
	/**
	 * Returns whether shipping is available.
	 *
	 * @return true if shipping is available, false otherwise
	 */
	public boolean isShippingAvailable() { return shippingAvailable; }
	/**
	 * Returns the pickup location details.
	 *
	 * @return the pickup location details
	 */
	public PickupLocationResponse getPickupLocation() { return pickupLocation; }
	/**
	 * Returns the seller identifier.
	 *
	 * @return the seller identifier
	 */
	public String getSellerId() { return sellerId; }
	/**
	 * Returns the number of views.
	 *
	 * @return the number of views
	 */
	public long getViews() { return views; }

	/**
	 * Sets the product identifier.
	 *
	 * @param productId the product identifier
	 */
	public void setProductId(String productId) { this.productId = productId; }
	/**
	 * Sets the product title.
	 *
	 * @param title the product title
	 */
	public void setTitle(String title) { this.title = title; }
	/**
	 * Sets the product description.
	 *
	 * @param description the product description
	 */
	public void setDescription(String description) { this.description = description; }
	/**
	 * Sets the product price.
	 *
	 * @param price the product price
	 */
	public void setPrice(Double price) { this.price = price; }
	/**
	 * Sets the publication date.
	 *
	 * @param publicationDate the publication date
	 */
	public void setPublicationDate(String publicationDate) { this.publicationDate = publicationDate; }
	/**
	 * Sets the condition status.
	 *
	 * @param conditionStatus the condition status
	 */
	public void setConditionStatus(String conditionStatus) { this.conditionStatus = conditionStatus; }
	/**
	 * Sets the category name.
	 *
	 * @param categoryName the category name
	 */
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
	/**
	 * Sets whether shipping is available.
	 *
	 * @param shippingAvailable whether shipping is available
	 */
	public void setShippingAvailable(boolean shippingAvailable) { this.shippingAvailable = shippingAvailable; }
	/**
	 * Sets the pickup location details.
	 *
	 * @param pickupLocation the pickup location details
	 */
	public void setPickupLocation(PickupLocationResponse pickupLocation) { this.pickupLocation = pickupLocation; }
	/**
	 * Sets the seller identifier.
	 *
	 * @param sellerId the seller identifier
	 */
	public void setSellerId(String sellerId) { this.sellerId = sellerId; }
	/**
	 * Sets the number of views.
	 *
	 * @param views the number of views
	 */
	public void setViews(long views) { this.views = views; }

}
