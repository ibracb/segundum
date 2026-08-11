package segundum.application.usecases;

import segundum.application.commands.ProposeSaleCommand;
import segundum.domain.models.sale.SaleId;

/**
 * Represents the use case for proposing a sale.
 */
public interface ProposeSaleUseCase {

    /**
     * Proposes a sale for a product.
     *
     * @param command the command containing the product and purchaser information
     * @return the identifier of the proposed sale
     */
    SaleId execute(ProposeSaleCommand command);
}
