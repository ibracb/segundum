package segundum.application.readmodels.user;

/**
 * Represents the statistics data of a user used by the read side.
 */
public class UserStatsReadModel {

    /**
     * The identifier of the user.
     */
    private final String id;

    /**
     * The number of purchases made by the user.
     */
    private final long purchases;

    /**
     * The number of sales made by the user.
     */
    private final long sales;

    /**
     * Constructs a new UserStatsReadModel with the given data.
     *
     * @param id        the user identifier
     * @param purchases the number of purchases
     * @param sales     the number of sales
     */
    public UserStatsReadModel(String id, long purchases, long sales) {
        this.id = id;
        this.purchases = purchases;
        this.sales = sales;
    }

    /**
     * Returns the identifier of the user.
     *
     * @return the user identifier
     */
    public String getId() { return id; }

    /**
     * Returns the number of purchases made by the user.
     *
     * @return the number of purchases
     */
    public long getPurchases() { return purchases; }

    /**
     * Returns the number of sales made by the user.
     *
     * @return the number of sales
     */
    public long getSales() { return sales; }

}
