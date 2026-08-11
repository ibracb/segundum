package segundum.infrastructure.rest.user.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the statistics data of a user.
 */
@Schema(description = "Statistics data of a user")
public class UserStatsResponse {

	/**
	 * The unique identifier of the user.
	 */
	@Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String id;

	/**
	 * The number of purchases made by the user.
	 */
	@Schema(description = "Number of purchases made", example = "15")
	private long purchases;

	/**
	 * The number of sales made by the user.
	 */
	@Schema(description = "Number of sales made", example = "7")
	private long sales;

	/**
	 * Constructs a new UserStatsResponse with the given parameters.
	 *
	 * @param id        the unique identifier of the user
	 * @param purchases the number of purchases made by the user
	 * @param sales     the number of sales made by the user
	 */
	public UserStatsResponse(String id, long purchases, long sales) {
		this.id = id;
		this.purchases = purchases;
		this.sales = sales;
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
	 * Returns the number of purchases made by the user.
	 *
	 * @return the number of purchases made by the user
	 */
	public long getPurchases() {
		return purchases;
	}

	/**
	 * Returns the number of sales made by the user.
	 *
	 * @return the number of sales made by the user
	 */
	public long getSales() {
		return sales;
	}

}
