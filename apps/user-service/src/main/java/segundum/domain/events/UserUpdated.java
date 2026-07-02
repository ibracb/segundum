package segundum.domain.events;

import segundum.domain.models.user.Name;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.UserId;

/**
 * Represents the event of a user being updated in the system.
 */
public class UserUpdated extends DomainEvent {
	
	/**
	 * The unique identifier of the user.
	 */
	private final UserId userId;
	
	/**
	 * The name of the user.
	 */
	private final Name name;
	
	/**
	 * The surname of the user.
	 */
	private final Surname surname;
	
	/**
	 * Constructs a new UserUpdated event with the given parameters.
	 * 
	 * @param userId the unique identifier of the user
	 * @param name the name of the user
	 * @param surname the surname of the user
	 */
	public UserUpdated(UserId userId, Name name, Surname surname) {
		super();
		this.userId = userId;
		this.name = name;
		this.surname = surname;
	}
	
	/**
	 * Returns the unique identifier of the user.
	 * 
	 * @return the unique identifier of the user
	 */
	public UserId getUserId() {
		return userId;
	}
	
	/**
	 * Returns the name of the user.
	 * 
	 * @return the name of the user
	 */
	public Name getName() {
		return name;
	}
	
	/**
	 * Returns the surname of the user.
	 * 
	 * @return the surname of the user
	 */
	public Surname getSurname() {
		return surname;
	}

}
