package segundum.infrastructure.rest.sale.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the purchaser information in a sale response.
 */
@Schema(description = "Purchaser information")
public class PurchaserResponse {

    /**
     * The purchaser identifier.
     */
    @Schema(description = "Purchaser identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String id;

    /**
     * The purchaser name.
     */
    @Schema(description = "Purchaser name", example = "Jane")
    private String name;

    /**
     * The purchaser surname.
     */
    @Schema(description = "Purchaser surname", example = "Smith")
    private String surname;

    /**
     * Constructs a new empty PurchaserResponse for deserialization.
     */
    public PurchaserResponse() {
    }

    /**
     * Constructs a new PurchaserResponse with the given values.
     *
     * @param id      the purchaser identifier
     * @param name    the purchaser name
     * @param surname the purchaser surname
     */
    public PurchaserResponse(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the purchaser identifier.
     *
     * @return the purchaser identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the purchaser name.
     *
     * @return the purchaser name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the purchaser surname.
     *
     * @return the purchaser surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the purchaser identifier.
     *
     * @param id the purchaser identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the purchaser name.
     *
     * @param name the purchaser name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the purchaser surname.
     *
     * @param surname the purchaser surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

}
