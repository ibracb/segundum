package segundum.domain.models.pickup;

import segundum.domain.exceptions.pickup.description.DescriptionBlankException;
import segundum.domain.exceptions.pickup.description.DescriptionNullException;
import segundum.domain.exceptions.pickup.description.DescriptionTooLongException;
import segundum.domain.exceptions.pickup.latitude.LatitudeTooHighException;
import segundum.domain.exceptions.pickup.latitude.LatitudeTooLowException;
import segundum.domain.exceptions.pickup.longitude.LongitudeTooHighException;
import segundum.domain.exceptions.pickup.longitude.LongitudeTooLowException;

/**
 * Represents a pickup location for a product.
 * This value object contains the description, latitude, and longitude of the pickup location.
 */
public class PickupLocation {

	/**
	 * The maximum allowed length for the description.
	 */
	private static final int MAX_DESCRIPTION_LENGTH = 500;

	/**
	 * The minimum allowed value for latitude.
	 */
	private static final double MIN_LATITUDE = -90.0;

	/**
	 * The maximum allowed value for latitude.
	 */
	private static final double MAX_LATITUDE = 90.0;

	/**
	 * The minimum allowed value for longitude.
	 */
	private static final double MIN_LONGITUDE = -180.0;

	/**
	 * The maximum allowed value for longitude.
	 */
	private static final double MAX_LONGITUDE = 180.0;

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
	 * Constructs a new PickupLocation object with the given parameters.
	 *
	 * @param description the description of the pickup location
	 * @param latitude the latitude of the pickup location
	 * @param longitude the longitude of the pickup location
	 * @throws DescriptionNullException if the description is null
	 * @throws DescriptionBlankException if the description is blank
	 * @throws DescriptionTooLongException if the description exceeds 500 characters
	 * @throws LatitudeTooLowException if the latitude is below -90.0
	 * @throws LatitudeTooHighException if the latitude is above 90.0
	 * @throws LongitudeTooLowException if the longitude is below -180.0
	 * @throws LongitudeTooHighException if the longitude is above 180.0
	 */
	public PickupLocation(String description, double latitude, double longitude) {
		ensureDescriptionIsNotNull(description);
		ensureDescriptionIsNotBlank(description);
		ensureDescriptionIsWithinMaxLength(description);
		ensureLatitudeIsNotTooLow(latitude);
		ensureLatitudeIsNotTooHigh(latitude);
		ensureLongitudeIsNotTooLow(longitude);
		ensureLongitudeIsNotTooHigh(longitude);
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Ensures that the description is not null.
	 *
	 * @param description the description to ensure
	 * @throws DescriptionNullException if the description is null
	 */
	private static void ensureDescriptionIsNotNull(String description) {
		if (description == null) {
			throw new DescriptionNullException();
		}
	}

	/**
	 * Ensures that the description is not blank.
	 *
	 * @param description the description to ensure
	 * @throws DescriptionBlankException if the description is blank
	 */
	private static void ensureDescriptionIsNotBlank(String description) {
		if (description.trim().isEmpty()) {
			throw new DescriptionBlankException();
		}
	}

	/**
	 * Ensures that the description does not exceed the maximum allowed length.
	 *
	 * @param description the description to ensure
	 * @throws DescriptionTooLongException if the description exceeds 500 characters
	 */
	private static void ensureDescriptionIsWithinMaxLength(String description) {
		if (description.length() > MAX_DESCRIPTION_LENGTH) {
			throw new DescriptionTooLongException();
		}
	}

	/**
	 * Ensures that the latitude is not below the minimum allowed value.
	 *
	 * @param latitude the latitude to ensure
	 * @throws LatitudeTooLowException if the latitude is below -90.0
	 */
	private static void ensureLatitudeIsNotTooLow(double latitude) {
		if (latitude < MIN_LATITUDE) {
			throw new LatitudeTooLowException();
		}
	}

	/**
	 * Ensures that the latitude is not above the maximum allowed value.
	 *
	 * @param latitude the latitude to ensure
	 * @throws LatitudeTooHighException if the latitude is above 90.0
	 */
	private static void ensureLatitudeIsNotTooHigh(double latitude) {
		if (latitude > MAX_LATITUDE) {
			throw new LatitudeTooHighException();
		}
	}

	/**
	 * Ensures that the longitude is not below the minimum allowed value.
	 *
	 * @param longitude the longitude to ensure
	 * @throws LongitudeTooLowException if the longitude is below -180.0
	 */
	private static void ensureLongitudeIsNotTooLow(double longitude) {
		if (longitude < MIN_LONGITUDE) {
			throw new LongitudeTooLowException();
		}
	}

	/**
	 * Ensures that the longitude is not above the maximum allowed value.
	 *
	 * @param longitude the longitude to ensure
	 * @throws LongitudeTooHighException if the longitude is above 180.0
	 */
	private static void ensureLongitudeIsNotTooHigh(double longitude) {
		if (longitude > MAX_LONGITUDE) {
			throw new LongitudeTooHighException();
		}
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
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Returns the longitude of the pickup location.
	 *
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

}
