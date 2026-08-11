package segundum.infrastructure.messaging.messages;

import java.time.Instant;

/**
 * Raw message representing a sale proposed event.
 */
public class SaleProposedMessage extends DomainEventMessage {

	/**
	 * The unique identifier of the sale.
	 */
	private final String saleId;

	/**
	 * The unique identifier of the product.
	 */
	private final String productId;

	/**
	 * The unique identifier of the seller.
	 */
	private final String sellerId;

	/**
	 * The name of the seller.
	 */
	private final String sellerName;

	/**
	 * The surname of the seller.
	 */
	private final String sellerSurname;

	/**
	 * The unique identifier of the purchaser.
	 */
	private final String purchaserId;

	/**
	 * The name of the purchaser.
	 */
	private final String purchaserName;

	/**
	 * The surname of the purchaser.
	 */
	private final String purchaserSurname;

	/**
	 * The price of the sale.
	 */
	private final double price;

	/**
	 * The title of the product.
	 */
	private final String title;

	/**
	 * The pickup location of the sale.
	 */
	private final PickupLocation pickupLocation;

	/**
	 * The datetime of the sale.
	 */
	private final Instant datetime;

	/**
	 * Constructs a new SaleProposedMessage with the given parameters.
	 *
	 * @param eventId the unique identifier of the event
	 * @param type the type of the event
	 * @param timestamp the timestamp of the event
	 * @param saleId the unique identifier of the sale
	 * @param productId the unique identifier of the product
	 * @param sellerId the unique identifier of the seller
	 * @param sellerName the name of the seller
	 * @param sellerSurname the surname of the seller
	 * @param purchaserId the unique identifier of the purchaser
	 * @param purchaserName the name of the purchaser
	 * @param purchaserSurname the surname of the purchaser
	 * @param price the price of the sale
	 * @param title the title of the product
	 * @param pickupLocation the pickup location of the sale
	 * @param datetime the datetime of the sale
	 */
	public SaleProposedMessage(String eventId, String type, String timestamp, String saleId,
			String productId, String sellerId, String sellerName, String sellerSurname,
			String purchaserId, String purchaserName, String purchaserSurname, double price,
			String title, PickupLocation pickupLocation, Instant datetime) {
		super(eventId, type, timestamp);
		this.saleId = saleId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.sellerSurname = sellerSurname;
		this.purchaserId = purchaserId;
		this.purchaserName = purchaserName;
		this.purchaserSurname = purchaserSurname;
		this.price = price;
		this.title = title;
		this.pickupLocation = pickupLocation;
		this.datetime = datetime;
	}

	/**
	 * Returns the unique identifier of the sale.
	 *
	 * @return the unique identifier of the sale
	 */
	public String getSaleId() {
		return saleId;
	}

	/**
	 * Returns the unique identifier of the product.
	 *
	 * @return the unique identifier of the product
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Returns the unique identifier of the seller.
	 *
	 * @return the unique identifier of the seller
	 */
	public String getSellerId() {
		return sellerId;
	}

	/**
	 * Returns the name of the seller.
	 *
	 * @return the name of the seller
	 */
	public String getSellerName() {
		return sellerName;
	}

	/**
	 * Returns the surname of the seller.
	 *
	 * @return the surname of the seller
	 */
	public String getSellerSurname() {
		return sellerSurname;
	}

	/**
	 * Returns the unique identifier of the purchaser.
	 *
	 * @return the unique identifier of the purchaser
	 */
	public String getPurchaserId() {
		return purchaserId;
	}

	/**
	 * Returns the name of the purchaser.
	 *
	 * @return the name of the purchaser
	 */
	public String getPurchaserName() {
		return purchaserName;
	}

	/**
	 * Returns the surname of the purchaser.
	 *
	 * @return the surname of the purchaser
	 */
	public String getPurchaserSurname() {
		return purchaserSurname;
	}

	/**
	 * Returns the price of the sale.
	 *
	 * @return the price of the sale
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the title of the product.
	 *
	 * @return the title of the product
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the pickup location of the sale.
	 *
	 * @return the pickup location of the sale
	 */
	public PickupLocation getPickupLocation() {
		return pickupLocation;
	}

	/**
	 * Returns the datetime of the sale.
	 *
	 * @return the datetime of the sale
	 */
	public Instant getDatetime() {
		return datetime;
	}

	/**
	 * Raw representation of the pickup location.
	 */
	public static class PickupLocation {

		/**
		 * The description of the pickup location.
		 */
		private final String description;

		/**
		 * The latitude of the pickup location.
		 */
		private final double latitude;

		/**
		 * The longitude of the pickup location.
		 */
		private final double longitude;

		/**
		 * Constructs a new PickupLocation with the given parameters.
		 *
		 * @param description the description of the pickup location
		 * @param latitude the latitude of the pickup location
		 * @param longitude the longitude of the pickup location
		 */
		public PickupLocation(String description, double latitude, double longitude) {
			this.description = description;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		/**
		 * Returns the description of the pickup location.
		 *
		 * @return the description of the pickup location
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Returns the latitude of the pickup location.
		 *
		 * @return the latitude of the pickup location
		 */
		public double getLatitude() {
			return latitude;
		}

		/**
		 * Returns the longitude of the pickup location.
		 *
		 * @return the longitude of the pickup location
		 */
		public double getLongitude() {
			return longitude;
		}

	}

}
