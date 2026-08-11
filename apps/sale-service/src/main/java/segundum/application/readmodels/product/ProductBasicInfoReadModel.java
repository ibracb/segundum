package segundum.application.readmodels.product;

/**
 * Represents the basic information of a product.
 */
public class ProductBasicInfoReadModel {

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
     * Constructs a new ProductBasicInfoReadModel with the given values.
     *
     * @param productId      the identifier of the product
     * @param title          the title of the product
     * @param price          the price of the product
     * @param pickupLocation the pickup location of the product
     * @param sellerId       the identifier of the seller of the product
     * @param saleStatus     the sale status of the product
     */
    public ProductBasicInfoReadModel(String productId, String title, Double price,
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
    public PickupLocationReadModel getPickupLocation() { return pickupLocation; }

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

}
