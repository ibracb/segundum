package segundum.application.commands;

import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;

/**
 * Represents a command to propose a sale for a product.
 */
public class ProposeSaleCommand {

    /**
     * The identifier of the product to sell.
     */
    private final ProductId productId;

    /**
     * The identifier of the purchaser proposing the sale.
     */
    private final PurchaserId purchaserId;

    /**
     * Constructs a new ProposeSaleCommand with the given values.
     *
     * @param productId   the identifier of the product to sell
     * @param purchaserId the identifier of the purchaser proposing the sale
     */
    public ProposeSaleCommand(ProductId productId, PurchaserId purchaserId) {
        this.productId = productId;
        this.purchaserId = purchaserId;
    }

    /**
     * Returns the identifier of the product to sell.
     *
     * @return the identifier of the product to sell
     */
    public ProductId getProductId() {
        return productId;
    }

    /**
     * Returns the identifier of the purchaser proposing the sale.
     *
     * @return the identifier of the purchaser proposing the sale
     */
    public PurchaserId getPurchaserId() {
        return purchaserId;
    }

}
