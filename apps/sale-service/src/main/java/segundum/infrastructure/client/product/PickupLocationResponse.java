package segundum.infrastructure.client.product;

/**
 * Represents the pickup location response of a product.
 */
public class PickupLocationResponse {

    /**
     * The description of the pickup location.
     */
    private String description;

    /**
     * The latitude of the pickup location.
     */
    private double latitude;

    /**
     * The longitude of the pickup location.
     */
    private double longitude;

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

    /**
     * Sets the description of the pickup location.
     *
     * @param description the description of the pickup location
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Sets the latitude of the pickup location.
     *
     * @param latitude the latitude of the pickup location
     */
    public void setLatitude(double latitude) { this.latitude = latitude; }

    /**
     * Sets the longitude of the pickup location.
     *
     * @param longitude the longitude of the pickup location
     */
    public void setLongitude(double longitude) { this.longitude = longitude; }

}
