package segundum.domain.events;

import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.Sale;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a domain event raised when a sale is completed.
 */
public class SaleCompleted extends DomainEvent implements SaleEvent {

    /**
     * The identifier of the sale.
     */
    private final SaleId saleId;

    /**
     * The identifier of the product.
     */
    private final ProductId productId;

    /**
     * The identifier of the seller.
     */
    private final SellerId sellerId;

    /**
     * The identifier of the purchaser.
     */
    private final PurchaserId purchaserId;

    /**
     * Constructs a new SaleCompleted event.
     *
     * @param saleId      the identifier of the sale
     * @param productId   the identifier of the product
     * @param sellerId    the identifier of the seller
     * @param purchaserId the identifier of the purchaser
     */
    public SaleCompleted(SaleId saleId, ProductId productId, SellerId sellerId, PurchaserId purchaserId) {
        super();
        this.saleId = saleId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.purchaserId = purchaserId;
    }

    /**
     * Returns the identifier of the sale.
     *
     * @return the identifier of the sale
     */
    public SaleId getSaleId() {
        return saleId;
    }

    /**
     * Returns the identifier of the product.
     *
     * @return the identifier of the product
     */
    public ProductId getProductId() {
        return productId;
    }

    /**
     * Returns the identifier of the seller.
     *
     * @return the identifier of the seller
     */
    public SellerId getSellerId() {
        return sellerId;
    }

    /**
     * Returns the identifier of the purchaser.
     *
     * @return the identifier of the purchaser
     */
    public PurchaserId getPurchaserId() {
        return purchaserId;
    }

    @Override
    public void dispatch(Sale sale) {
        sale.when(this);
    }

}
