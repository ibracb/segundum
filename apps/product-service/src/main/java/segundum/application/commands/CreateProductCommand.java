package segundum.application.commands;

import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Title;
import segundum.domain.models.seller.SellerId;

/**
 * Represents a command to create a new product in the system.
 */
public class CreateProductCommand {

	/**
	 * The title of the product.
	 */
	private final Title title;

	/**
	 * The description of the product.
	 */
	private final Description description;

	/**
	 * The price of the product.
	 */
	private final Price price;

	/**
	 * The condition status of the product.
	 */
	private final ConditionStatus status;

	/**
	 * The category of the product.
	 */
	private final CategoryId categoryId;

	/**
	 * Whether shipping is available for the product.
	 */
	private final boolean shippingAvailable;

	/**
	 * The seller of the product.
	 */
	private final SellerId sellerId;

	/**
	 * Constructs a new CreateProductCommand with the given parameters.
	 *
	 * @param title the title of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param status the condition status of the product
	 * @param categoryId the category of the product
	 * @param shippingAvailable whether shipping is available for the product
	 * @param sellerId the seller of the product
	 */
	public CreateProductCommand(Title title, Description description, Price price,
			ConditionStatus status, CategoryId categoryId, boolean shippingAvailable,
			SellerId sellerId) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.status = status;
		this.categoryId = categoryId;
		this.shippingAvailable = shippingAvailable;
		this.sellerId = sellerId;
	}

	/**
	 * Returns the title of the product.
	 *
	 * @return the title of the product
	 */
	public Title getTitle() {
		return title;
	}

	/**
	 * Returns the description of the product.
	 *
	 * @return the description of the product
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Returns the price of the product.
	 *
	 * @return the price of the product
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * Returns the condition status of the product.
	 *
	 * @return the condition status of the product
	 */
	public ConditionStatus getStatus() {
		return status;
	}

	/**
	 * Returns the category of the product.
	 *
	 * @return the category of the product
	 */
	public CategoryId getCategoryId() {
		return categoryId;
	}

	/**
	 * Returns whether shipping is available for the product.
	 *
	 * @return true if shipping is available, false otherwise
	 */
	public boolean isShippingAvailable() {
		return shippingAvailable;
	}

	/**
	 * Returns the seller of the product.
	 *
	 * @return the seller of the product
	 */
	public SellerId getSellerId() {
		return sellerId;
	}

}
