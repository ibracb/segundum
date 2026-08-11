package segundum.domain.events;

import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.Sale;
import segundum.domain.models.sale.SaleId;

/**
 * Represents a domain event raised when a sale is cancelled by the seller.
 */
public class SaleCancelledBySeller extends DomainEvent implements SaleEvent {

    /**
     * The identifier of the sale.
     */
    private final SaleId saleId;

    /**
     * The identifier of the product.
     */
    private final ProductId productId;

    /**
     * Constructs a new SaleCancelledBySeller event.
     *
     * @param saleId    the identifier of the sale
     * @param productId the identifier of the product
     */
    public SaleCancelledBySeller(SaleId saleId, ProductId productId) {
        super();
        this.saleId = saleId;
        this.productId = productId;
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

    @Override
    public void dispatch(Sale sale) {
        sale.when(this);
    }

}
