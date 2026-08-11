package segundum.infrastructure.rest.user.mappers;

import segundum.domain.models.user.User;
import segundum.infrastructure.rest.user.responses.UserProfileResponse;

/**
 * Mapper class for converting between User domain objects and UserProfileResponse objects.
 */
public class UserProfileResponseMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private UserProfileResponseMapper() {
	}

	/**
	 * Converts a User into a UserProfileResponse.
	 *
	 * @param user the user to convert
	 * @return the UserProfileResponse representing the user's profile
	 */
	public static UserProfileResponse fromDomain(User user) {
		return new UserProfileResponse(
				user.getUserId().getValue().toString(),
				user.getName().getValue(),
				user.getSurname().getValue(),
				user.getEmail().getValue(),
				user.getPhone().getValue()
		);
	}

}
