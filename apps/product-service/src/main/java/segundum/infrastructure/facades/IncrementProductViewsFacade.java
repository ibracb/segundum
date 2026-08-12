package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.IncrementProductViewsCommand;
import segundum.application.usecases.IncrementProductViewsUseCase;

/**
 * Represents the transaction boundary for incrementing product views.
 */
@Component
public class IncrementProductViewsFacade {

	/**
	 * The use case for incrementing product views.
	 */
	private final IncrementProductViewsUseCase useCase;

	/**
	 * Constructs a new IncrementProductViewsFacade with the given use case.
	 *
	 * @param useCase the use case for incrementing product views
	 */
	public IncrementProductViewsFacade(IncrementProductViewsUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Increments product views within a single transaction.
	 *
	 * @param command the increment product views command
	 */
	@Transactional
	public void run(IncrementProductViewsCommand command) {
		useCase.execute(command);
	}

}
