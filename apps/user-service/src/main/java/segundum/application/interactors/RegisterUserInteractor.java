package segundum.application.interactors;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.domain.exceptions.email.EmailAlreadyExistsException;
import segundum.domain.exceptions.phone.PhoneAlreadyExistsException;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserFactory;
import segundum.domain.repositories.UserRepository;
import segundum.utils.factories.RepositoryFactory;

/**
 * Represents the interactor for creating a new user in the system.
 */
public class RegisterUserInteractor implements RegisterUserUseCase {

	/**
	 * The repository for managing users.
	 */
	private final UserRepository userRepository;

	/**
	 * Constructs a new RegisterUserInteractor, initializing the user repository using the RepositoryFactory.
	 */
	public RegisterUserInteractor() {
		this.userRepository = RepositoryFactory.getUserRepository(User.class);
	}

	/**
	 * Constructs a new RegisterUserInteractor with the given repository.
	 *
	 * @param userRepository The repository for managing users.
	 */
	RegisterUserInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
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
		return userRepository.create(user);
	}
}
