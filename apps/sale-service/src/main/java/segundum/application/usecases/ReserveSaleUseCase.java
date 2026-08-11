package segundum.application.usecases;

import segundum.application.commands.ReserveSaleCommand;

/**
 * Represents the use case for reserving a sale by the seller.
 */
public interface ReserveSaleUseCase {

    /**
     * Reserves a sale on behalf of the seller.
     *
     * @param command the command containing the sale and seller information
     */
    void execute(ReserveSaleCommand command);
}
