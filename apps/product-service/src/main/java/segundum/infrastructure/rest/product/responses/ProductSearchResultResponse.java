package segundum.infrastructure.rest.product.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the response with a product search result.
 */
@Schema(description = "Product search result")
public class ProductSearchResultResponse {

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
	 * The product price.
	 */
	@Schema(description = "Product price", example = "299.99")
	private Double price;
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
	 * Constructs a new ProductSearchResultResponse with no arguments.
	 */
	public ProductSearchResultResponse() {
	}

	/**
	 * Constructs a new ProductSearchResultResponse with the given values.
	 *
	 * @param productId the product identifier
	 * @param title the product title
	 * @param price the product price
	 * @param conditionStatus the condition status
	 * @param categoryName the category name
	 */
	public ProductSearchResultResponse(String productId, String title, Double price,
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
	public String getProductId() { return productId; }
	/**
	 * Returns the product title.
	 *
	 * @return the product title
	 */
	public String getTitle() { return title; }
	/**
	 * Returns the product price.
	 *
	 * @return the product price
	 */
	public Double getPrice() { return price; }
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
	 * Sets the product price.
	 *
	 * @param price the product price
	 */
	public void setPrice(Double price) { this.price = price; }
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

}
