package segundum.infrastructure.rest.user.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statistics data of a user")
public class UserStatsResponse {

	@Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String id;

	@Schema(description = "Number of purchases made", example = "15")
	private long purchases;

	@Schema(description = "Number of sales made", example = "7")
	private long sales;

	public UserStatsResponse(String id, long purchases, long sales) {
		this.id = id;
		this.purchases = purchases;
		this.sales = sales;
	}

	public String getId() {
		return id;
	}

	public long getPurchases() {
		return purchases;
	}

	public long getSales() {
		return sales;
	}

}
