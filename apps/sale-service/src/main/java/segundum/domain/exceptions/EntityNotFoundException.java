package segundum.domain.exceptions;

/**
 * Exception thrown when an entity cannot be found in the system.
 */
@SuppressWarnings("serial")
public class EntityNotFoundException extends DomainException {

	/**
	 * Constructs a new EntityNotFoundException with the given entity details.
	 *
	 * @param entityName the name of the entity
	 * @param entityId the identifier of the entity
	 */
    public EntityNotFoundException(String entityName, String entityId) {
        super(entityName + " with ID " + entityId + " not found.");
    }

}
