package segundum.application.usecases;

import segundum.application.commands.CancelSaleBySellerCommand;

/**
 * Represents the use case for cancelling a sale by the seller.
 */
public interface CancelSaleBySellerUseCase {

    /**
     * Cancels a sale on behalf of the seller.
     *
     * @param command the command containing the sale and seller information
     */
    void execute(CancelSaleBySellerCommand command);
}
