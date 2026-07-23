package segundum.infrastructure.rest.user.mappers;

import segundum.domain.models.user.User;
import segundum.infrastructure.rest.user.responses.UserStatsResponse;

/**
 * Mapper class for converting between User domain objects and UserStatsResponse objects.
 */
public class UserStatsResponseMapper {

	private UserStatsResponseMapper() {
	}

	public static UserStatsResponse fromDomain(User user) {
		return new UserStatsResponse(
				user.getUserId().getValue().toString(),
				user.getPurchases(),
				user.getSales()
		);
	}

}
