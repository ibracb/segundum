package segundum.application.usecases.interactors;

import segundum.application.commands.DeactivateUserCommand;
import segundum.application.usecases.DeactivateUserUseCase;
import segundum.domain.events.UserDeactivated;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.user.User;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.UserRepository;

/**
 * Represents the interactor for deactivating an existing user in the system.
 */
public class DeactivateUserInteractor implements DeactivateUserUseCase {
	
	/**
	 * The repository for managing users.
	 */
	private final UserRepository userRepository;

	/**
	 * The domain event publisher.
	 */
	private final DomainEventPublisher domainEventPublisher;
	
	/**
	 * Constructs a new DeactivateUserInteractor with the given repository and publisher.
	 *
	 * @param userRepository       The repository for managing users.
	 * @param domainEventPublisher The domain event publisher.
	 */
	public DeactivateUserInteractor(UserRepository userRepository, DomainEventPublisher domainEventPublisher) {
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
	}
	
	@Override
	public void execute(DeactivateUserCommand command) {
		User user = userRepository.findById(command.getUserId()).orElseThrow(
				() -> new EntityNotFoundException("User", command.getUserId().getValue().toString()));
		user.deactivate();
		userRepository.update(user);
		domainEventPublisher.publish(new UserDeactivated(command.getUserId()));
	}

}
