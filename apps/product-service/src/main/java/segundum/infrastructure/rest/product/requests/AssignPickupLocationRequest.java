package segundum.infrastructure.rest.product.requests;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to assign a pickup location to a product")
public class AssignPickupLocationRequest {

	@NotNull
	@NotBlank
	@Schema(description = "Description of the pickup location", example = "Main entrance")
	private String description;

	@DecimalMin("-90.0")
	@DecimalMax("90.0")
	@Schema(description = "Latitude", example = "40.4168")
	private double latitude;

	@DecimalMin("-180.0")
	@DecimalMax("180.0")
	@Schema(description = "Longitude", example = "-3.7038")
	private double longitude;

	/**
	 * Default constructor required by JSON deserialization.
	 */
	public AssignPickupLocationRequest() {
		// Default constructor to satisfy JSON deserialization requirements
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
