package segundum.application.usecases;

import segundum.application.commands.CancelSaleByPurchaserCommand;

/**
 * Represents the use case for cancelling a sale by the purchaser.
 */
public interface CancelSaleByPurchaserUseCase {

	/**
	 * Cancels a sale on behalf of the purchaser.
	 *
	 * @param command the command containing the sale and purchaser information
	 */
	void execute(CancelSaleByPurchaserCommand command);

}
