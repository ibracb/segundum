package segundum.domain.models.product;

import segundum.domain.exceptions.product.salestatus.ProductNotDraftException;
import segundum.domain.exceptions.product.salestatus.ProductNotForSaleException;
import segundum.domain.exceptions.product.salestatus.ProductNotReservedException;
import segundum.domain.exceptions.product.shipping.ShippingAlreadyDisabledException;
import segundum.domain.exceptions.product.shipping.ShippingAlreadyEnabledException;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.seller.SellerId;

/**
 * Represents a product in the product service.
 */
public class Product {
	
	/**
	 * Constant representing the initial number of views for a product.
	 */
	private static final long NO_VIEWS = 0;
	
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
	private Description description;

	/**
	 * The price of the product.
	 */
	private Price price;

	/**
	 * The publication date and time of the product.
	 */
	private final PublicationDate publicationDate;

	/**
	 * The condition status of the product.
	 */
	private ConditionStatus conditionStatus;

	/**
	 * The sale status of the product.
	 */
	private SaleStatus saleStatus;

	/**
	 * The category of the product.
	 */
	private final CategoryId categoryId;

	/**
	 * Whether shipping is available for the product.
	 */
	private boolean shippingAvailable;

	/**
	 * The pickup location for the product (nullable).
	 */
	private PickupLocation pickupLocation;

	/**
	 * The seller of the product.
	 */
	private final SellerId sellerId;

	/**
	 * The number of views for the product.
	 */
	private long views;

	/**
	 * Constructs a new Product object with the given parameters.
	 *
	 * @param title the title of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param status the condition status of the product
	 * @param categoryId the category of the product
	 * @param shippingAvailable whether shipping is available for the product
	 * @param sellerId the seller of the product
	 */
	Product(Title title, Description description, Price price,
			ConditionStatus conditionStatus, CategoryId categoryId,
			boolean shippingAvailable, SellerId sellerId) {
		this.productId = ProductId.generate();
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = PublicationDate.now();
		this.conditionStatus = conditionStatus;
		this.saleStatus = SaleStatus.DRAFT;
		this.categoryId = categoryId;
		this.shippingAvailable = shippingAvailable;
		this.pickupLocation = null;
		this.sellerId = sellerId;
		this.views = NO_VIEWS;
	}

	/**
	 * Constructs a new Product object for reconstitution from persistence.
	 *
	 * @param productId the unique identifier of the product
	 * @param title the title of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param publicationDate the publication date and time of the product
	 * @param status the condition status of the product
	 * @param saleStatus the sale status of the product
	 * @param categoryId the category of the product
	 * @param shippingAvailable whether shipping is available for the product
	 * @param pickupLocation the pickup location for the product (nullable)
	 * @param sellerId the seller of the product
	 * @param views the number of views for the product
	 */
	Product(ProductId productId, Title title, Description description, Price price,
			PublicationDate publicationDate, ConditionStatus conditionStatus, SaleStatus saleStatus,
			CategoryId categoryId, boolean shippingAvailable, PickupLocation pickupLocation,
			SellerId sellerId, long views) {
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = publicationDate;
		this.conditionStatus = conditionStatus;
		this.saleStatus = saleStatus;
		this.categoryId = categoryId;
		this.shippingAvailable = shippingAvailable;
		this.pickupLocation = pickupLocation;
		this.sellerId = sellerId;
		this.views = views;
	}

	/**
	 * Publishes the product for sale.
	 *
	 * @throws ProductNotDraftException if the product is not in draft status
	 */
	public void putForSale() {
		ensureIsDraft();
		this.saleStatus = SaleStatus.FOR_SALE;
	}

	/**
	 * Takes the product down from sale.
	 *
	 * @throws ProductNotForSaleException if the product is not for sale
	 */
	public void takeDown() {
		ensureIsForSale();
		this.saleStatus = SaleStatus.DRAFT;
	}

	/**
	 * Reserves the product for a buyer.
	 *
	 * @throws ProductNotForSaleException if the product is not for sale
	 */
	public void reserve() {
		ensureIsForSale();
		this.saleStatus = SaleStatus.RESERVED;
	}

	/**
	 * Cancels the reservation of the product.
	 *
	 * @throws ProductNotReservedException if the product is not reserved
	 */
	public void cancelReservation() {
		ensureIsReserved();
		this.saleStatus = SaleStatus.FOR_SALE;
	}

	/**
	 * Marks the product as sold.
	 *
	 * @throws ProductNotReservedException if the product is not reserved
	 */
	public void sell() {
		ensureIsReserved();
		this.saleStatus = SaleStatus.SOLD;
	}

