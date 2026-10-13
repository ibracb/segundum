package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.finders.UserFinder;
import segundum.application.queries.GetUserRatingsQuery;
import segundum.application.readmodels.user.UserRatingsReadModel;
import segundum.application.usecases.GetUserRatingsUseCase;
import segundum.domain.exceptions.EntityNotFoundException;

/**
 * Represents the interactor for retrieving a user's ratings summary from the system.
 */
public class GetUserRatingsInteractor implements GetUserRatingsUseCase {

	/**
	 * The read-side repository for users.
	 */
	private final UserFinder userFinder;

	/**
	 * Constructs a new GetUserRatingsInteractor with the given read repository.
	 * @param userFinder The read-side repository for users.
	 */
	public GetUserRatingsInteractor(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	@Override
	public UserRatingsReadModel execute(GetUserRatingsQuery query) {
		Optional<UserRatingsReadModel> ratings = userFinder.findRatingsById(query.getUserId());
		return ratings.orElseThrow(
				() -> new EntityNotFoundException("User", "ID", query.getUserId().getValue().toString()));
	}

}
