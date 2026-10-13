package segundum.application.gateways;

import segundum.application.dtos.seller.SellerData;
import segundum.domain.models.sale.SellerId;

/**
 * Represents a gateway for fetching a seller.
 */
public interface GetSeller {

    /**
     * Fetches a seller by its identifier.
     *
     * @param sellerId the identifier of the seller
     * @return the seller data
     */
    SellerData fetch(SellerId sellerId);
}
