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
	 * @param identifierType the type of identifier used to search (e.g. "ID", "email")
	 * @param identifierValue the value of the identifier that was not found
	 */
    public EntityNotFoundException(String entityName, String identifierType, String identifierValue) {
        super(entityName + " with " + identifierType + " " + identifierValue + " not found.");
    }

}
