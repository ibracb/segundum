package segundum.domain.models.product;

import java.util.UUID;

/**
 * Represents a product's unique identifier.
 */
public class ProductId {

	/**
	 * The value of the unique identifier.
	 */
	private final UUID value;

	/**
	 * Constructs a new Id object with a randomly generated UUID value.
	 */
	private ProductId() {
		this.value = UUID.randomUUID();
	}

	/**
	 * Constructs a new Id object with the given UUID value.
	 *
	 * @param value the UUID value
	 */
	private ProductId(UUID value) {
		this.value = value;
	}

	/**
	 * Generates a new unique identifier.
	 *
	 * @return a new Id object
	 */
	public static ProductId generate() {
		return new ProductId();
	}

	/**
	 * Creates a new ProductId from a UUID.
	 *
	 * @param uuid the UUID value
	 * @return a new ProductId object
	 */
	public static ProductId fromUUID(UUID uuid) {
		return new ProductId(uuid);
	}

	/**
	 * Creates a new ProductId from a UUID string.
	 *
	 * @param uuid the UUID string
	 * @return a new ProductId object
	 */
	public static ProductId fromString(String uuid) {
		return new ProductId(UUID.fromString(uuid));
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
		ProductId productId = (ProductId) o;
		return value.equals(productId.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}
