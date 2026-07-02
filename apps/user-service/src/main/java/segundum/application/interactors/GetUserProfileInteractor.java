package segundum.application.interactors;

import java.util.Optional;

import segundum.application.usecases.GetUserProfileUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.repositories.UserRepository;
import segundum.utils.factories.RepositoryFactory;

public class GetUserProfileInteractor implements GetUserProfileUseCase {

	/**
	 * The repository for managing users.
	 */
    private final UserRepository userRepository;

    /**
	 * Constructs a new GetUserProfileInteractor, initializing the user repository using the RepositoryFactory.
	 */
    public GetUserProfileInteractor() {
        this.userRepository = RepositoryFactory.getUserRepository(User.class);
    }

    /**
     * Constructs a new GetUserProfileInteractor with the given repository.
     * @param userRepository The repository for managing users.
     */
    GetUserProfileInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(UserId userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(
                () -> new EntityNotFoundException("User", userId.getValue().toString()));
    }

}
