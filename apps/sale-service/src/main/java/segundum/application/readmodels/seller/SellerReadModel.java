package segundum.application.readmodels.seller;

/**
 * Represents a seller read model.
 */
public class SellerReadModel {

    /**
     * The identifier of the seller.
     */
    private final String id;

    /**
     * The name of the seller.
     */
    private final String name;

    /**
     * The surname of the seller.
     */
    private final String surname;

    /**
     * Constructs a new SellerReadModel with the given values.
     *
     * @param id      the identifier of the seller
     * @param name    the name of the seller
     * @param surname the surname of the seller
     */
    public SellerReadModel(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the identifier of the seller.
     *
     * @return the identifier of the seller
     */
    public String getId() { return id; }

    /**
     * Returns the name of the seller.
     *
     * @return the name of the seller
     */
    public String getName() { return name; }

    /**
     * Returns the surname of the seller.
     *
     * @return the surname of the seller
     */
    public String getSurname() { return surname; }

}
