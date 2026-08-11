package segundum.application.commands;

import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SaleId;

/**
 * Represents a command to cancel a sale by the purchaser.
 */
public class CancelSaleByPurchaserCommand {

    /**
     * The identifier of the sale to cancel.
     */
    private final SaleId saleId;

    /**
     * The identifier of the purchaser cancelling the sale.
     */
    private final PurchaserId purchaserId;

    /**
     * Constructs a new CancelSaleByPurchaserCommand with the given values.
     *
     * @param saleId      the identifier of the sale to cancel
     * @param purchaserId the identifier of the purchaser cancelling the sale
     */
    public CancelSaleByPurchaserCommand(SaleId saleId, PurchaserId purchaserId) {
        this.saleId = saleId;
        this.purchaserId = purchaserId;
    }

    /**
     * Returns the identifier of the sale to cancel.
     *
     * @return the identifier of the sale to cancel
     */
    public SaleId getSaleId() {
        return saleId;
    }

    /**
     * Returns the identifier of the purchaser cancelling the sale.
     *
     * @return the identifier of the purchaser cancelling the sale
     */
    public PurchaserId getPurchaserId() {
        return purchaserId;
    }

}
