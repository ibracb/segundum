package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.readmodels.user.UserNameReadModel;
import segundum.application.queries.GetUserNameQuery;
import segundum.application.finders.UserFinder;
import segundum.application.usecases.GetUserNameUseCase;
import segundum.domain.exceptions.EntityNotFoundException;

/**
 * Represents the interactor for retrieving the name of a user in the system.
 */
public class GetUserNameInteractor implements GetUserNameUseCase {

    /**
     * The read-side repository for users.
     */
    private final UserFinder userFinder;

    /**
     * Constructs a new GetUserNameInteractor with the given read repository.
     *
     * @param userFinder the read-side repository for users
     */
    public GetUserNameInteractor(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    @Override
    public UserNameReadModel execute(GetUserNameQuery query) {
        Optional<UserNameReadModel> name = userFinder.findNameById(query.getUserId());
        return name.orElseThrow(
                () -> new EntityNotFoundException("User", query.getUserId().getValue().toString()));
    }

}
