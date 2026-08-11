package segundum.infrastructure.rest.user.mappers;

import segundum.domain.models.user.User;
import segundum.infrastructure.rest.user.responses.UserNameResponse;

/**
 * Represents a mapper for converting a User into a UserNameResponse.
 */
public class UserNameResponseMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private UserNameResponseMapper() {
	}

	/**
	 * Converts a User into a UserNameResponse.
	 *
	 * @param user the user to convert
	 * @return the UserNameResponse representing the user's name
	 */
	public static UserNameResponse fromDomain(User user) {
        return new UserNameResponse(
                user.getUserId().getValue().toString(),
                user.getName().getValue(),
                user.getSurname().getValue()
        );
    }

}
