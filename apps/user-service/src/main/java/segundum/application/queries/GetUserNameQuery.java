package segundum.application.queries;

import segundum.domain.models.user.UserId;

/**
 * Represents a query for retrieving the name of a user by their identifier.
 */
public class GetUserNameQuery {

    /**
     * The unique identifier of the user.
     */
    private final UserId userId;

    /**
     * Constructs a new GetUserNameQuery with the given user identifier.
     *
     * @param userId the unique identifier of the user
     */
    public GetUserNameQuery(UserId userId) {
        this.userId = userId;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the unique identifier of the user
     */
    public UserId getUserId() {
        return userId;
    }

}
