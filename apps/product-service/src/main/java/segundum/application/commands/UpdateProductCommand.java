package segundum.application.commands;

import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;

/**
 * Represents a command to update an existing product in the system.
 */
public class UpdateProductCommand {

	/**
	 * The unique identifier of the product to be updated.
	 */
	private final ProductId productId;

	/**
	 * The new price of the product (nullable).
	 */
	private final Price price;

	/**
	 * The new description of the product (nullable).
	 */
	private final Description description;

	/**
	 * The new condition status of the product (nullable).
	 */
	private final ConditionStatus conditionStatus;

	/**
	 * Constructs a new UpdateProductCommand with the given parameters.
	 *
	 * @param productId the unique identifier of the product to be updated
	 * @param price the new price of the product (nullable)
	 * @param description the new description of the product (nullable)
	 * @param conditionStatus the new condition status of the product (nullable)
	 */
	public UpdateProductCommand(ProductId productId, Price price, Description description,
			ConditionStatus conditionStatus) {
		this.productId = productId;
		this.price = price;
		this.description = description;
		this.conditionStatus = conditionStatus;
	}

	/**
	 * Returns the unique identifier of the product to be updated.
	 *
	 * @return the unique identifier of the product
	 */
	public ProductId getProductId() {
		return productId;
	}

	/**
	 * Returns the new price of the product.
	 *
	 * @return the new price (may be null)
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * Returns the new description of the product.
	 *
	 * @return the new description (may be null)
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Returns the new condition status of the product.
	 *
	 * @return the new condition status (may be null)
	 */
	public ConditionStatus getConditionStatus() {
		return conditionStatus;
	}

}
