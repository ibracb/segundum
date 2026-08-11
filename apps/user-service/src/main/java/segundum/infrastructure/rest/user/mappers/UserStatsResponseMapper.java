package segundum.infrastructure.rest.user.mappers;

import segundum.domain.models.user.User;
import segundum.infrastructure.rest.user.responses.UserStatsResponse;

/**
 * Mapper class for converting between User domain objects and UserStatsResponse objects.
 */
public class UserStatsResponseMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private UserStatsResponseMapper() {
	}

	/**
	 * Converts a User into a UserStatsResponse.
	 *
	 * @param user the user to convert
	 * @return the UserStatsResponse representing the user's statistics
	 */
	public static UserStatsResponse fromDomain(User user) {
		return new UserStatsResponse(
				user.getUserId().getValue().toString(),
				user.getPurchases(),
				user.getSales()
		);
	}

}
