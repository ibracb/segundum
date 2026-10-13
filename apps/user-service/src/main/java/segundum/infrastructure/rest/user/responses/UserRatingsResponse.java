package segundum.infrastructure.rest.user.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the ratings summary of a user.
 */
@Schema(description = "Ratings summary of a user")
public class UserRatingsResponse {

	/**
	 * The unique identifier of the user.
	 */
	@Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String id;

	/**
	 * The number of ratings received as purchaser.
	 */
	@Schema(description = "Number of ratings received as purchaser", example = "15")
	private long ratingsAsPurchaser;

	/**
	 * The number of ratings received as seller.
	 */
	@Schema(description = "Number of ratings received as seller", example = "7")
	private long ratingsAsSeller;

	/**
	 * The average of the ratings received as purchaser, between 1 and 5.
	 */
	@Schema(description = "Average of the ratings received as purchaser", example = "4.5")
	private double averageRatingAsPurchaser;

	/**
	 * The average of the ratings received as seller, between 1 and 5.
	 */
	@Schema(description = "Average of the ratings received as seller", example = "4.8")
	private double averageRatingAsSeller;

	/**
	 * Constructs a new UserRatingsResponse with the given parameters.
	 *
	 * @param id                        the unique identifier of the user
	 * @param ratingsAsPurchaser        the number of ratings received as purchaser
	 * @param ratingsAsSeller           the number of ratings received as seller
	 * @param averageRatingAsPurchaser  the average of the ratings received as purchaser
	 * @param averageRatingAsSeller     the average of the ratings received as seller
	 */
	public UserRatingsResponse(String id, long ratingsAsPurchaser, long ratingsAsSeller,
			double averageRatingAsPurchaser, double averageRatingAsSeller) {
		this.id = id;
		this.ratingsAsPurchaser = ratingsAsPurchaser;
		this.ratingsAsSeller = ratingsAsSeller;
		this.averageRatingAsPurchaser = averageRatingAsPurchaser;
		this.averageRatingAsSeller = averageRatingAsSeller;
	}

	/**
	 * Returns the unique identifier of the user.
	 *
	 * @return the unique identifier of the user
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the number of ratings received as purchaser.
	 *
	 * @return the number of ratings received as purchaser
	 */
	public long getRatingsAsPurchaser() {
		return ratingsAsPurchaser;
	}

	/**
	 * Returns the number of ratings received as seller.
	 *
	 * @return the number of ratings received as seller
	 */
	public long getRatingsAsSeller() {
		return ratingsAsSeller;
	}

	/**
	 * Returns the average of the ratings received as purchaser.
	 *
	 * @return the average of the ratings received as purchaser
	 */
	public double getAverageRatingAsPurchaser() {
		return averageRatingAsPurchaser;
	}

	/**
	 * Returns the average of the ratings received as seller.
	 *
	 * @return the average of the ratings received as seller
	 */
	public double getAverageRatingAsSeller() {
		return averageRatingAsSeller;
	}

}
