package segundum.infrastructure.rest.product.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Pickup location details")
public class PickupLocationResponse {

	@Schema(description = "Location description", example = "Main entrance")
	private String description;
	@Schema(description = "Latitude", example = "40.4168")
	private double latitude;
	@Schema(description = "Longitude", example = "-3.7038")
	private double longitude;

	public PickupLocationResponse() {
	}

	public PickupLocationResponse(String description, double latitude, double longitude) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getDescription() { return description; }
	public double getLatitude() { return latitude; }
	public double getLongitude() { return longitude; }

	public void setDescription(String description) { this.description = description; }
	public void setLatitude(double latitude) { this.latitude = latitude; }
	public void setLongitude(double longitude) { this.longitude = longitude; }

}
