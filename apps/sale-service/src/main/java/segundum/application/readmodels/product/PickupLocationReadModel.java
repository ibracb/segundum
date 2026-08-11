package segundum.application.readmodels.product;

/**
 * Represents the pickup location of a product.
 */
public class PickupLocationReadModel {

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
     * Constructs a new PickupLocationReadModel with the given values.
     *
     * @param description the description of the pickup location
     * @param latitude    the latitude of the pickup location
     * @param longitude   the longitude of the pickup location
     */
    public PickupLocationReadModel(String description, double latitude, double longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the description of the pickup location.
     *
     * @return the description of the pickup location
     */
    public String getDescription() { return description; }

    /**
     * Returns the latitude of the pickup location.
     *
     * @return the latitude of the pickup location
     */
    public double getLatitude() { return latitude; }

    /**
     * Returns the longitude of the pickup location.
     *
     * @return the longitude of the pickup location
     */
    public double getLongitude() { return longitude; }

}
