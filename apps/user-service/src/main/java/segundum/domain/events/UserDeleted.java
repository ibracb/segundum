package segundum.domain.events;

import segundum.domain.models.user.UserId;

/**
 * Represents the event of a user being deleted in the system.
 */
public class UserDeleted extends DomainEvent {
	
	/**
	 * The unique identifier of the user.
	 */
	private final UserId userId;
	
	/**
	 * Constructs a new UserDeleted event with the given parameters.
	 * 
	 * @param userId the unique identifier of the user
	 */
	public UserDeleted(UserId userId) {
		super();
		this.userId = userId;
	}
	
	/**
	 * Returns the unique identifier of the user.
	 * 
	 * @return the unique identifier of the user
	 */
	public UserId getUserId() {
		return userId;
	}

}
