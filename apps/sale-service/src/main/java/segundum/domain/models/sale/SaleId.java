package segundum.domain.models.sale;

import java.util.UUID;

import segundum.domain.models.AggregateId;

/**
 * Represents the unique identifier of a sale.
 */
public class SaleId implements AggregateId {

	/**
	 * The UUID value of the sale identifier.
	 */
    private final UUID value;

	/**
	 * Constructs a new SaleId object with a randomly generated UUID.
	 */
    private SaleId() {
        this.value = UUID.randomUUID();
    }

	/**
	 * Constructs a new SaleId object with the given value.
	 *
	 * @param value the UUID value
	 */
    private SaleId(UUID value) {
        this.value = value;
    }

	/**
	 * Generates a new SaleId with a randomly generated UUID.
	 *
	 * @return a new SaleId object
	 */
    public static SaleId generate() {
        return new SaleId();
    }

	/**
	 * Creates a new SaleId from a String value.
	 *
	 * @param uuid the sale identifier value
	 * @return a new SaleId object
	 */
    public static SaleId fromString(String uuid) {
        return new SaleId(UUID.fromString(uuid));
    }

	/**
	 * Returns the UUID value of the sale identifier.
	 *
	 * @return the UUID value of the sale identifier
	 */
    public UUID getValue() {
        return value;
    }

    @Override
    public String asString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleId saleId = (SaleId) o;
        return value.equals(saleId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
