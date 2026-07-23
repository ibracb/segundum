package segundum.domain.events;

import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;

/**
 * Event published when a product is updated.
 */
public class ProductUpdated extends DomainEvent {

	/**
	 * The unique identifier of the product.
	 */
	private final ProductId productId;

	/**
	 * The new price of the product.
	 */
	private final Price price;

	/**
	 * The new description of the product.
	 */
	private final Description description;

	/**
	 * The new condition status of the product.
	 */
	private final ConditionStatus conditionStatus;

	/**
	 * Constructs a new ProductUpdated event with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 * @param price the new price of the product
	 * @param description the new description of the product
	 * @param conditionStatus the new condition status of the product
	 */
	public ProductUpdated(ProductId productId, Price price, Description description,
			ConditionStatus conditionStatus) {
		super();
		this.productId = productId;
		this.price = price;
		this.description = description;
		this.conditionStatus = conditionStatus;
	}

	/**
	 * Returns the unique identifier of the product.
	 *
	 * @return the unique identifier of the product
	 */
	public ProductId getProductId() {
		return productId;
	}

	/**
	 * Returns the new price of the product.
	 *
	 * @return the new price of the product
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * Returns the new description of the product.
	 *
	 * @return the new description of the product
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Returns the new condition status of the product.
	 *
	 * @return the new condition status of the product
	 */
	public ConditionStatus getConditionStatus() {
		return conditionStatus;
	}

}
