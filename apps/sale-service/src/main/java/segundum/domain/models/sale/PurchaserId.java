package segundum.domain.models.sale;

import java.util.UUID;

import segundum.domain.exceptions.sale.purchaserid.PurchaserIdBlankException;
import segundum.domain.exceptions.sale.purchaserid.PurchaserIdInvalidFormatException;
import segundum.domain.exceptions.sale.purchaserid.PurchaserIdNullException;

/**
 * Represents the unique identifier of a purchaser in a sale.
 */
public class PurchaserId {

	/**
	 * The UUID value of the purchaser identifier.
	 */
    private final UUID value;

	/**
	 * Constructs a new PurchaserId object with the given value.
	 *
	 * @param value the UUID value
	 */
    private PurchaserId(UUID value) {
        this.value = value;
    }

	/**
	 * Creates a new PurchaserId from a String value.
	 *
	 * @param uuid the purchaser identifier value
	 * @return a new PurchaserId object
	 * @throws PurchaserIdNullException if the value is null
	 * @throws PurchaserIdBlankException if the value is blank
	 * @throws PurchaserIdInvalidFormatException if the value is not a valid UUID
	 */
    public static PurchaserId fromString(String uuid) {
        ensureIsNotNull(uuid);
        ensureIsNotBlank(uuid);
        ensureIsValidUUID(uuid);
        return new PurchaserId(UUID.fromString(uuid));
    }

	/**
	 * Creates a new PurchaserId from a UUID value.
	 *
	 * @param uuid the UUID value
	 * @return a new PurchaserId object
	 * @throws PurchaserIdNullException if the value is null
	 */
    public static PurchaserId fromUUID(UUID uuid) {
        if (uuid == null) {
            throw new PurchaserIdNullException();
        }
        return new PurchaserId(uuid);
    }

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws PurchaserIdNullException if the value is null
	 */
    private static void ensureIsNotNull(String value) {
        if (value == null) {
            throw new PurchaserIdNullException();
        }
    }

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws PurchaserIdBlankException if the value is blank
	 */
    private static void ensureIsNotBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new PurchaserIdBlankException();
        }
    }

	/**
	 * Ensures that the given value is a valid UUID.
	 *
	 * @param value the value to ensure
	 * @throws PurchaserIdInvalidFormatException if the value is not a valid UUID
	 */
    private static void ensureIsValidUUID(String value) {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new PurchaserIdInvalidFormatException();
        }
    }

	/**
	 * Returns the UUID value of the purchaser identifier.
	 *
	 * @return the UUID value of the purchaser identifier
	 */
    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaserId that = (PurchaserId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
