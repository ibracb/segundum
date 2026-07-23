package segundum.domain.models.seller;

import java.util.UUID;

import segundum.domain.exceptions.seller.sellerid.SellerIdBlankException;
import segundum.domain.exceptions.seller.sellerid.SellerIdInvalidFormatException;
import segundum.domain.exceptions.seller.sellerid.SellerIdNullException;

/**
 * Represents a seller's unique identifier.
 * This is a replica of the user ID from the user microservice, received via domain events.
 */
public class SellerId {

	/**
	 * The value of the unique identifier.
	 */
	private final UUID value;

	/**
	 * Constructs a new SellerId object with the given UUID value.
	 *
	 * @param value the UUID value
	 */
	private SellerId(UUID value) {
		this.value = value;
	}

	/**
	 * Creates a new SellerId from a UUID string.
	 *
	 * @param uuid the UUID string
	 * @return a new SellerId object
	 */
	public static SellerId fromString(String uuid) {
		ensureIsNotNull(uuid);
		ensureIsNotBlank(uuid);
		ensureIsValidUUID(uuid);
		return new SellerId(UUID.fromString(uuid));
	}

	/**
	 * Creates a new SellerId from a UUID.
	 *
	 * @param uuid the UUID value
	 * @return a new SellerId object
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
	 * Ensures that the given value is a valid UUID format.
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
	 * Returns the value of the unique identifier.
	 *
	 * @return the value of the unique identifier
	 */
	public UUID getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SellerId sellerId = (SellerId) o;
		return value.equals(sellerId.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}
