package segundum.application.usecases;

import java.util.Optional;

import segundum.application.queries.GetSalePartiesQuery;
import segundum.application.readmodels.sale.SalePartiesReadModel;

/**
 * Represents the use case for retrieving the parties of a sale.
 */
public interface GetSalePartiesUseCase {

    /**
     * Fetches the parties of a sale matching the given query.
     *
     * @param query the query containing the sale identifier
     * @return the parties of the sale, if it exists
     */
    Optional<SalePartiesReadModel> execute(GetSalePartiesQuery query);

}
