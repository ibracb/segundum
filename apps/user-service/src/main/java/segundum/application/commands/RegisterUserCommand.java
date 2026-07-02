package segundum.application.commands;

import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;

/**
 * Represents a command to create a new user in the system.
 */
public class RegisterUserCommand {
	
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
	 * The password of the user.
	 */
	private final Password password;
	
	/**
	 * The birthdate of the user.
	 */
	private final Birthdate birthdate;
	
	/**
	 * The phone number of the user.
	 */
	private final Phone phone;
	
	/**
	 * Constructs a new CreateUserCommand with the given parameters.
	 * 
	 * @param name the name of the user
	 * @param surname the surname of the user
	 * @param email the email of the user
	 * @param password the password of the user
	 * @param birthdate the birthdate of the user
	 * @param phone the phone number of the user
	 */
	public RegisterUserCommand(Name name, Surname surname, Email email, Password password, Birthdate birthdate, Phone phone) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.phone = phone;
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
	
	/**
	 * Returns the password of the user.
	 * 
	 * @return the password of the user
	 */
	public Password getPassword() {
		return password;
	}
	
	/**
	 * Returns the birthdate of the user.
	 * 
	 * @return the birthdate of the user
	 */
	public Birthdate getBirthdate() {
		return birthdate;
	}
	
	/**
	 * Returns the phone number of the user.
	 * 
	 * @return the phone number of the user
	 */
	public Phone getPhone() {
		return phone;
	}
	
}
