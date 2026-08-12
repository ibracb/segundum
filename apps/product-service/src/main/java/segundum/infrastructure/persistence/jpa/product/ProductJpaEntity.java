package segundum.infrastructure.persistence.jpa.product;

import java.time.Instant;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * JPA entity representing a product in the persistence layer.
 */
@Entity
@Table(name = "products")
public class ProductJpaEntity {

	/**
	 * The product identifier.
	 */
	@Id
	@Column(name = "id", nullable = false)
	private String id;

	/**
	 * The title of the product.
	 */
	@Column(name = "title", nullable = false)
	private String title;

	/**
	 * The description of the product.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "description", nullable = false)
	private String description;

	/**
	 * The price of the product.
	 */
	@Column(name = "price", nullable = false)
	private double price;

	/**
	 * The publication date and time of the product.
	 */
	@Column(name = "publication_date", nullable = false)
	private Instant publicationDate;

	/**
	 * The condition status of the product.
	 */
	@Column(name = "condition_status", nullable = false)
	private String conditionStatus;

	/**
	 * The sale status of the product.
	 */
	@Column(name = "sale_status", nullable = false)
	private String saleStatus;

	/**
	 * The category identifier.
	 */
	@Column(name = "category_id", nullable = false)
	private String categoryId;

	/**
	 * Whether shipping is available for the product.
	 */
	@Column(name = "shipping_available", nullable = false)
	private boolean shippingAvailable;

	/**
	 * The seller identifier.
	 */
	@Column(name = "seller_id", nullable = false)
	private String sellerId;

	/**
	 * The number of views for the product.
	 */
	@Column(name = "views", nullable = false)
	private long views;

	/**
	 * The pickup location for the product (nullable).
	 */
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "description", column = @Column(name = "pickup_description")),
		@AttributeOverride(name = "latitude", column = @Column(name = "pickup_latitude")),
		@AttributeOverride(name = "longitude", column = @Column(name = "pickup_longitude"))
	})
	private PickupLocationEmbeddable pickupLocation;

	/**
	 * Default constructor required by JPA.
	 */
	protected ProductJpaEntity() {
	}

	/**
	 * Constructs a new ProductJpaEntity with the given attributes.
	 *
	 * @param id the product identifier
	 * @param title the title of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param publicationDate the publication date and time of the product
	 * @param conditionStatus the condition status of the product
	 * @param saleStatus the sale status of the product
	 * @param categoryId the category identifier
	 * @param shippingAvailable whether shipping is available for the product
	 * @param sellerId the seller identifier
	 * @param views the number of views for the product
	 * @param pickupLocation the pickup location for the product (nullable)
	 */
	public ProductJpaEntity(String id, String title, String description,
			double price, Instant publicationDate, String conditionStatus,
			String saleStatus, String categoryId, boolean shippingAvailable,
			String sellerId, long views, PickupLocationEmbeddable pickupLocation) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = publicationDate;
		this.conditionStatus = conditionStatus;
		this.saleStatus = saleStatus;
		this.categoryId = categoryId;
		this.shippingAvailable = shippingAvailable;
		this.sellerId = sellerId;
		this.views = views;
		this.pickupLocation = pickupLocation;
	}

	/**
	 * Returns the product identifier.
	 *
	 * @return the product identifier
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the title of the product.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the description of the product.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the price of the product.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the publication date and time of the product.
	 *
	 * @return the publication date
	 */
	public Instant getPublicationDate() {
		return publicationDate;
	}

	/**
	 * Returns the condition status of the product.
	 *
	 * @return the condition status
	 */
	public String getConditionStatus() {
		return conditionStatus;
	}

	/**
	 * Returns the sale status of the product.
	 *
	 * @return the sale status
	 */
	public String getSaleStatus() {
		return saleStatus;
	}

	/**
	 * Returns the category identifier.
	 *
	 * @return the category identifier
	 */
	public String getCategoryId() {
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
	 * Returns the seller identifier.
	 *
	 * @return the seller identifier
	 */
	public String getSellerId() {
		return sellerId;
	}

	/**
	 * Returns the number of views for the product.
	 *
	 * @return the number of views
	 */
	public long getViews() {
		return views;
	}

	/**
	 * Returns the pickup location for the product.
	 *
	 * @return the pickup location (may be null)
	 */
	public PickupLocationEmbeddable getPickupLocation() {
		return pickupLocation;
	}

	/**
	 * Sets the sale status of the product.
	 *
	 * @param saleStatus the new sale status
	 */
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

}
