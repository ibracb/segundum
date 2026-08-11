package segundum.domain.models.sale;

import java.util.UUID;

import segundum.domain.exceptions.sale.sellerid.SellerIdBlankException;
import segundum.domain.exceptions.sale.sellerid.SellerIdInvalidFormatException;
import segundum.domain.exceptions.sale.sellerid.SellerIdNullException;

/**
 * Represents the unique identifier of a seller in a sale.
 */
public class SellerId {

	/**
	 * The UUID value of the seller identifier.
	 */
    private final UUID value;

	/**
	 * Constructs a new SellerId object with the given value.
	 *
	 * @param value the UUID value
	 */
    private SellerId(UUID value) {
        this.value = value;
    }

	/**
	 * Creates a new SellerId from a String value.
	 *
	 * @param uuid the seller identifier value
	 * @return a new SellerId object
	 * @throws SellerIdNullException if the value is null
	 * @throws SellerIdBlankException if the value is blank
	 * @throws SellerIdInvalidFormatException if the value is not a valid UUID
	 */
    public static SellerId fromString(String uuid) {
        ensureIsNotNull(uuid);
        ensureIsNotBlank(uuid);
        ensureIsValidUUID(uuid);
        return new SellerId(UUID.fromString(uuid));
    }

	/**
	 * Creates a new SellerId from a UUID value.
	 *
	 * @param uuid the UUID value
	 * @return a new SellerId object
	 * @throws SellerIdNullException if the value is null
	 */
    public static SellerId fromUUID(UUID uuid) {
        if (uuid == null) {
            throw new SellerIdNullException();
        }
        return new SellerId(uuid);
    }

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws SellerIdNullException if the value is null
	 */
    private static void ensureIsNotNull(String value) {
        if (value == null) {
            throw new SellerIdNullException();
        }
    }

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws SellerIdBlankException if the value is blank
	 */
    private static void ensureIsNotBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new SellerIdBlankException();
        }
    }

	/**
	 * Ensures that the given value is a valid UUID.
	 *
	 * @param value the value to ensure
	 * @throws SellerIdInvalidFormatException if the value is not a valid UUID
	 */
    private static void ensureIsValidUUID(String value) {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new SellerIdInvalidFormatException();
        }
    }

	/**
	 * Returns the UUID value of the seller identifier.
	 *
	 * @return the UUID value of the seller identifier
	 */
    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerId that = (SellerId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
