package segundum.domain.models;

/**
 * Represents the unique identifier of an aggregate in the system.
 */
public interface AggregateId {

	/**
	 * Returns the identifier as a String value.
	 *
	 * @return the identifier as a String value
	 */
    String asString();

}
