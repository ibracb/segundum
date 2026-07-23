package segundum.application.usecases;

import segundum.application.commands.TakeProductDownCommand;

/**
 * Use case to take a product down from sale.
 */
public interface TakeProductDownUseCase {

	void execute(TakeProductDownCommand command);

}
