package segundum.infrastructure.rest.sale.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the basic product information in a sale response.
 */
@Schema(description = "Basic product information")
public class ProductBasicInfoResponse {

    /**
     * The product identifier.
     */
    @Schema(description = "Product identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String productId;

    /**
     * The product title.
     */
    @Schema(description = "Product title", example = "iPhone 12")
    private String title;

    /**
     * The product price.
     */
    @Schema(description = "Product price", example = "299.99")
    private Double price;

    /**
     * The pickup location details.
     */
    @Schema(description = "Pickup location details")
    private PickupLocationResponse pickupLocation;

    /**
     * The seller identifier.
     */
    @Schema(description = "Seller identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String sellerId;

    /**
     * Constructs a new empty ProductBasicInfoResponse for deserialization.
     */
    public ProductBasicInfoResponse() {
    }

    /**
     * Constructs a new ProductBasicInfoResponse with the given values.
     *
     * @param productId      the product identifier
     * @param title          the product title
     * @param price          the product price
     * @param pickupLocation the pickup location details
     * @param sellerId       the seller identifier
     */
    public ProductBasicInfoResponse(String productId, String title, Double price,
            PickupLocationResponse pickupLocation, String sellerId) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.pickupLocation = pickupLocation;
        this.sellerId = sellerId;
    }

    /**
     * Returns the product identifier.
     *
     * @return the product identifier
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Returns the product title.
     *
     * @return the product title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the product price.
     *
     * @return the product price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Returns the pickup location details.
     *
     * @return the pickup location details
     */
    public PickupLocationResponse getPickupLocation() {
        return pickupLocation;
    }

    /**
     * Returns the seller identifier.
     *
     * @return the seller identifier
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * Sets the product identifier.
     *
     * @param productId the product identifier
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Sets the product title.
     *
     * @param title the product title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the product price.
     *
     * @param price the product price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Sets the pickup location details.
     *
     * @param pickupLocation the pickup location details
     */
    public void setPickupLocation(PickupLocationResponse pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    /**
     * Sets the seller identifier.
     *
     * @param sellerId the seller identifier
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

}
