package segundum.infrastructure.rest.user.mappers;

import segundum.application.readmodels.user.UserProfileReadModel;
import segundum.domain.models.user.User;
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
	 * Converts a User domain entity into a UserProfileResponse.
	 *
	 * @param user the user domain entity to convert
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
