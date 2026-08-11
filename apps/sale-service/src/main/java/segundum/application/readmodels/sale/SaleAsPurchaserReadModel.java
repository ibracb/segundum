package segundum.application.readmodels.sale;

import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.application.readmodels.seller.SellerReadModel;

/**
 * Represents a sale as seen by the purchaser.
 */
public class SaleAsPurchaserReadModel {

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
    private final ProductBasicInfoReadModel product;

    /**
     * The seller of the sale.
     */
    private final SellerReadModel seller;

    /**
     * Constructs a new SaleAsPurchaserReadModel with the given values.
     *
     * @param saleId   the identifier of the sale
     * @param status   the status of the sale
     * @param datetime the date and time of the sale
     * @param product  the product of the sale
     * @param seller   the seller of the sale
     */
    public SaleAsPurchaserReadModel(String saleId, String status, String datetime,
            ProductBasicInfoReadModel product, SellerReadModel seller) {
        this.saleId = saleId;
        this.status = status;
        this.datetime = datetime;
        this.product = product;
        this.seller = seller;
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
    public ProductBasicInfoReadModel getProduct() { return product; }

    /**
     * Returns the seller of the sale.
     *
     * @return the seller of the sale
     */
    public SellerReadModel getSeller() { return seller; }

}
