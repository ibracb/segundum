package segundum.infrastructure.persistence.jpa.product;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;

/**
 * Embeddable representing a pickup location for a product.
 */
@Embeddable
public class PickupLocationEmbeddable {

	/**
	 * The description of the pickup location.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "pickup_description")
	private String description;

	/**
	 * The latitude of the pickup location.
	 */
	@Column(name = "pickup_latitude")
	private Double latitude;

	/**
	 * The longitude of the pickup location.
	 */
	@Column(name = "pickup_longitude")
	private Double longitude;

	/**
	 * Default constructor required by JPA.
	 */
	protected PickupLocationEmbeddable() {
	}

	/**
	 * Constructs a new PickupLocationEmbeddable with the given attributes.
	 *
	 * @param description the description of the pickup location
	 * @param latitude the latitude of the pickup location
	 * @param longitude the longitude of the pickup location
	 */
	public PickupLocationEmbeddable(String description, Double latitude, Double longitude) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Returns the description of the pickup location.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the latitude of the pickup location.
	 *
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * Returns the longitude of the pickup location.
	 *
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

}
