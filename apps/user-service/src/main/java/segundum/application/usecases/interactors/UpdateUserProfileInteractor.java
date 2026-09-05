package segundum.application.usecases.interactors;

import segundum.application.commands.UpdateUserCommand;
import segundum.application.usecases.UpdateUserProfileUseCase;
import segundum.domain.events.UserUpdated;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.user.phone.PhoneAlreadyExistsException;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.User;
import segundum.application.outbound.DomainEventPublisher;
import segundum.application.outbound.PasswordHasher;
import segundum.domain.repositories.UserRepository;

/**
 * Represents the interactor for updating an existing user in the system.
 */
public class UpdateUserProfileInteractor implements UpdateUserProfileUseCase {

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
	 * Constructs a new UpdateUserProfileInteractor with the given dependencies.
	 *
	 * @param userRepository       The repository for managing users.
	 * @param domainEventPublisher The domain event publisher.
	 * @param passwordHasher       The password hasher for securing passwords.
	 */
	public UpdateUserProfileInteractor(UserRepository userRepository, DomainEventPublisher domainEventPublisher,
			PasswordHasher passwordHasher) {
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.passwordHasher = passwordHasher;
	}

	@Override
	public User execute(UpdateUserCommand command) {
		User user = userRepository.findById(command.getUserId()).orElseThrow(
				() -> new EntityNotFoundException("User", command.getUserId().getValue().toString()));
		if (command.getName() != null) {
			user.changeName(command.getName());
		}
		if (command.getSurname() != null) {
			user.changeSurname(command.getSurname());
		}
		if (command.getPassword() != null) {
			String hashedPassword = passwordHasher.hash(command.getPassword());
			user.changePassword(Password.hashed(hashedPassword));
		}
		if (command.getPhone() != null) {
			assertPhoneNotAlreadyTaken(command.getPhone(), user.getPhone());
			user.changePhone(command.getPhone());
		}
		userRepository.update(user);
		domainEventPublisher.publish(new UserUpdated(user.getUserId(), user.getName(),
				user.getSurname()));
		return user;
	}

	/**
	 * Asserts that the new phone number is not already taken by another user.
	 * 
	 * @param newPhone    the new phone number to check
	 * @param currentPhone the current phone number of the user
	 * @throws PhoneAlreadyExistsException if the new phone number is already in use by a different user
	 */
	private void assertPhoneNotAlreadyTaken(Phone newPhone, Phone currentPhone) {
		if (!currentPhone.equals(newPhone) && userRepository.existsByPhone(newPhone)) {
			throw new PhoneAlreadyExistsException(newPhone.getValue());
		}
	}
	
}
