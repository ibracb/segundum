package segundum.application.finders;

import java.util.Optional;

import segundum.application.readmodels.seller.SellerReadModel;
import segundum.domain.models.seller.SellerId;

/**
 * Finder interface for querying seller read models.
 */
public interface SellerFinder {

    /**
     * Finds a seller by its identifier.
     *
     * @param id the seller identifier
     * @return the seller read model, if it exists
     */
    Optional<SellerReadModel> findById(SellerId id);

}
