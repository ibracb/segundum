package segundum.application.usecases.interactors;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.domain.events.UserRegistered;
import segundum.domain.exceptions.user.email.EmailAlreadyExistsException;
import segundum.domain.exceptions.user.phone.PhoneAlreadyExistsException;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserFactory;
import segundum.application.outbound.DomainEventPublisher;
import segundum.application.outbound.PasswordHasher;
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
	 * The password hasher for securing passwords.
	 */
	private final PasswordHasher passwordHasher;

	/**
	 * Constructs a new RegisterUserInteractor with the given dependencies.
	 *
	 * @param userRepository       The repository for managing users.
	 * @param domainEventPublisher The domain event publisher.
	 * @param passwordHasher       The password hasher for securing passwords.
	 */
	public RegisterUserInteractor(UserRepository userRepository, DomainEventPublisher domainEventPublisher,
			PasswordHasher passwordHasher) {
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.passwordHasher = passwordHasher;
	}

	@Override
	public User execute(RegisterUserCommand command) {
		if (userRepository.existsByEmail(command.getEmail())) {
			throw new EmailAlreadyExistsException(command.getEmail().getValue());
		}
		if (userRepository.existsByPhone(command.getPhone())) {
			throw new PhoneAlreadyExistsException(command.getPhone().getValue());
		}
		String hashedPassword = passwordHasher.hash(command.getPassword());
		User user = UserFactory.create(
				command.getName(),
				command.getSurname(),
				command.getEmail(),
				Password.hashed(hashedPassword),
				command.getBirthdate(),
				command.getPhone());
		userRepository.create(user);
		domainEventPublisher.publish(new UserRegistered(user.getUserId(), user.getName(), user.getSurname(), user.getEmail()));
		return user;
	}
}
