package segundum.application.readmodels.product;

/**
 * Read model for pickup location data.
 * Used by the read side to avoid leaking domain entities.
 */
public class PickupLocationReadModel {

	private final String description;
	private final double latitude;
	private final double longitude;

	/**
	 * Constructs a new PickupLocationReadModel.
	 *
	 * @param description the location description
	 * @param latitude    the latitude
	 * @param longitude   the longitude
	 */
	public PickupLocationReadModel(String description, double latitude, double longitude) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getDescription() { return description; }
	public double getLatitude() { return latitude; }
	public double getLongitude() { return longitude; }

}
