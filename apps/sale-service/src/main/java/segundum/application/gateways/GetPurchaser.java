package segundum.application.gateways;

import segundum.application.readmodels.purchaser.PurchaserReadModel;
import segundum.domain.models.sale.PurchaserId;

/**
 * Represents a gateway for fetching a purchaser.
 */
public interface GetPurchaser {

    /**
     * Fetches a purchaser by its identifier.
     *
     * @param purchaserId the identifier of the purchaser
     * @return the purchaser read model
     */
    PurchaserReadModel fetch(PurchaserId purchaserId);
}
