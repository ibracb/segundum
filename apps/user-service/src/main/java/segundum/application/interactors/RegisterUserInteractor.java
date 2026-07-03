package segundum.application.interactors;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.domain.events.DomainEventPublisher;
import segundum.domain.events.UserRegistered;
import segundum.domain.exceptions.email.EmailAlreadyExistsException;
import segundum.domain.exceptions.phone.PhoneAlreadyExistsException;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserFactory;
import segundum.domain.repositories.UserRepository;

/**
 * Represents the interactor for creating a new user in the system.
 */
public class RegisterUserInteractor implements RegisterUserUseCase {

	/**
	 * The repository for managing users.
	 */
	private final UserRepository userRepository;

	/**
	 * The domain event publisher.
	 */
	private final DomainEventPublisher domainEventPublisher;

	/**
	 * Constructs a new RegisterUserInteractor with the given repository and publisher.
	 *
	 * @param userRepository       The repository for managing users.
	 * @param domainEventPublisher The domain event publisher.
	 */
	public RegisterUserInteractor(UserRepository userRepository, DomainEventPublisher domainEventPublisher) {
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public User execute(RegisterUserCommand command) {
		if (userRepository.existsByEmail(command.getEmail())) {
			throw new EmailAlreadyExistsException(command.getEmail().getValue());
		}
		if (userRepository.existsByPhone(command.getPhone())) {
			throw new PhoneAlreadyExistsException(command.getPhone().getValue());
		}
		User user = UserFactory.createUser(
				command.getName(),
				command.getSurname(),
				command.getEmail(),
				command.getPassword(),
				command.getBirthdate(),
				command.getPhone());
		userRepository.create(user);
		domainEventPublisher.publish(new UserRegistered(user.getUserId(), user.getName(), user.getSurname(), user.getEmail()));
		return user;
	}
}
