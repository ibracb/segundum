package segundum.application.readmodels.sale;

/**
 * Represents the parties of a sale: its purchaser and its seller, along with
 * the current status of the sale.
 */
public class SalePartiesReadModel {

    private final String saleId;
    private final String purchaserId;
    private final String sellerId;
    private final String status;

    /**
     * Constructs a new SalePartiesReadModel with the given values.
     *
     * @param saleId      the identifier of the sale
     * @param purchaserId the identifier of the purchaser
     * @param sellerId    the identifier of the seller
     * @param status      the current status of the sale
     */
    public SalePartiesReadModel(String saleId, String purchaserId, String sellerId, String status) {
        this.saleId = saleId;
        this.purchaserId = purchaserId;
        this.sellerId = sellerId;
        this.status = status;
    }

    public String getSaleId() {
        return saleId;
    }
    
    public String getPurchaserId() {
        return purchaserId;
    }
    
    public String getSellerId() {
        return sellerId;
    }

    public String getStatus() {
        return status;
    }

}
