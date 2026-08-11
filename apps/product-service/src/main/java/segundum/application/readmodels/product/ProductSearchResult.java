package segundum.application.readmodels.product;

/**
 * Represents the result of a product search.
 */
public class ProductSearchResult {

	/**
	 * The unique identifier of the product.
	 */
	private final String productId;
	/**
	 * The title of the product.
	 */
	private final String title;
	/**
	 * The price of the product.
	 */
	private final Double price;
	/**
	 * The condition status of the product.
	 */
	private final String conditionStatus;
	/**
	 * The name of the category the product belongs to.
	 */
	private final String categoryName;

	/**
	 * Constructs a new ProductSearchResult with the given values.
	 *
	 * @param productId the product identifier
	 * @param title the product title
	 * @param price the product price
	 * @param conditionStatus the condition status
	 * @param categoryName the category name
	 */
	public ProductSearchResult(String productId, String title, Double price,
			String conditionStatus, String categoryName) {
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.conditionStatus = conditionStatus;
		this.categoryName = categoryName;
	}

	/**
	 * Returns the product identifier.
	 *
	 * @return the product identifier
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Returns the product title.
	 *
	 * @return the product title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the product price.
	 *
	 * @return the product price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Returns the condition status.
	 *
	 * @return the condition status
	 */
	public String getConditionStatus() {
		return conditionStatus;
	}

	/**
	 * Returns the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

}
