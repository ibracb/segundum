package segundum.infrastructure.rest.user.mappers;

import segundum.application.readmodels.user.UserStatsReadModel;
import segundum.infrastructure.rest.user.responses.UserStatsResponse;

/**
 * Mapper class for converting between UserStatsReadModel and UserStatsResponse objects.
 */
public class UserStatsResponseMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private UserStatsResponseMapper() {
	}

	/**
	 * Converts a UserStatsReadModel into a UserStatsResponse.
	 *
	 * @param stats the user stats read model to convert
	 * @return the UserStatsResponse representing the user's statistics
	 */
	public static UserStatsResponse fromReadModel(UserStatsReadModel stats) {
		return new UserStatsResponse(
				stats.getId(),
				stats.getPurchases(),
				stats.getSales()
		);
	}

}
