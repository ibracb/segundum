package segundum.application.readmodels.product;

public class ProductSearchResult {

	private final String productId;
	private final String title;
	private final Double price;
	private final String conditionStatus;
	private final String categoryName;

	public ProductSearchResult(String productId, String title, Double price,
			String conditionStatus, String categoryName) {
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.conditionStatus = conditionStatus;
		this.categoryName = categoryName;
	}

	public String getProductId() {
		return productId;
	}

	public String getTitle() {
		return title;
	}

	public Double getPrice() {
		return price;
	}

	public String getConditionStatus() {
		return conditionStatus;
	}

	public String getCategoryName() {
		return categoryName;
	}

}
