package segundum.infrastructure.rest.sale.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the seller information in a sale response.
 */
@Schema(description = "Seller information")
public class SellerResponse {

    /**
     * The seller identifier.
     */
    @Schema(description = "Seller identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String id;

    /**
     * The seller name.
     */
    @Schema(description = "Seller name", example = "John")
    private String name;

    /**
     * The seller surname.
     */
    @Schema(description = "Seller surname", example = "Doe")
    private String surname;

    /**
     * Constructs a new empty SellerResponse for deserialization.
     */
    public SellerResponse() {
    }

    /**
     * Constructs a new SellerResponse with the given values.
     *
     * @param id      the seller identifier
     * @param name    the seller name
     * @param surname the seller surname
     */
    public SellerResponse(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the seller identifier.
     *
     * @return the seller identifier
     */
    public String getId() {
        return id;
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
     * Sets the seller identifier.
     *
     * @param id the seller identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the seller name.
     *
     * @param name the seller name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the seller surname.
     *
     * @param surname the seller surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

}
