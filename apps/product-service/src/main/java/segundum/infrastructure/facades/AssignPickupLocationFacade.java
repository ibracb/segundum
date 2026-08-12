package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.AssignProductPickupLocationCommand;
import segundum.application.usecases.AssignProductPickupLocationUseCase;

/**
 * Represents the transaction boundary for assigning a pickup location to a product.
 */
@Component
public class AssignPickupLocationFacade {

	/**
	 * The use case for assigning a pickup location.
	 */
	private final AssignProductPickupLocationUseCase useCase;

	/**
	 * Constructs a new AssignPickupLocationFacade with the given use case.
	 *
	 * @param useCase the use case for assigning a pickup location
	 */
	public AssignPickupLocationFacade(AssignProductPickupLocationUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Assigns a pickup location within a single transaction.
	 *
	 * @param command the assign pickup location command
	 */
	@Transactional
	public void run(AssignProductPickupLocationCommand command) {
		useCase.execute(command);
	}

}
