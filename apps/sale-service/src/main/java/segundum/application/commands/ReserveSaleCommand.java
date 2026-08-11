package segundum.application.commands;

import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a command to reserve a sale by the seller.
 */
public class ReserveSaleCommand {

    /**
     * The identifier of the sale to reserve.
     */
    private final SaleId saleId;

    /**
     * The identifier of the seller reserving the sale.
     */
    private final SellerId sellerId;

    /**
     * Constructs a new ReserveSaleCommand with the given values.
     *
     * @param saleId   the identifier of the sale to reserve
     * @param sellerId the identifier of the seller reserving the sale
     */
    public ReserveSaleCommand(SaleId saleId, SellerId sellerId) {
        this.saleId = saleId;
        this.sellerId = sellerId;
    }

    /**
     * Returns the identifier of the sale to reserve.
     *
     * @return the identifier of the sale to reserve
     */
    public SaleId getSaleId() {
        return saleId;
    }

    /**
     * Returns the identifier of the seller reserving the sale.
     *
     * @return the identifier of the seller reserving the sale
     */
    public SellerId getSellerId() {
        return sellerId;
    }

}
