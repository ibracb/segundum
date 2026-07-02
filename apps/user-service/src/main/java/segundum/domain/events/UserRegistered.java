package segundum.domain.events;

import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.UserId;

/**
 * Represents the event of a user being created in the system.
 */
public class UserRegistered extends DomainEvent {
	
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
	 * The email of the user.
	 */
	private final Email email;
	
	/**
	 * Constructs a new UserCreated event with the given parameters.
	 * 
	 * @param id the unique identifier of the user
	 * @param name the name of the user
	 * @param surname the surname of the user
	 * @param email the email of the user
	 * @param password the password of the user
	 * @param birthdate the birthdate of the user
	 * @param phone the phone number of the user
	 */
	public UserRegistered(UserId userId, Name name, Surname surname, Email email) {
		super();
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.email = email;
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
	
	/**
	 * Returns the email of the user.
	 * 
	 * @return the email of the user
	 */
	public Email getEmail() {
		return email;
	}

}
