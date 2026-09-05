package segundum.application.readmodels.sale;

import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.application.readmodels.purchaser.PurchaserReadModel;
import segundum.application.readmodels.seller.SellerReadModel;

/**
 * Represents the full detail of a sale.
 */
public class SaleDetailReadModel {

    private final String saleId;
    private final String status;
    private final String datetime;
    private final ProductBasicInfoReadModel product;
    private final SellerReadModel seller;
    private final PurchaserReadModel purchaser;

    /**
     * Constructs a new SaleDetailReadModel with the given values.
     *
     * @param saleId    the identifier of the sale
     * @param status    the status of the sale
     * @param datetime  the date and time of the sale
     * @param product   the product of the sale
     * @param seller    the seller of the sale
     * @param purchaser the purchaser of the sale
     */
    public SaleDetailReadModel(String saleId, String status, String datetime,
            ProductBasicInfoReadModel product, SellerReadModel seller, PurchaserReadModel purchaser) {
        this.saleId = saleId;
        this.status = status;
        this.datetime = datetime;
        this.product = product;
        this.seller = seller;
        this.purchaser = purchaser;
    }

    public String getSaleId() { return saleId; }
    public String getStatus() { return status; }
    public String getDatetime() { return datetime; }
    public ProductBasicInfoReadModel getProduct() { return product; }
    public SellerReadModel getSeller() { return seller; }
    public PurchaserReadModel getPurchaser() { return purchaser; }

}
