package segundum.infrastructure.rest.product.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the response with a product summary for monthly history.
 */
@Schema(description = "Product summary for monthly history")
public class ProductSummaryResponse {

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
	 * The publication date.
	 */
	@Schema(description = "Publication date", example = "2024-01-15")
	private String publicationDate;
	/**
	 * The category name.
	 */
	@Schema(description = "Category name", example = "Electronics")
	private String categoryName;
	/**
	 * The number of views.
	 */
	@Schema(description = "Number of views", example = "150")
	private long views;

	/**
	 * Constructs a new ProductSummaryResponse with no arguments.
	 */
	public ProductSummaryResponse() {
	}

	/**
	 * Constructs a new ProductSummaryResponse with the given values.
	 *
	 * @param productId the product identifier
	 * @param title the product title
	 * @param price the product price
	 * @param publicationDate the publication date
	 * @param categoryName the category name
	 * @param views the number of views
	 */
	public ProductSummaryResponse(String productId, String title, Double price,
			String publicationDate, String categoryName, long views) {
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.publicationDate = publicationDate;
		this.categoryName = categoryName;
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
	 * Returns the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() { return categoryName; }
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
	 * Sets the category name.
	 *
	 * @param categoryName the category name
	 */
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
	/**
	 * Sets the number of views.
	 *
	 * @param views the number of views
	 */
	public void setViews(long views) { this.views = views; }

}
