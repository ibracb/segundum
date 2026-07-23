package segundum.domain.events;

import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.PublicationDate;
import segundum.domain.models.product.Title;
import segundum.domain.models.product.Description;
import segundum.domain.models.seller.SellerId;

/**
 * Event published when a new product is created.
 */
public class ProductCreated extends DomainEvent {

	/**
	 * The unique identifier of the product.
	 */
	private final ProductId productId;

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
	 * The publication date of the product.
	 */
	private final PublicationDate publicationDate;

	/**
	 * The condition status of the product.
	 */
	private final ConditionStatus status;

	/**
	 * The unique identifier of the category.
	 */
	private final CategoryId categoryId;

	/**
	 * Whether shipping is available for the product.
	 */
	private final boolean shippingAvailable;

	/**
	 * The unique identifier of the seller.
	 */
	private final SellerId sellerId;

	/**
	 * Constructs a new ProductCreated event with the given parameters.
	 *
	 * @param productId the unique identifier of the product
	 * @param title the title of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param publicationDate the publication date of the product
	 * @param status the condition status of the product
	 * @param categoryId the unique identifier of the category
	 * @param shippingAvailable whether shipping is available for the product
	 * @param sellerId the unique identifier of the seller
	 */
	public ProductCreated(ProductId productId, Title title, Description description,
			Price price, PublicationDate publicationDate, ConditionStatus status,
			CategoryId categoryId, boolean shippingAvailable, SellerId sellerId) {
		super();
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = publicationDate;
		this.status = status;
		this.categoryId = categoryId;
		this.shippingAvailable = shippingAvailable;
		this.sellerId = sellerId;
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
	 * Returns the publication date of the product.
	 *
	 * @return the publication date of the product
	 */
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}

	/**
	 * Returns the condition status of the product.
	 *
	 * @return the condition status of the product
	 */
	public ConditionStatus getConditionStatus() {
		return status;
	}

	/**
	 * Returns the unique identifier of the category.
	 *
	 * @return the unique identifier of the category
	 */
	public CategoryId getCategoryId() {
		return categoryId;
	}

	/**
	 * Returns whether shipping is available for the product.
	 *
	 * @return whether shipping is available for the product
	 */
	public boolean isShippingAvailable() {
		return shippingAvailable;
	}

	/**
	 * Returns the unique identifier of the seller.
	 *
	 * @return the unique identifier of the seller
	 */
	public SellerId getSellerId() {
		return sellerId;
	}

}
