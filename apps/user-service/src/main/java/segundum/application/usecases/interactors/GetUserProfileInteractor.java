package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.readmodels.user.UserProfileReadModel;
import segundum.application.queries.GetUserProfileQuery;
import segundum.application.finders.UserFinder;
import segundum.application.usecases.GetUserProfileUseCase;
import segundum.domain.exceptions.EntityNotFoundException;

/**
 * Represents the interactor for retrieving a user's profile from the system.
 */
public class GetUserProfileInteractor implements GetUserProfileUseCase {

	/**
	 * The read-side repository for users.
	 */
    private final UserFinder userFinder;

    /**
     * Constructs a new GetUserProfileInteractor with the given read repository.
     * @param userFinder The read-side repository for users.
     */
    public GetUserProfileInteractor(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    @Override
    public UserProfileReadModel execute(GetUserProfileQuery query) {
        Optional<UserProfileReadModel> profile = userFinder.findProfileById(query.getUserId());
        return profile.orElseThrow(
                () -> new EntityNotFoundException("User", query.getUserId().getValue().toString()));
    }

}
