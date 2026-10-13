package segundum.application.dtos.purchaser;

/**
 * Represents purchaser data of the users bounded context.
 */
public class PurchaserData {

    /**
     * The identifier of the purchaser.
     */
    private final String id;

    /**
     * The name of the purchaser.
     */
    private final String name;

    /**
     * The surname of the purchaser.
     */
    private final String surname;

    /**
     * Constructs a new PurchaserData with the given values.
     *
     * @param id      the identifier of the purchaser
     * @param name    the name of the purchaser
     * @param surname the surname of the purchaser
     */
    public PurchaserData(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the identifier of the purchaser.
     *
     * @return the identifier of the purchaser
     */
    public String getId() { return id; }

    /**
     * Returns the name of the purchaser.
     *
     * @return the name of the purchaser
     */
    public String getName() { return name; }

    /**
     * Returns the surname of the purchaser.
     *
     * @return the surname of the purchaser
     */
    public String getSurname() { return surname; }

}
