package segundum.application.usecases.interactors;

import segundum.application.commands.DeleteUserCommand;
import segundum.application.usecases.DeleteUserUseCase;
import segundum.domain.events.UserDeleted;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.UserRepository;

/**
 * Represents the interactor for deleting an existing user in the system.
 */
public class DeleteUserInteractor implements DeleteUserUseCase {
	
	/**
	 * The repository for managing users.
	 */
	private final UserRepository userRepository;

	/**
	 * The domain event publisher.
	 */
	private final DomainEventPublisher domainEventPublisher;
	
	/**
	 * Constructs a new DeleteUserInteractor with the given repository and publisher.
	 *
	 * @param userRepository       The repository for managing users.
	 * @param domainEventPublisher The domain event publisher.
	 */
	public DeleteUserInteractor(UserRepository userRepository, DomainEventPublisher domainEventPublisher) {
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
	}
	
	@Override
	public void execute(DeleteUserCommand command) {
		if (!userRepository.existsById(command.getUserId())) {
			throw new EntityNotFoundException("User", command.getUserId().getValue().toString());
		}
		userRepository.delete(command.getUserId());
		domainEventPublisher.publish(new UserDeleted(command.getUserId()));
	}

}
