package segundum.application.interactors;

import segundum.application.usecases.DeleteUserUseCase;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.repositories.UserRepository;
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
	 * Constructs a new DeleteUserInteractor, initializing the user repository using the RepositoryFactory.
	 */
	public DeleteUserInteractor() {
		this.userRepository = RepositoryFactory.getUserRepository(User.class);
	}

	/**
	 * Constructs a new DeleteUserInteractor with the given repository.
	 *
	 * @param userRepository The repository for managing users.
	 */
	DeleteUserInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void execute(UserId userId) {
		userRepository.delete(userId);
	}

}
