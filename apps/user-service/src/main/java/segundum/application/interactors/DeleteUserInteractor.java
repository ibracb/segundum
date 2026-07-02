package segundum.application.interactors;

import segundum.application.usecases.DeleteUserUseCase;
import segundum.domain.events.DomainEventPublisher;
import segundum.domain.events.UserDeleted;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.repositories.UserRepository;
import segundum.utils.factories.EventPublisherFactory;
import segundum.utils.factories.RepositoryFactory;

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
	 * Constructs a new DeleteUserInteractor, initializing the user repository using the RepositoryFactory.
	 */
	public DeleteUserInteractor() {
		this.userRepository = RepositoryFactory.getUserRepository(User.class);
		this.domainEventPublisher = EventPublisherFactory.getPublisher(DomainEventPublisher.class);
	}

	/**
	 * Constructs a new DeleteUserInteractor with the given repository and publisher.
	 *
	 * @param userRepository       The repository for managing users.
	 * @param domainEventPublisher The domain event publisher.
	 */
	DeleteUserInteractor(UserRepository userRepository, DomainEventPublisher domainEventPublisher) {
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
	}
	
	@Override
	public void execute(UserId userId) {
		userRepository.delete(userId);
		domainEventPublisher.publish(new UserDeleted(userId));
	}

}
