package segundum.application.usecases.interactors;

import java.util.List;

import segundum.application.readmodels.user.UserInfoReadModel;
import segundum.application.queries.GetUserListQuery;
import segundum.application.finders.UserFinder;
import segundum.application.usecases.GetUserListUseCase;

/**
 * Represents the interactor for retrieving all user info from the system.
 */
public class GetUserListInteractor implements GetUserListUseCase {

	/**
	 * The read-side repository for users.
	 */
	private final UserFinder userFinder;

	/**
	 * Constructs a new GetUserListInteractor with the given read repository.
	 *
	 * @param userFinder the read-side repository for users
	 */
	public GetUserListInteractor(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	@Override
	public List<UserInfoReadModel> execute(GetUserListQuery query) {
		return userFinder.findAllUserInfo();
	}

}
