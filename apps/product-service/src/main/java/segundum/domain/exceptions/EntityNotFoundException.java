package segundum.domain.exceptions;

/**
 * Exception thrown when an entity is not found in the system.
 */
@SuppressWarnings("serial")
public class EntityNotFoundException extends DomainException {

	/**
	 * Constructs a new EntityNotFoundException with a message indicating that the specified entity was not found.
	 *
	 * @param entityName the name of the entity that was not found
	 * @param identifierType the type of identifier used to search (e.g. "ID", "email")
	 * @param identifierValue the value of the identifier that was not found
	 */
	public EntityNotFoundException(String entityName, String identifierType, String identifierValue) {
		super(entityName + " with " + identifierType + " " + identifierValue + " not found.");
	}

}
