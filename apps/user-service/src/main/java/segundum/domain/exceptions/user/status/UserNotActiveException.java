package segundum.domain.exceptions.user.status;

import segundum.domain.exceptions.DomainException;

@SuppressWarnings("serial")
/**
 * Exception thrown when an operation is performed on a user that is not active.
 */
public class UserNotActiveException extends DomainException {

	/**
	 * Constructs a new UserNotActiveException for the given user identifier.
	 *
	 * @param id the identifier of the user that is not active
	 */
	public UserNotActiveException(String id) {
		super("User with ID " + id + " is not active.");
	}

}
