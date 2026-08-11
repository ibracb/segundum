package segundum.application.usecases;

import segundum.application.commands.CompleteSaleCommand;

/**
 * Represents the use case for completing a sale by the seller.
 */
public interface CompleteSaleUseCase {

    /**
     * Completes a sale on behalf of the seller.
     *
     * @param command the command containing the sale and seller information
     */
    void execute(CompleteSaleCommand command);
}
