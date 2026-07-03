package segundum.application.interactors;

import java.util.Optional;

import segundum.application.queries.GetUserProfileQuery;
import segundum.application.usecases.GetUserProfileUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.user.User;
import segundum.domain.repositories.UserRepository;

/**
 * Represents the interactor for retrieving a user's profile from the system.
 */
public class GetUserProfileInteractor implements GetUserProfileUseCase {

	/**
	 * The repository for managing users.
	 */
    private final UserRepository userRepository;

    /**
     * Constructs a new GetUserProfileInteractor with the given repository.
     * @param userRepository The repository for managing users.
     */
    public GetUserProfileInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(GetUserProfileQuery query) {
        Optional<User> user = userRepository.findById(query.getUserId());
        return user.orElseThrow(
                () -> new EntityNotFoundException("User", query.getUserId().getValue().toString()));
    }

}
