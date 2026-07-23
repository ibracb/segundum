package segundum.infrastructure.rest.product.responses;

public class SellerProductResponse {

	private String productId;
	private String title;
	private Double price;
	private String conditionStatus;
	private String publicationDate;
	private String categoryName;
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
