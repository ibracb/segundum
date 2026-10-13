package segundum.application.queries;

import segundum.domain.models.sale.SaleId;

/**
 * Represents a query for retrieving the parties of a sale.
 */
public class GetSalePartiesQuery {

    private final SaleId saleId;

    /**
     * Constructs a new GetSalePartiesQuery with the given sale identifier.
     *
     * @param saleId the identifier of the sale
     */
    public GetSalePartiesQuery(SaleId saleId) {
        this.saleId = saleId;
    }

    public SaleId getSaleId() { return saleId; }

}
