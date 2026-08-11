package segundum.infrastructure.rest.sale.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the pickup location details in a sale response.
 */
@Schema(description = "Pickup location details")
public class PickupLocationResponse {

    /**
     * The pickup location description.
     */
    @Schema(description = "Pickup location description", example = "At the main entrance")
    private String description;

    /**
     * The pickup location latitude.
     */
    @Schema(description = "Pickup location latitude", example = "37.9893")
    private double latitude;

    /**
     * The pickup location longitude.
     */
    @Schema(description = "Pickup location longitude", example = "-1.1308")
    private double longitude;

    /**
     * Constructs a new empty PickupLocationResponse for deserialization.
     */
    public PickupLocationResponse() {
    }

    /**
     * Constructs a new PickupLocationResponse with the given values.
     *
     * @param description the pickup location description
     * @param latitude    the pickup location latitude
     * @param longitude   the pickup location longitude
     */
    public PickupLocationResponse(String description, double latitude, double longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the pickup location description.
     *
     * @return the pickup location description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the pickup location latitude.
     *
     * @return the pickup location latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the pickup location longitude.
     *
     * @return the pickup location longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the pickup location description.
     *
     * @param description the pickup location description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the pickup location latitude.
     *
     * @param latitude the pickup location latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Sets the pickup location longitude.
     *
     * @param longitude the pickup location longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
