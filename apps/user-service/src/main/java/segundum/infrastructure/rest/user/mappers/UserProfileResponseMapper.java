package segundum.infrastructure.rest.user.mappers;

import segundum.application.readmodels.user.UserProfileReadModel;
import segundum.infrastructure.rest.user.responses.UserProfileResponse;

/**
 * Mapper class for converting to UserProfileResponse objects.
 */
public class UserProfileResponseMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private UserProfileResponseMapper() {
	}

	/**
	 * Converts a UserProfileReadModel into a UserProfileResponse.
	 *
	 * @param profile the user profile read model to convert
	 * @return the UserProfileResponse representing the user's profile
	 */
	public static UserProfileResponse fromReadModel(UserProfileReadModel profile) {
		return new UserProfileResponse(
				profile.getId(),
				profile.getName(),
				profile.getSurname(),
				profile.getEmail(),
				profile.getPhone()
		);
	}

}
