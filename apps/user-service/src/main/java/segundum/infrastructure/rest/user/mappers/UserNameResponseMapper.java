package segundum.infrastructure.rest.user.mappers;

import segundum.application.readmodels.user.UserNameReadModel;
import segundum.infrastructure.rest.user.responses.UserNameResponse;

/**
 * Represents a mapper for converting a UserNameReadModel into a UserNameResponse.
 */
public class UserNameResponseMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private UserNameResponseMapper() {
	}

	/**
	 * Converts a UserNameReadModel into a UserNameResponse.
	 *
	 * @param userName the user name read model to convert
	 * @return the UserNameResponse representing the user's name
	 */
	public static UserNameResponse fromReadModel(UserNameReadModel userName) {
        return new UserNameResponse(
                userName.getId(),
                userName.getName(),
                userName.getSurname()
        );
    }

}
