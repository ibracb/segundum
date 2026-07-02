package segundum.infrastructure.rest.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Represents a request to register a new user.
 */
public class RegisterUserRequest {
	
	/**
	 * The name of the user.
	 */
	@NotNull
	@NotBlank
	private String name;
	
	/**
	 * The surname of the user.
	 */
	@NotNull
	@NotBlank
	private String surname;
	
	/**
	 * The email of the user.
	 */
	@NotNull
	@NotBlank
	private String email;
	
	/**
	 * The password of the user.
	 */
	@NotNull
	@NotBlank
	private String password;
	
	/**
	 * The birthdate of the user.
	 */
	@NotNull
	@NotBlank
	private LocalDate birthdate;
	
	/**
	 * The phone number of the user.
	 */
	@NotNull
	@NotBlank
	private String phone;
	
	/**
	 * Default constructor for RegisterUserRequest.
	 */
	public RegisterUserRequest() {
		// Default constructor
	}
	
	/**
	 * Returns the name of the user.
	 * 
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the user.
	 * 
	 * @param name the name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the surname of the user.
	 * 
	 * @return the surname of the user
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Sets the surname of the user.
	 * 
	 * @param surname the surname of the user
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * Returns the email of the user.
	 * 
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email of the user.
	 * 
	 * @param email the email of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Returns the password of the user.
	 * 
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password of the user.
	 * 
	 * @param password the password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns the birthdate of the user.
	 * 
	 * @return the birthdate of the user
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	/**
	 * Sets the birthdate of the user.
	 * 
	 * @param birthdate the birthdate of the user
	 */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	/**
	 * Returns the phone number of the user.
	 * 
	 * @return the phone number of the user
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone number of the user.
	 * 
	 * @param phone the phone number of the user
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
