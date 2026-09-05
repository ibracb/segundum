package segundum.domain.models.user;

import java.util.List;

/**
 * Factory class for creating User objects.
 */
public class UserFactory {
	
	/**
	 * Private constructor to prevent instantiation of the UserFactory class.
	 */
	private UserFactory() {
	}
	
	/**
	 * Creates a new User object with the given parameters.
	 * The user is created with the default USER role.
	 * 
	 * @param name the name of the user
	 * @param surname the surname of the user
	 * @param email the email of the user
	 * @param password the password of the user
	 * @param birthdate the birthdate of the user
	 * @param phone the phone number of the user
	 * @return a new User object
	 */
	public static User create(Name name, Surname surname, Email email, Password password, Birthdate birthdate, Phone phone) {
		return new User(name, surname, email, password, birthdate, phone);
	}
	
	/**
	 * Reconstitutes a User object with all fields from user's data.
	 * 
	 * @param userId the unique identifier of the user
	 * @param name the name of the user
	 * @param surname the surname of the user
	 * @param email the email of the user
	 * @param password the password of the user
	 * @param birthdate the birthdate of the user
	 * @param phone the phone number of the user
	 * @param purchases the number of purchases
	 * @param sales the number of sales
	 * @param status the status of the user
	 * @param registrationDate the registration date of the user
	 * @param roles the roles of the user
	 * @return the reconstituted User object
	 */
	public static User reconstitute(UserId userId, Name name, Surname surname, Email email, Password password, Birthdate birthdate, Phone phone, long purchases, long sales, UserStatus status, RegistrationDate registrationDate, List<UserRole> roles) {
		return new User(userId, name, surname, email, password, birthdate, phone, purchases, sales, status, registrationDate, roles);
	}

}
