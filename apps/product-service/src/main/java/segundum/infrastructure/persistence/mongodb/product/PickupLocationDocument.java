package segundum.infrastructure.persistence.mongodb.product;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MongoDB embedded document representing a pickup location.
 */
public class PickupLocationDocument {

	@Field("description")
	private String description;

	@Field("latitude")
	private Double latitude;

	@Field("longitude")
	private Double longitude;

	public PickupLocationDocument() {
	}

	public PickupLocationDocument(String description, Double latitude, Double longitude) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

}
