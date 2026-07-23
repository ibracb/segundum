package segundum.infrastructure.rest.user.mappers;

import segundum.domain.models.user.User;
import segundum.infrastructure.rest.user.responses.UserProfileResponse;

/**
 * Mapper class for converting between User domain objects and UserProfileResponse objects.
 */
public class UserProfileResponseMapper {

	private UserProfileResponseMapper() {
	}

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
