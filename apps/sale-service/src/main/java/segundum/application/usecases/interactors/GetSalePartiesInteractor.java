package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.finders.SaleFinder;
import segundum.application.queries.GetSalePartiesQuery;
import segundum.application.readmodels.sale.SalePartiesReadModel;
import segundum.application.usecases.GetSalePartiesUseCase;

/**
 * Represents the interactor for retrieving the parties of a sale.
 */
public class GetSalePartiesInteractor implements GetSalePartiesUseCase {

    /**
     * The repository for reading sales from the read store.
     */
    private final SaleFinder saleFinder;

    /**
     * Constructs a new GetSalePartiesInteractor with the given dependency.
     *
     * @param saleFinder the repository for reading sales from the read store
     */
    public GetSalePartiesInteractor(SaleFinder saleFinder) {
        this.saleFinder = saleFinder;
    }

    @Override
    public Optional<SalePartiesReadModel> execute(GetSalePartiesQuery query) {
        return saleFinder.findPartiesBySaleId(query.getSaleId());
    }

}
