package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.queries.GetUserStatsQuery;
import segundum.application.usecases.GetUserStatsUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.user.User;
import segundum.domain.repositories.UserRepository;

/**
 * Represents the interactor for retrieving a user's statistics from the system.
 */
public class GetUserStatsInteractor implements GetUserStatsUseCase {

	/**
	 * The repository for managing users.
	 */
	private final UserRepository userRepository;

	/**
	 * Constructs a new GetUserStatsInteractor with the given repository.
	 * @param userRepository The repository for managing users.
	 */
	public GetUserStatsInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User execute(GetUserStatsQuery query) {
		Optional<User> user = userRepository.findById(query.getUserId());
		return user.orElseThrow(
				() -> new EntityNotFoundException("User", query.getUserId().getValue().toString()));
	}

}
