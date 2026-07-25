package segundum.domain.models.product;

import java.time.Instant;

/**
 * Represents a product's publication date and time.
 */
public class PublicationDate {

	/**
	 * The value of the publication date.
	 */
	private final Instant value;

	/**
	 * Constructs a new PublicationDate object with the given value.
	 *
	 * @param value the publication date value
	 */
	private PublicationDate(Instant value) {
		this.value = value;
	}

	/**
	 * Creates a new PublicationDate with the current date and time.
	 *
	 * @return a new PublicationDate object with the current date and time
	 */
	public static PublicationDate now() {
		return new PublicationDate(Instant.now());
	}

	/**
	 * Creates a new PublicationDate from an existing Instant value.
	 *
	 * @param value the instant value
	 * @return a new PublicationDate object
	 */
	public static PublicationDate fromInstant(Instant value) {
		return new PublicationDate(value);
	}

	/**
	 * Returns the value of the publication date.
	 *
	 * @return the publication date value
	 */
	public Instant getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PublicationDate that = (PublicationDate) o;
		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}
