package segundum.application.readmodels.sale;

import segundum.application.dtos.product.ProductBasicInfo;
import segundum.application.dtos.purchaser.PurchaserData;
import segundum.application.dtos.seller.SellerData;

/**
 * Represents the full detail of a sale.
 */
public class SaleDetailReadModel {

    private final String saleId;
    private final String status;
    private final String datetime;
    private final ProductBasicInfo product;
    private final SellerData seller;
    private final PurchaserData purchaser;

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
            ProductBasicInfo product, SellerData seller, PurchaserData purchaser) {
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
    public ProductBasicInfo getProduct() { return product; }
    public SellerData getSeller() { return seller; }
    public PurchaserData getPurchaser() { return purchaser; }

}
