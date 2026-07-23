package segundum.domain.models.user;

import segundum.domain.exceptions.SameValueException;

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
	 * Constructs a new User object with the given parameters.
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
	 */
	User(UserId userId, Name name, Surname surname, Email email, Password password, Birthdate birthdate,
			Phone phone, long purchases, long sales, UserStatus status) {
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
	}
	
	/**
	 * Changes the name of the user.
	 * 
	 * @param name the new name of the user
	 * @throws SameValueException if the new name is the same as the current name
	 */
	public void changeName(Name name) {
		if(this.name.equals(name)) {
			throw new SameValueException("name");
		}
		this.name = name;
	}
	
	/**
	 * Changes the surname of the user.
	 * 
	 * @param surname the new surname of the user
	 * @throws SameValueException if the new surname is the same as the current surname
	 */
	public void changeSurname(Surname surname) {
		if(this.surname.equals(surname)) {
			throw new SameValueException("surname");
		}
		this.surname = surname;
	}
	
	/**
	 * Changes the password of the user.
	 * 
	 * @param newPassword the new password of the user
	 * @throws SameValueException if the new password is the same as the current password
	 */
	public void changePassword(Password newPassword) {
		if(this.password.equals(newPassword)) {
			throw new SameValueException("password");
		}
		this.password = newPassword;
	}
	
	/**
	 * Changes the phone number of the user.
	 * 
	 * @param phone the new phone number of the user
	 * @throws SameValueException if the new phone number is the same as the current phone number
	 */
	public void changePhone(Phone phone) {
		if(this.phone.equals(phone)) {
			throw new SameValueException("phone");
		}
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
	 * Deletes the user by setting status to DELETED.
	 */
	public void delete() {
		this.status = UserStatus.DELETED;
	}

	/**
	 * Checks if the user has been deleted.
	 *
	 * @return true if the user is deleted, false otherwise
	 */
	public boolean isDeleted() {
		return this.status == UserStatus.DELETED;
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

}
