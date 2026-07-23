package segundum.infrastructure.rest.user.responses;

/**
 * Represents the statistics data of a user in the system.
 */
public class UserStatsResponse {

	private String id;

	private long purchases;

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
