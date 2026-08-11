package segundum.application.readmodels.product;

/**
 * Represents the basic information of a product used by the read side.
 */
public class ProductBasicInfo {

    /**
     * The identifier of the product.
     */
    private final String productId;
    /**
     * The title of the product.
     */
    private final String title;
    /**
     * The price of the product.
     */
    private final Double price;
    /**
     * The pickup location of the product.
     */
    private final PickupLocationReadModel pickupLocation;
    /**
     * The identifier of the seller of the product.
     */
    private final String sellerId;
    /**
     * The sale status of the product.
     */
    private final String saleStatus;

    /**
     * Constructs a new ProductBasicInfo with the given data.
     *
     * @param productId the product identifier
     * @param title the product title
     * @param price the product price
     * @param pickupLocation the pickup location
     * @param sellerId the seller identifier
     * @param saleStatus the sale status
     */
    public ProductBasicInfo(String productId, String title, Double price,
            PickupLocationReadModel pickupLocation, String sellerId, String saleStatus) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.pickupLocation = pickupLocation;
        this.sellerId = sellerId;
        this.saleStatus = saleStatus;
    }

    /**
     * Returns the identifier of the product.
     *
     * @return the product identifier
     */
    public String getProductId() { return productId; }
    /**
     * Returns the title of the product.
     *
     * @return the product title
     */
    public String getTitle() { return title; }
    /**
     * Returns the price of the product.
     *
     * @return the product price
     */
    public Double getPrice() { return price; }
    /**
     * Returns the pickup location of the product.
     *
     * @return the pickup location
     */
    public PickupLocationReadModel getPickupLocation() { return pickupLocation; }
    /**
     * Returns the identifier of the seller of the product.
     *
     * @return the seller identifier
     */
    public String getSellerId() { return sellerId; }
    /**
     * Returns the sale status of the product.
     *
     * @return the sale status
     */
    public String getSaleStatus() { return saleStatus; }

}
