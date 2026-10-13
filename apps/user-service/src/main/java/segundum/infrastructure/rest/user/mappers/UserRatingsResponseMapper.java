package segundum.infrastructure.rest.user.mappers;

import segundum.application.readmodels.user.UserRatingsReadModel;
import segundum.infrastructure.rest.user.responses.UserRatingsResponse;

/**
 * Mapper class for converting between UserRatingsReadModel and UserRatingsResponse objects.
 */
public class UserRatingsResponseMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private UserRatingsResponseMapper() {
	}

	/**
	 * Converts a UserRatingsReadModel into a UserRatingsResponse.
	 *
	 * @param ratings the user ratings read model to convert
	 * @return the UserRatingsResponse representing the user's ratings summary
	 */
	public static UserRatingsResponse fromReadModel(UserRatingsReadModel ratings) {
		return new UserRatingsResponse(
				ratings.getId(),
				ratings.getRatingsAsPurchaser(),
				ratings.getRatingsAsSeller(),
				ratings.getAverageRatingAsPurchaser(),
				ratings.getAverageRatingAsSeller()
		);
	}

}
