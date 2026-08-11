package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.queries.GetUserNameQuery;
import segundum.application.usecases.GetUserNameUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.user.User;
import segundum.domain.repositories.UserRepository;

/**
 * Represents the interactor for retrieving the name of a user in the system.
 */
public class GetUserNameInteractor implements GetUserNameUseCase {

    /**
     * The repository for managing users.
     */
    private final UserRepository userRepository;

    /**
     * Constructs a new GetUserNameInteractor with the given repository.
     *
     * @param userRepository the repository for managing users
     */
    public GetUserNameInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(GetUserNameQuery query) {
        Optional<User> user = userRepository.findById(query.getUserId());
        return user.orElseThrow(
                () -> new EntityNotFoundException("User", query.getUserId().getValue().toString()));
    }

}
