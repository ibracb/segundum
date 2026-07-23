package segundum.infrastructure.rest.product.responses;

public class PickupLocationResponse {

	private String description;
	private double latitude;
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
