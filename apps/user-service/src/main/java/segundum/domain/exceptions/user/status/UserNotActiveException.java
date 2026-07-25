package segundum.domain.exceptions.user.status;

import segundum.domain.exceptions.DomainException;

@SuppressWarnings("serial")
public class UserNotActiveException extends DomainException {

	public UserNotActiveException(String id) {
		super("User with ID " + id + " is not active.");
	}

}