	/**
	 * Discards a draft product.
	 *
	 * @throws ProductNotDraftException if the product is not in draft status
	 */
	public void discard() {
		ensureIsDraft();
		this.saleStatus = SaleStatus.DELETED;
	}

	/**
	 * Permanently removes a product that is currently for sale.
	 *
	 * @throws ProductNotForSaleException if the product is not for sale
	 */
	public void remove() {
		ensureIsForSale();
		this.saleStatus = SaleStatus.DELETED;
	}

	/**
	 * Enables shipping for the product.
	 *
	 * @throws ShippingAlreadyEnabledException if shipping is already enabled
	 */
	public void enableShipping() {
		ensureShippingIsNotAlreadyEnabled();
		shippingAvailable = true;
	}

	/**
	 * Disables shipping for the product.
	 *
	 * @throws ShippingAlreadyDisabledException if shipping is already disabled
	 */
	public void disableShipping() {
		ensureShippingIsNotAlreadyDisabled();
		shippingAvailable = false;
	}

	/**
	 * Increments the number of views for the product.
	 * 
	 * @throws ProductNotForSaleException if the product is not for sale
	 */
	public void incrementViews() {
		ensureIsForSale();
		views++;
	}

	/**
	 * Changes the description of the product.
	 *
	 * @param description the new description
	 */
	public void changeDescription(Description description) {
		ensureIsDraft();
		this.description = description;
	}

	/**
	 * Changes the price of the product.
	 *
	 * @param price the new price
	 */
	public void changePrice(Price price) {
		ensureIsDraft();
		this.price = price;
	}

	/**
	 * Changes the condition status of the product.
	 *
	 * @param status the new condition status
	 */
	public void changeConditionStatus(ConditionStatus conditionStatus) {
		ensureIsDraft();
		this.conditionStatus = conditionStatus;
	}

	/**
	 * Assigns a pickup location to the product.
	 * If a pickup location was previously assigned, it is replaced.
	 *
	 * @param pickupLocation the pickup location to assign
	 */
	public void assignPickupLocation(PickupLocation pickupLocation) {
		ensureIsDraft();
		this.pickupLocation = pickupLocation;
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
	 * Returns the publication date and time of the product.
	 *
	 * @return the publication date and time of the product
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
		return conditionStatus;
	}

	/**
	 * Returns the sale status of the product.
	 *
	 * @return the sale status of the product
	 */
	public SaleStatus getSaleStatus() {
		return saleStatus;
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
	 * Returns the pickup location for the product.
	 *
	 * @return the pickup location for the product (may be null)
	 */
	public PickupLocation getPickupLocation() {
		return pickupLocation;
	}

	/**
	 * Returns the seller of the product.
	 *
	 * @return the seller of the product
	 */
	public SellerId getSellerId() {
		return sellerId;
	}

	/**
	 * Returns the number of views for the product.
	 *
	 * @return the number of views for the product
	 */
	public long getViews() {
		return views;
	}
	
	/**
	 * Ensures that the product is in draft status.
	 *
	 * @throws ProductNotDraftException if the product is not in draft status
	 */
	private void ensureIsDraft() {
		if (saleStatus != SaleStatus.DRAFT) {
			throw new ProductNotDraftException();
		}
	}
	
	/**
	 * Ensures that the product is for sale.
	 *
	 * @throws ProductNotForSaleException if the product is not for sale
	 */
	private void ensureIsForSale() {
		if (saleStatus != SaleStatus.FOR_SALE) {
			throw new ProductNotForSaleException();
		}
	}
	
	/**
	 * Ensures that the product is reserved.
	 *
	 * @throws ProductNotReservedException if the product is not reserved
	 */
	private void ensureIsReserved() {
		if (saleStatus != SaleStatus.RESERVED) {
			throw new ProductNotReservedException();
		}
	}
	
	/**
	 * Ensures that shipping is not already enabled.
	 *
	 * @throws ShippingAlreadyEnabledException if shipping is already enabled
	 */
	private void ensureShippingIsNotAlreadyEnabled() {
		if (shippingAvailable) {
			throw new ShippingAlreadyEnabledException();
		}
	}
	
	/**
	 * Ensures that shipping is not already disabled.
	 *
	 * @throws ShippingAlreadyDisabledException if shipping is already disabled
	 */
	private void ensureShippingIsNotAlreadyDisabled() {
		if (!shippingAvailable) {
			throw new ShippingAlreadyDisabledException();
		}
	}

}
