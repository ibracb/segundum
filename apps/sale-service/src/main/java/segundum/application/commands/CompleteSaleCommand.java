package segundum.application.commands;

import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a command to complete a sale by the seller.
 */
public class CompleteSaleCommand {

    /**
     * The identifier of the sale to complete.
     */
    private final SaleId saleId;

    /**
     * The identifier of the seller completing the sale.
     */
    private final SellerId sellerId;

    /**
     * Constructs a new CompleteSaleCommand with the given values.
     *
     * @param saleId   the identifier of the sale to complete
     * @param sellerId the identifier of the seller completing the sale
     */
    public CompleteSaleCommand(SaleId saleId, SellerId sellerId) {
        this.saleId = saleId;
        this.sellerId = sellerId;
    }

    /**
     * Returns the identifier of the sale to complete.
     *
     * @return the identifier of the sale to complete
     */
    public SaleId getSaleId() {
        return saleId;
    }

    /**
     * Returns the identifier of the seller completing the sale.
     *
     * @return the identifier of the seller completing the sale
     */
    public SellerId getSellerId() {
        return sellerId;
    }

}
