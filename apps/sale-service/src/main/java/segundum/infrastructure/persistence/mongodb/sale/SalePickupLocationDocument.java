package segundum.infrastructure.persistence.mongodb.sale;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Denormalized pickup location information embedded in a sale document.
 */
public class SalePickupLocationDocument {

	@Field("description")
	private String description;

	@Field("latitude")
	private double latitude;

	@Field("longitude")
	private double longitude;

	public SalePickupLocationDocument() {
	}

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public double getLatitude() { return latitude; }
	public void setLatitude(double latitude) { this.latitude = latitude; }
	public double getLongitude() { return longitude; }
	public void setLongitude(double longitude) { this.longitude = longitude; }

}
