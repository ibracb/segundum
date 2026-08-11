package segundum.infrastructure.messaging.messages;

/**
 * Represents the message sent when a pickup location is assigned to a product.
 */
public class PickupLocationAssignedMessage extends DomainEventMessage {

	/**
	 * Represents a pickup location.
	 */
	public static class PickupLocationMessage {
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
		 * Constructs a new PickupLocationMessage with the given data.
		 *
		 * @param description the description of the location
		 * @param latitude the latitude of the location
		 * @param longitude the longitude of the location
		 */
		public PickupLocationMessage(String description, double latitude, double longitude) {
			this.description = description;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		/**
		 * Returns the description of the pickup location.
		 *
		 * @return the description of the location
		 */
		public String getDescription() { return description; }
		/**
		 * Returns the latitude of the pickup location.
		 *
		 * @return the latitude of the location
		 */
		public double getLatitude() { return latitude; }
		/**
		 * Returns the longitude of the pickup location.
		 *
		 * @return the longitude of the location
		 */
		public double getLongitude() { return longitude; }
	}

	/**
	 * The identifier of the product.
	 */
	private final String productId;
	/**
	 * The assigned pickup location.
	 */
	private final PickupLocationMessage pickupLocation;

	/**
	 * Constructs a new PickupLocationAssignedMessage with the given data.
	 *
	 * @param eventId the event identifier
	 * @param type the event type
	 * @param timestamp the event timestamp
	 * @param productId the product identifier
	 * @param pickupLocation the assigned pickup location
	 */
	public PickupLocationAssignedMessage(String eventId, String type, String timestamp,
			String productId, PickupLocationMessage pickupLocation) {
		super(eventId, type, timestamp);
		this.productId = productId;
		this.pickupLocation = pickupLocation;
	}

	/**
	 * Returns the identifier of the product.
	 *
	 * @return the product identifier
	 */
	public String getProductId() { return productId; }
	/**
	 * Returns the assigned pickup location.
	 *
	 * @return the assigned pickup location
	 */
	public PickupLocationMessage getPickupLocation() { return pickupLocation; }

}
