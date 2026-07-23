package segundum.domain.exceptions;

/**
 * Exception thrown when an entity is not found in the system.
 */
@SuppressWarnings("serial")
public class EntityNotFoundException extends DomainException {

	/**
	 * Constructs a new EntityNotFound exception with a message indicating that the specified entity was not found.
	 *
	 * @param entityName the name of the entity that was not found
	 * @param entityId the ID of the entity that was not found
	 */
	public EntityNotFoundException(String entityName, String entityId) {
		super(entityName + " with ID " + entityId + " not found.");
	}

}
