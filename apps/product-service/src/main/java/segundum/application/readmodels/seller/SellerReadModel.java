package segundum.application.readmodels.seller;

/**
 * Represents the data of a seller, as served by the read side.
 */
public class SellerReadModel {

    /**
     * The unique identifier of the seller.
     */
    private final String sellerId;

    /**
     * The name of the seller.
     */
    private final String name;

    /**
     * The surname of the seller.
     */
    private final String surname;

    /**
     * The email of the seller.
     */
    private final String email;

    /**
     * Whether the seller is active.
     */
    private final boolean active;

    /**
     * Constructs a new SellerReadModel with the given values.
     *
     * @param sellerId the seller identifier
     * @param name the seller name
     * @param surname the seller surname
     * @param email the seller email
     * @param active whether the seller is active
     */
    public SellerReadModel(String sellerId, String name, String surname, String email, boolean active) {
        this.sellerId = sellerId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.active = active;
    }

    /**
     * Returns the seller identifier.
     *
     * @return the seller identifier
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * Returns the seller name.
     *
     * @return the seller name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the seller surname.
     *
     * @return the seller surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the seller email.
     *
     * @return the seller email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns whether the seller is active.
     *
     * @return true if the seller is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

}
