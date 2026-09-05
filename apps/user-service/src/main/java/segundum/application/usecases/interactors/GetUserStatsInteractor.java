package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.readmodels.user.UserStatsReadModel;
import segundum.application.queries.GetUserStatsQuery;
import segundum.application.finders.UserFinder;
import segundum.application.usecases.GetUserStatsUseCase;
import segundum.domain.exceptions.EntityNotFoundException;

/**
 * Represents the interactor for retrieving a user's statistics from the system.
 */
public class GetUserStatsInteractor implements GetUserStatsUseCase {

	/**
	 * The read-side repository for users.
	 */
	private final UserFinder userFinder;

	/**
	 * Constructs a new GetUserStatsInteractor with the given read repository.
	 * @param userFinder The read-side repository for users.
	 */
	public GetUserStatsInteractor(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	@Override
	public UserStatsReadModel execute(GetUserStatsQuery query) {
		Optional<UserStatsReadModel> stats = userFinder.findStatsById(query.getUserId());
		return stats.orElseThrow(
				() -> new EntityNotFoundException("User", query.getUserId().getValue().toString()));
	}

}
