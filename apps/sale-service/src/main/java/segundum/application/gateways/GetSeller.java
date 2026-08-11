package segundum.application.gateways;

import segundum.application.readmodels.seller.SellerReadModel;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a gateway for fetching a seller.
 */
public interface GetSeller {

    /**
     * Fetches a seller by its identifier.
     *
     * @param sellerId the identifier of the seller
     * @return the seller read model
     */
    SellerReadModel fetch(SellerId sellerId);
}
