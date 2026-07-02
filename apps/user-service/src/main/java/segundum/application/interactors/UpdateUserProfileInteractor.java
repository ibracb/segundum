package segundum.application.interactors;

import java.util.Optional;

import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.phone.PhoneAlreadyExistsException;

import segundum.application.commands.UpdateUserCommand;
import segundum.application.usecases.UpdateUserProfileUseCase;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.User;
import segundum.domain.repositories.UserRepository;
import segundum.utils.factories.RepositoryFactory;

/**
 * Represents the interactor for updating an existing user in the system.
 */
public class UpdateUserProfileInteractor implements UpdateUserProfileUseCase {

	/**
	 * The repository for managing users.
	 */
	private final UserRepository userRepository;

	/**
	 * Constructs a new UpdateUserProfileInteractor, initializing the user repository using the RepositoryFactory.
	 */
	public UpdateUserProfileInteractor() {
		this.userRepository = RepositoryFactory.getUserRepository(User.class);
	}

	/**
	 * Constructs a new UpdateUserProfileInteractor with the given repository.
	 *
	 * @param userRepository The repository for managing users.
	 */
	UpdateUserProfileInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User execute(UpdateUserCommand command) {
		Optional<User> user = userRepository.findById(command.getUserId());
		User userToUpdate = user.orElseThrow(
				() -> new EntityNotFoundException("User", command.getUserId().getValue().toString()));
		if (command.getName() != null) {
			userToUpdate.changeName(command.getName());
		}
		if (command.getSurname() != null) {
			userToUpdate.changeSurname(command.getSurname());
		}
		if (command.getPassword() != null) {
			userToUpdate.changePassword(command.getPassword());
		}
		if (command.getPhone() != null) {
			assertPhoneNotAlreadyTaken(command.getPhone(), userToUpdate.getPhone());
			userToUpdate.changePhone(command.getPhone());
		}
		userRepository.update(userToUpdate);
		return userToUpdate;
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
