package segundum.application.usecases;

import segundum.application.commands.RejectSaleCommand;

/**
 * Represents the use case for rejecting a sale by the seller.
 */
public interface RejectSaleUseCase {

    /**
     * Rejects a sale on behalf of the seller.
     *
     * @param command the command containing the sale and seller information
     */
    void execute(RejectSaleCommand command);
}
