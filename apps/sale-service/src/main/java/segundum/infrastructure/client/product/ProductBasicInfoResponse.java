package segundum.infrastructure.client.product;

/**
 * Represents the basic information response of a product.
 */
public class ProductBasicInfoResponse {

    /**
     * The identifier of the product.
     */
    private String productId;

    /**
     * The title of the product.
     */
    private String title;

    /**
     * The price of the product.
     */
    private Double price;

    /**
     * The pickup location of the product.
     */
    private PickupLocationResponse pickupLocation;

    /**
     * The identifier of the seller of the product.
     */
    private String sellerId;

    /**
     * The sale status of the product.
     */
    private String saleStatus;

    /**
     * Returns the identifier of the product.
     *
     * @return the identifier of the product
     */
    public String getProductId() { return productId; }

    /**
     * Returns the title of the product.
     *
     * @return the title of the product
     */
    public String getTitle() { return title; }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public Double getPrice() { return price; }

    /**
     * Returns the pickup location of the product.
     *
     * @return the pickup location of the product
     */
    public PickupLocationResponse getPickupLocation() { return pickupLocation; }

    /**
     * Returns the identifier of the seller of the product.
     *
     * @return the identifier of the seller of the product
     */
    public String getSellerId() { return sellerId; }

    /**
     * Returns the sale status of the product.
     *
     * @return the sale status of the product
     */
    public String getSaleStatus() { return saleStatus; }

    /**
     * Sets the identifier of the product.
     *
     * @param productId the identifier of the product
     */
    public void setProductId(String productId) { this.productId = productId; }

    /**
     * Sets the title of the product.
     *
     * @param title the title of the product
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */
    public void setPrice(Double price) { this.price = price; }

    /**
     * Sets the pickup location of the product.
     *
     * @param pickupLocation the pickup location of the product
     */
    public void setPickupLocation(PickupLocationResponse pickupLocation) { this.pickupLocation = pickupLocation; }

    /**
     * Sets the identifier of the seller of the product.
     *
     * @param sellerId the identifier of the seller of the product
     */
    public void setSellerId(String sellerId) { this.sellerId = sellerId; }

    /**
     * Sets the sale status of the product.
     *
     * @param saleStatus the sale status of the product
     */
    public void setSaleStatus(String saleStatus) { this.saleStatus = saleStatus; }

}
