package segundum.infrastructure.rest.product.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product summary for seller views")
public class SellerProductResponse {

	@Schema(description = "Product identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String productId;
	@Schema(description = "Product title", example = "iPhone 12")
	private String title;
	@Schema(description = "Product price", example = "299.99")
	private Double price;
	@Schema(description = "Condition status", example = "GOOD")
	private String conditionStatus;
	@Schema(description = "Publication date", example = "2024-01-15")
	private String publicationDate;
	@Schema(description = "Category name", example = "Electronics")
	private String categoryName;
	@Schema(description = "Number of views", example = "150")
	private long views;

	public SellerProductResponse() {
	}

	public SellerProductResponse(String productId, String title, Double price, String conditionStatus,
			String publicationDate, String categoryName, long views) {
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.conditionStatus = conditionStatus;
		this.publicationDate = publicationDate;
		this.categoryName = categoryName;
		this.views = views;
	}

	public String getProductId() { return productId; }
	public String getTitle() { return title; }
	public Double getPrice() { return price; }
	public String getConditionStatus() { return conditionStatus; }
	public String getPublicationDate() { return publicationDate; }
	public String getCategoryName() { return categoryName; }
	public long getViews() { return views; }

	public void setProductId(String productId) { this.productId = productId; }
	public void setTitle(String title) { this.title = title; }
	public void setPrice(Double price) { this.price = price; }
	public void setConditionStatus(String conditionStatus) { this.conditionStatus = conditionStatus; }
	public void setPublicationDate(String publicationDate) { this.publicationDate = publicationDate; }
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
	public void setViews(long views) { this.views = views; }

}
