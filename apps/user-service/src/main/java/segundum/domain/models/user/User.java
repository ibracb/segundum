package segundum.domain.models.user;

import java.util.List;

import segundum.domain.exceptions.SameValueException;
import segundum.domain.exceptions.user.status.UserNotActiveException;

/**
 * Represents a user in the system.
 */
public class User {
	
	/**
	 * The default number of purchases made by the user.
	 */
	private static final long NO_PURCHASES = 0;
	
	/**
	 * The default number of sales made by the user.
	 */
	private static final long NO_SALES = 0;
	
	
	/**
	 * The unique identifier of the user.
	 */
	private final UserId userId;
	
	/**
	 * The name of the user.
	 */
	private Name name;
	
	/**
	 * The surname of the user.
	 */
	private Surname surname;
	
	/**
	 * The email of the user.
	 */
	private final Email email;
	
	/**
	 * The password of the user.
	 */
	private Password password;
	
	/**
	 * The birthdate of the user.
	 */
	private final Birthdate birthdate;
	
	/**
	 * The phone number of the user.
	 */
	private Phone phone;
	
	/**
	 * The number of purchases made by the user.
	 */
	private long purchases;
	
	/**
	 * The number of sales made by the user.
	 */
	private long sales;

	/**
	 * The status of the user.
	 */
	private UserStatus status;

	/**
	 * The registration date of the user.
	 */
	private final RegistrationDate registrationDate;

	/**
	 * The roles of the user.
	 */
	private List<UserRole> roles;

	/**
	 * Constructs a new User object with the given parameters.
	 * The user is created with the default USER role.
	 * 
	 * @param name the name of the user
	 * @param surname the surname of the user
	 * @param email the email of the user
	 * @param password the password of the user
	 * @param birthdate the birthdate of the user
	 * @param phone the phone number of the user
	 */
	User(Name name, Surname surname, Email email, Password password, Birthdate birthdate, Phone phone) {
		this.userId = UserId.generate();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.phone = phone;
		this.purchases = NO_PURCHASES;
		this.sales = NO_SALES;
		this.status = UserStatus.ACTIVE;
		this.registrationDate = RegistrationDate.now();
		this.roles = List.of(UserRole.USER);
	}
	
	/**
	 * Constructs a new User object with all fields, for reconstitution from persistence.
	 * 
	 * @param userId the unique identifier of the user
	 * @param name the name of the user
	 * @param surname the surname of the user
	 * @param email the email of the user
	 * @param password the password of the user
	 * @param birthdate the birthdate of the user
	 * @param phone the phone number of the user
	 * @param purchases the number of purchases made by the user
	 * @param sales the number of sales made by the user
	 * @param status the status of the user
	 * @param registrationDate the registration date of the user
	 * @param roles the roles of the user
	 */
	User(UserId userId, Name name, Surname surname, Email email, Password password, Birthdate birthdate,
			Phone phone, long purchases, long sales, UserStatus status, RegistrationDate registrationDate,
			List<UserRole> roles) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.phone = phone;
		this.purchases = purchases;
		this.sales = sales;
		this.status = status;
		this.registrationDate = registrationDate;
		this.roles = roles;
	}
	
	/**
	 * Changes the name of the user.
	 * 
	 * @param name the new name of the user
	 */
	public void changeName(Name name) {
		ensureIsActive();
		ensureNameIsNotTheSame(name);
		this.name = name;
	}
	
	/**
	 * Changes the surname of the user.
	 * 
	 * @param surname the new surname of the user
	 * @throws SameValueException if the new surname is the same as the current surname
	 */
	public void changeSurname(Surname surname) {
		ensureIsActive();
		ensureSurnameIsNotTheSame(surname);
		this.surname = surname;
	}
	
	/**
	 * Changes the password of the user.
	 * 
	 * @param newPassword the new password of the user
	 */
	public void changePassword(Password password) {
		ensurePasswordIsNotTheSame(password);
		this.password = password;
	}
	
	/**
	 * Changes the phone number of the user.
	 * 
	 * @param phone the new phone number of the user
	 */
	public void changePhone(Phone phone) {
		ensurePhoneIsNotTheSame(phone);
		this.phone = phone;
	}
	
	/**
	 * Increments the number of purchases made by the user by 1.
	 */
	public void incrementPurchases() {
		purchases++;
	}
	
	/**
	 * Increments the number of sales made by the user by 1.
	 */
	public void incrementSales() {
		sales++;
	}
	
	/**
	 * Deactivates the user by setting status to INACTIVE.
	 */
	public void deactivate() {
		ensureIsActive();
		status = UserStatus.INACTIVE;
	}

	/**
	 * Checks if the user has been deactivated.
	 *
	 * @return true if the user is inactive, false otherwise
	 */
	public boolean isActive() {
		return status == UserStatus.ACTIVE;
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
	
	/**
	 * Returns the number of purchases made by the user.
	 * 
	 * @return the number of purchases made by the user
	 */
	public long getPurchases() {
		return purchases;
	}
	
	/**
	 * Returns the number of sales made by the user.
	 * 
	 * @return the number of sales made by the user
	 */
	public long getSales() {
		return sales;
	}

	/**
	 * Returns the status of the user.
	 *
	 * @return the user status
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * Returns the registration date of the user.
	 *
	 * @return the user registration date
	 */
	public RegistrationDate getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * Returns the roles of the user.
	 *
	 * @return the user roles
	 */
	public List<UserRole> getUserRoles() {
		return roles;
	}

	/**
	 * Checks if the user has the given role.
	 *
	 * @param role the role to check
	 * @return true if the user has the role, false otherwise
	 */
	public boolean hasRole(UserRole role) {
		return roles.contains(role);
	}
	
	/**
	 * Ensures that the new name is not the same as the current name.
	 * 
	 * @param name the new name to check
	 * @throws SameValueException if the new name is the same as the current name
	 */
	private void ensureNameIsNotTheSame(Name name) {
		if(this.name.equals(name)) {
			throw new SameValueException("name");
		}
	}
	
	/**
	 * Ensures that the new surname is not the same as the current surname.
	 * 
	 * @param surname the new surname to check
	 * @throws SameValueException if the new surname is the same as the current surname
	 */
	private void ensureSurnameIsNotTheSame(Surname surname) {
		if(this.surname.equals(surname)) {
			throw new SameValueException("surname");
		}
	}
	
	/**
	 * Ensures that the new password is not the same as the current password.
	 * 
	 * @param password the new password to check
	 * @throws SameValueException if the new password is the same as the current password
	 */
	private void ensurePasswordIsNotTheSame(Password password) {
		if(this.password.equals(password)) {
			throw new SameValueException("password");
		}
	}
	
	/**
	 * Ensures that the new phone number is not the same as the current phone number.
	 * 
	 * @param phone the new phone number to check
	 * @throws SameValueException if the new phone number is the same as the current phone number
	 */
	private void ensurePhoneIsNotTheSame(Phone phone) {
		if(this.phone.equals(phone)) {
			throw new SameValueException("phone");
		}
	}
	
	private void ensureIsActive() {
		if (this.status != UserStatus.ACTIVE) {
			throw new UserNotActiveException(userId.getValue().toString());
		}
	}

}
