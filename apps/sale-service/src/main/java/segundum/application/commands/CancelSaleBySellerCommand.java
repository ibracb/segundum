package segundum.application.commands;

import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a command to cancel a sale by the seller.
 */
public class CancelSaleBySellerCommand {

    /**
     * The identifier of the sale to cancel.
     */
    private final SaleId saleId;

    /**
     * The identifier of the seller cancelling the sale.
     */
    private final SellerId sellerId;

    /**
     * Constructs a new CancelSaleBySellerCommand with the given values.
     *
     * @param saleId   the identifier of the sale to cancel
     * @param sellerId the identifier of the seller cancelling the sale
     */
    public CancelSaleBySellerCommand(SaleId saleId, SellerId sellerId) {
        this.saleId = saleId;
        this.sellerId = sellerId;
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
     * Returns the identifier of the seller cancelling the sale.
     *
     * @return the identifier of the seller cancelling the sale
     */
    public SellerId getSellerId() {
        return sellerId;
    }

}
