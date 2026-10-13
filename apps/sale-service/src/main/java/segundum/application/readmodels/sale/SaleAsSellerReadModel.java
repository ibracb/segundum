package segundum.application.readmodels.sale;

import segundum.application.dtos.product.ProductBasicInfo;
import segundum.application.dtos.purchaser.PurchaserData;

/**
 * Represents a sale as seen by the seller.
 */
public class SaleAsSellerReadModel {

    /**
     * The identifier of the sale.
     */
    private final String saleId;

    /**
     * The status of the sale.
     */
    private final String status;

    /**
     * The date and time of the sale.
     */
    private final String datetime;

    /**
     * The product of the sale.
     */
    private final ProductBasicInfo product;

    /**
     * The purchaser of the sale.
     */
    private final PurchaserData purchaser;

    /**
     * Constructs a new SaleAsSellerReadModel with the given values.
     *
     * @param saleId    the identifier of the sale
     * @param status    the status of the sale
     * @param datetime  the date and time of the sale
     * @param product   the product of the sale
     * @param purchaser the purchaser of the sale
     */
    public SaleAsSellerReadModel(String saleId, String status, String datetime,
            ProductBasicInfo product, PurchaserData purchaser) {
        this.saleId = saleId;
        this.status = status;
        this.datetime = datetime;
        this.product = product;
        this.purchaser = purchaser;
    }

    /**
     * Returns the identifier of the sale.
     *
     * @return the identifier of the sale
     */
    public String getSaleId() { return saleId; }

    /**
     * Returns the status of the sale.
     *
     * @return the status of the sale
     */
    public String getStatus() { return status; }

    /**
     * Returns the date and time of the sale.
     *
     * @return the date and time of the sale
     */
    public String getDatetime() { return datetime; }

    /**
     * Returns the product of the sale.
     *
     * @return the product of the sale
     */
    public ProductBasicInfo getProduct() { return product; }

    /**
     * Returns the purchaser of the sale.
     *
     * @return the purchaser of the sale
     */
    public PurchaserData getPurchaser() { return purchaser; }

}
