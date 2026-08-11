package segundum.application.commands;

import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a command to reject a sale by the seller.
 */
public class RejectSaleCommand {

    /**
     * The identifier of the sale to reject.
     */
    private final SaleId saleId;

    /**
     * The identifier of the seller rejecting the sale.
     */
    private final SellerId sellerId;

    /**
     * Constructs a new RejectSaleCommand with the given values.
     *
     * @param saleId   the identifier of the sale to reject
     * @param sellerId the identifier of the seller rejecting the sale
     */
    public RejectSaleCommand(SaleId saleId, SellerId sellerId) {
        this.saleId = saleId;
        this.sellerId = sellerId;
    }

    /**
     * Returns the identifier of the sale to reject.
     *
     * @return the identifier of the sale to reject
     */
    public SaleId getSaleId() {
        return saleId;
    }

    /**
     * Returns the identifier of the seller rejecting the sale.
     *
     * @return the identifier of the seller rejecting the sale
     */
    public SellerId getSellerId() {
        return sellerId;
    }

}
