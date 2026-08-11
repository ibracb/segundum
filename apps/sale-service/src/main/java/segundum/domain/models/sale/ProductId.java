package segundum.domain.models.sale;

import java.util.UUID;

import segundum.domain.exceptions.sale.productid.ProductIdBlankException;
import segundum.domain.exceptions.sale.productid.ProductIdInvalidFormatException;
import segundum.domain.exceptions.sale.productid.ProductIdNullException;

/**
 * Represents the unique identifier of a product in a sale.
 */
public class ProductId {

	/**
	 * The UUID value of the product identifier.
	 */
    private final UUID value;

	/**
	 * Constructs a new ProductId object with the given value.
	 *
	 * @param value the UUID value
	 */
    private ProductId(UUID value) {
        this.value = value;
    }

	/**
	 * Creates a new ProductId from a String value.
	 *
	 * @param uuid the product identifier value
	 * @return a new ProductId object
	 * @throws ProductIdNullException if the value is null
	 * @throws ProductIdBlankException if the value is blank
	 * @throws ProductIdInvalidFormatException if the value is not a valid UUID
	 */
    public static ProductId fromString(String uuid) {
        ensureIsNotNull(uuid);
        ensureIsNotBlank(uuid);
        ensureIsValidUUID(uuid);
        return new ProductId(UUID.fromString(uuid));
    }

	/**
	 * Creates a new ProductId from a UUID value.
	 *
	 * @param uuid the UUID value
	 * @return a new ProductId object
	 * @throws ProductIdNullException if the value is null
	 */
    public static ProductId fromUUID(UUID uuid) {
        if (uuid == null) {
            throw new ProductIdNullException();
        }
        return new ProductId(uuid);
    }

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws ProductIdNullException if the value is null
	 */
    private static void ensureIsNotNull(String value) {
        if (value == null) {
            throw new ProductIdNullException();
        }
    }

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws ProductIdBlankException if the value is blank
	 */
    private static void ensureIsNotBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new ProductIdBlankException();
        }
    }

	/**
	 * Ensures that the given value is a valid UUID.
	 *
	 * @param value the value to ensure
	 * @throws ProductIdInvalidFormatException if the value is not a valid UUID
	 */
    private static void ensureIsValidUUID(String value) {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new ProductIdInvalidFormatException();
        }
    }

	/**
	 * Returns the UUID value of the product identifier.
	 *
	 * @return the UUID value of the product identifier
	 */
    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId that = (ProductId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
