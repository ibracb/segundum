package segundum.application.usecases;

import segundum.application.commands.PutProductForSaleCommand;

/**
 * Use case to put a product on sale.
 */
public interface PutProductForSaleUseCase {

	void execute(PutProductForSaleCommand command);

}
