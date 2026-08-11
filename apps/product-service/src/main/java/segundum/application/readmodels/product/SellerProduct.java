package segundum.application.readmodels.product;

import java.time.Instant;

/**
 * Represents a product owned by a seller.
 */
public class SellerProduct {

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
	 * The publication date of the product.
	 */
	private final Instant publicationDate;
	/**
	 * The name of the category the product belongs to.
	 */
	private final String categoryName;
	/**
	 * The number of views of the product.
	 */
	private final long views;

	/**
	 * Constructs a new SellerProduct with the given values.
	 *
	 * @param productId the product identifier
	 * @param title the product title
	 * @param price the product price
	 * @param conditionStatus the condition status
	 * @param publicationDate the publication date
	 * @param categoryName the category name
	 * @param views the number of views
	 */
	public SellerProduct(String productId, String title, Double price, String conditionStatus,
			Instant publicationDate, String categoryName, long views) {
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.conditionStatus = conditionStatus;
		this.publicationDate = publicationDate;
		this.categoryName = categoryName;
		this.views = views;
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
	 * Returns the publication date.
	 *
	 * @return the publication date
	 */
	public Instant getPublicationDate() {
		return publicationDate;
	}

	/**
	 * Returns the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Returns the number of views.
	 *
	 * @return the number of views
	 */
	public long getViews() {
		return views;
	}

}
