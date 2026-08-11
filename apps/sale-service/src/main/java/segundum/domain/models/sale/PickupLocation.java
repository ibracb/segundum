package segundum.domain.models.sale;

import java.util.Objects;

import segundum.domain.exceptions.sale.pickup.description.DescriptionBlankException;
import segundum.domain.exceptions.sale.pickup.description.DescriptionNullException;
import segundum.domain.exceptions.sale.pickup.description.DescriptionTooLongException;
import segundum.domain.exceptions.sale.pickup.latitude.LatitudeTooHighException;
import segundum.domain.exceptions.sale.pickup.latitude.LatitudeTooLowException;
import segundum.domain.exceptions.sale.pickup.longitude.LongitudeTooHighException;
import segundum.domain.exceptions.sale.pickup.longitude.LongitudeTooLowException;

/**
 * Represents the pickup location of a sale.
 */
public class PickupLocation {

	/**
	 * The maximum allowed length for the description.
	 */
    private static final int MAX_DESCRIPTION_LENGTH = 500;
	/**
	 * The minimum allowed latitude value.
	 */
    private static final double MIN_LATITUDE = -90.0;
	/**
	 * The maximum allowed latitude value.
	 */
    private static final double MAX_LATITUDE = 90.0;
	/**
	 * The minimum allowed longitude value.
	 */
    private static final double MIN_LONGITUDE = -180.0;
	/**
	 * The maximum allowed longitude value.
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
	 * Constructs a new PickupLocation object with the given values.
	 *
	 * @param description the description of the pickup location
	 * @param latitude the latitude of the pickup location
	 * @param longitude the longitude of the pickup location
	 * @throws DescriptionNullException if the description is null
	 * @throws DescriptionBlankException if the description is blank
	 * @throws DescriptionTooLongException if the description exceeds the maximum length
	 * @throws LatitudeTooLowException if the latitude is below the minimum value
	 * @throws LatitudeTooHighException if the latitude is above the maximum value
	 * @throws LongitudeTooLowException if the longitude is below the minimum value
	 * @throws LongitudeTooHighException if the longitude is above the maximum value
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
	 * Ensures that the given description is not null.
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
	 * Ensures that the given description is not blank (i.e., not empty or only whitespace).
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
	 * Ensures that the given description does not exceed the maximum allowed length.
	 *
	 * @param description the description to ensure
	 * @throws DescriptionTooLongException if the description exceeds the maximum length
	 */
    private static void ensureDescriptionIsWithinMaxLength(String description) {
        if (description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new DescriptionTooLongException();
        }
    }

	/**
	 * Ensures that the given latitude is not too low.
	 *
	 * @param latitude the latitude to ensure
	 * @throws LatitudeTooLowException if the latitude is below the minimum value
	 */
    private static void ensureLatitudeIsNotTooLow(double latitude) {
        if (latitude < MIN_LATITUDE) {
            throw new LatitudeTooLowException();
        }
    }

	/**
	 * Ensures that the given latitude is not too high.
	 *
	 * @param latitude the latitude to ensure
	 * @throws LatitudeTooHighException if the latitude is above the maximum value
	 */
    private static void ensureLatitudeIsNotTooHigh(double latitude) {
        if (latitude > MAX_LATITUDE) {
            throw new LatitudeTooHighException();
        }
    }

	/**
	 * Ensures that the given longitude is not too low.
	 *
	 * @param longitude the longitude to ensure
	 * @throws LongitudeTooLowException if the longitude is below the minimum value
	 */
    private static void ensureLongitudeIsNotTooLow(double longitude) {
        if (longitude < MIN_LONGITUDE) {
            throw new LongitudeTooLowException();
        }
    }

	/**
	 * Ensures that the given longitude is not too high.
	 *
	 * @param longitude the longitude to ensure
	 * @throws LongitudeTooHighException if the longitude is above the maximum value
	 */
    private static void ensureLongitudeIsNotTooHigh(double longitude) {
        if (longitude > MAX_LONGITUDE) {
            throw new LongitudeTooHighException();
        }
    }

	/**
	 * Returns the description of the pickup location.
	 *
	 * @return the description of the pickup location
	 */
    public String getDescription() {
        return description;
    }

	/**
	 * Returns the latitude of the pickup location.
	 *
	 * @return the latitude of the pickup location
	 */
    public double getLatitude() {
        return latitude;
    }

	/**
	 * Returns the longitude of the pickup location.
	 *
	 * @return the longitude of the pickup location
	 */
    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickupLocation that = (PickupLocation) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0 &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, latitude, longitude);
    }

}
