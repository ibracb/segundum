package segundum.infrastructure.rest.product.responses;

public class ProductSearchResultResponse {

	private String productId;
	private String title;
	private Double price;
	private String conditionStatus;
	private String categoryName;

	public ProductSearchResultResponse() {
	}

	public ProductSearchResultResponse(String productId, String title, Double price,
			String conditionStatus, String categoryName) {
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.conditionStatus = conditionStatus;
		this.categoryName = categoryName;
	}

	public String getProductId() { return productId; }
	public String getTitle() { return title; }
	public Double getPrice() { return price; }
	public String getConditionStatus() { return conditionStatus; }
	public String getCategoryName() { return categoryName; }

	public void setProductId(String productId) { this.productId = productId; }
	public void setTitle(String title) { this.title = title; }
	public void setPrice(Double price) { this.price = price; }
	public void setConditionStatus(String conditionStatus) { this.conditionStatus = conditionStatus; }
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

}
