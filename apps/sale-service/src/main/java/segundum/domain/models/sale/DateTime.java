package segundum.domain.models.sale;

import java.time.Instant;

/**
 * Represents a point in time associated with a sale.
 */
public class DateTime {

	/**
	 * The instant value of the date time.
	 */
    private final Instant value;

	/**
	 * Constructs a new DateTime object with the given value.
	 *
	 * @param value the instant value
	 */
    private DateTime(Instant value) {
        this.value = value;
    }

	/**
	 * Creates a new DateTime representing the current time.
	 *
	 * @return a new DateTime representing the current time
	 */
    public static DateTime now() {
        return new DateTime(Instant.now());
    }

	/**
	 * Creates a new DateTime from the given Instant value.
	 *
	 * @param value the instant value
	 * @return a new DateTime object
	 */
    public static DateTime fromInstant(Instant value) {
        return new DateTime(value);
    }

	/**
	 * Returns the instant value of the date time.
	 *
	 * @return the instant value of the date time
	 */
    public Instant getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTime that = (DateTime) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
