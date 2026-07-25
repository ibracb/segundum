package segundum.domain.events;

import segundum.domain.models.user.UserId;

/**
 * Represents the event of a user being deactivated in the system.
 */
public class UserDeactivated extends DomainEvent {
	
	/**
	 * The unique identifier of the user.
	 */
	private final UserId userId;
	
	/**
	 * Constructs a new UserDeactivated event with the given parameters.
	 * 
	 * @param userId the unique identifier of the user
	 */
	public UserDeactivated(UserId userId) {
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
