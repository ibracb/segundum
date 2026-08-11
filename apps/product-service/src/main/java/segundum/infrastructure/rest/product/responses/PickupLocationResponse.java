package segundum.infrastructure.rest.product.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the response with pickup location details.
 */
@Schema(description = "Pickup location details")
public class PickupLocationResponse {

	/**
	 * The location description.
	 */
	@Schema(description = "Location description", example = "Main entrance")
	private String description;
	/**
	 * The latitude.
	 */
	@Schema(description = "Latitude", example = "40.4168")
	private double latitude;
	/**
	 * The longitude.
	 */
	@Schema(description = "Longitude", example = "-3.7038")
	private double longitude;

	/**
	 * Constructs a new PickupLocationResponse with no arguments.
	 */
	public PickupLocationResponse() {
	}

	/**
	 * Constructs a new PickupLocationResponse with the given values.
	 *
	 * @param description the location description
	 * @param latitude the latitude
	 * @param longitude the longitude
	 */
	public PickupLocationResponse(String description, double latitude, double longitude) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Returns the location description.
	 *
	 * @return the location description
	 */
	public String getDescription() { return description; }
	/**
	 * Returns the latitude.
	 *
	 * @return the latitude
	 */
	public double getLatitude() { return latitude; }
	/**
	 * Returns the longitude.
	 *
	 * @return the longitude
	 */
	public double getLongitude() { return longitude; }

	/**
	 * Sets the location description.
	 *
	 * @param description the location description
	 */
	public void setDescription(String description) { this.description = description; }
	/**
	 * Sets the latitude.
	 *
	 * @param latitude the latitude
	 */
	public void setLatitude(double latitude) { this.latitude = latitude; }
	/**
	 * Sets the longitude.
	 *
	 * @param longitude the longitude
	 */
	public void setLongitude(double longitude) { this.longitude = longitude; }

}
