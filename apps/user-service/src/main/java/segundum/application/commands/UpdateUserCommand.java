package segundum.application.commands;

import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.UserId;

/**
 * Represents a command to update an existing user in the system.
 */
public class UpdateUserCommand {
	
	/**
	 * The unique identifier of the user to be updated.
	 */
	private final UserId userId;
	
	/**
	 * The name of the user to be updated.
	 */
	private final Name name;
	
	/**
	 * The surname of the user to be updated.
	 */
	private final Surname surname;
	
	/**
	 * The password of the user to be updated.
	 */
	private final Password password;
	
	/**
	 * The phone number of the user to be updated.
	 */
	private final Phone phone;
	
	/**
	 * Constructs a new UpdateUserCommand with the given parameters.
	 * 
	 * @param userId the unique identifier of the user to be updated
	 * @param name the name of the user to be updated
	 * @param surname the surname of the user to be updated
	 * @param password the password of the user to be updated
	 * @param phone the phone number of the user to be updated
	 */
	public UpdateUserCommand(UserId userId, Name name, Surname surname, Password password, Phone phone) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.phone = phone;
	}
	
	/**
	 * Returns the unique identifier of the user to be updated.
	 * 
	 * @return the unique identifier of the user to be updated
	 */
	public UserId getUserId() {
		return userId;
	}
	
	/**
	 * Returns the name of the user to be updated.
	 * 
	 * @return the name of the user to be updated
	 */
	public Name getName() {
		return name;
	}
	
	/**
	 * Returns the surname of the user to be updated.
	 * 
	 * @return the surname of the user to be updated
	 */
	public Surname getSurname() {
		return surname;
	}
	
	/**
	 * Returns the password of the user to be updated.
	 * 
	 * @return the password of the user to be updated
	 */
	public Password getPassword() {
		return password;
	}
	
	/**
	 * Returns the phone number of the user to be updated.
	 * 
	 * @return the phone number of the user to be updated
	 */
	public Phone getPhone() {
		return phone;
	}

}
