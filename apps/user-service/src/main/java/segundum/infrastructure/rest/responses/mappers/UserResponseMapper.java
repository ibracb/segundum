package segundum.infrastructure.rest.responses.mappers;

import segundum.domain.models.user.User;
import segundum.infrastructure.rest.responses.UserResponse;

/**
 * Mapper class for converting between User domain objects and UserResponse objects.
 */
public class UserResponseMapper {
	
	/**
	 * Private constructor to prevent instantiation of the UserResponseMapper class.
	 */
	private UserResponseMapper() {
	}
	
	/**
	 * Converts a User domain object to a UserResponse.
	 *
	 * @param user the User domain object to convert
	 * @return the corresponding UserResponse
	 */
	public static UserResponse fromDomain(User user) {
		return new UserResponse(
				user.getUserId().getValue().toString(),
				user.getName().getValue(),
				user.getSurname().getValue(),
				user.getEmail().getValue(),
				user.getBirthdate().getValue().toString(),
				user.getPhone().getValue(),
				user.getPurchases(),
				user.getSales()
		);
	}
	
}
