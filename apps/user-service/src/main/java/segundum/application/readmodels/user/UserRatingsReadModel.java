package segundum.application.readmodels.user;

/**
 * Represents the ratings summary of a user used by the read side.
 */
public class UserRatingsReadModel {

    /**
     * The identifier of the user.
     */
    private final String id;

    /**
     * The number of ratings received as purchaser.
     */
    private final long ratingsAsPurchaser;

    /**
     * The number of ratings received as seller.
     */
    private final long ratingsAsSeller;

    /**
     * The average of the ratings received as purchaser, between 1 and 5.
     */
    private final double averageRatingAsPurchaser;

    /**
     * The average of the ratings received as seller, between 1 and 5.
     */
    private final double averageRatingAsSeller;

    /**
     * Constructs a new UserRatingsReadModel with the given data.
     *
     * @param id                      the user identifier
     * @param ratingsAsPurchaser      the number of ratings received as purchaser
     * @param ratingsAsSeller         the number of ratings received as seller
     * @param averageRatingAsPurchaser the average of the ratings received as purchaser
     * @param averageRatingAsSeller   the average of the ratings received as seller
     */
    public UserRatingsReadModel(String id, long ratingsAsPurchaser, long ratingsAsSeller,
            double averageRatingAsPurchaser, double averageRatingAsSeller) {
        this.id = id;
        this.ratingsAsPurchaser = ratingsAsPurchaser;
        this.ratingsAsSeller = ratingsAsSeller;
        this.averageRatingAsPurchaser = averageRatingAsPurchaser;
        this.averageRatingAsSeller = averageRatingAsSeller;
    }

    /**
     * Returns the identifier of the user.
     *
     * @return the user identifier
     */
    public String getId() { return id; }

    /**
     * Returns the number of ratings received as purchaser.
     *
     * @return the number of ratings received as purchaser
     */
    public long getRatingsAsPurchaser() { return ratingsAsPurchaser; }

    /**
     * Returns the number of ratings received as seller.
     *
     * @return the number of ratings received as seller
     */
    public long getRatingsAsSeller() { return ratingsAsSeller; }

    /**
     * Returns the average of the ratings received as purchaser.
     *
     * @return the average of the ratings received as purchaser
     */
    public double getAverageRatingAsPurchaser() { return averageRatingAsPurchaser; }

    /**
     * Returns the average of the ratings received as seller.
     *
     * @return the average of the ratings received as seller
     */
    public double getAverageRatingAsSeller() { return averageRatingAsSeller; }

}
