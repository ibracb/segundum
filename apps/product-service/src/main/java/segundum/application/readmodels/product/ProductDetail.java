package segundum.application.readmodels.product;

import java.time.Instant;

public class ProductDetail {

	private final String productId;
	private final String title;
	private final String description;
	private final Double price;
	private final Instant publicationDate;
	private final String conditionStatus;
	private final String categoryName;
	private final boolean shippingAvailable;
	private final PickupLocationReadModel pickupLocation;
	private final String sellerId;
	private final long views;

	public ProductDetail(String productId, String title, String description, Double price,
			Instant publicationDate, String conditionStatus, String categoryName,
			boolean shippingAvailable, PickupLocationReadModel pickupLocation, String sellerId, long views) {
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publicationDate = publicationDate;
		this.conditionStatus = conditionStatus;
		this.categoryName = categoryName;
		this.shippingAvailable = shippingAvailable;
		this.pickupLocation = pickupLocation;
		this.sellerId = sellerId;
		this.views = views;
	}

	public String getProductId() { return productId; }
	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public Double getPrice() { return price; }
	public Instant getPublicationDate() { return publicationDate; }
	public String getConditionStatus() { return conditionStatus; }
	public String getCategoryName() { return categoryName; }
	public boolean isShippingAvailable() { return shippingAvailable; }
	public PickupLocationReadModel getPickupLocation() { return pickupLocation; }
	public String getSellerId() { return sellerId; }
	public long getViews() { return views; }

}
