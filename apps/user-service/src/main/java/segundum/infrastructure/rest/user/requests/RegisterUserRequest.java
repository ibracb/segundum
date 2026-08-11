package segundum.infrastructure.rest.user.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a request to register a new user.
 */
@Schema(description = "Request to register a new user")
public class RegisterUserRequest {

	/**
	 * The first name of the user.
	 */
	@NotNull
	@NotBlank
	@Schema(description = "User's first name", example = "John")
	private String name;

	/**
	 * The last name of the user.
	 */
	@NotNull
	@NotBlank
	@Schema(description = "User's last name", example = "Doe")
	private String surname;

	/**
	 * The email address of the user.
	 */
	@NotNull
	@NotBlank
	@Schema(description = "User's email address", example = "john.doe@example.com")
	private String email;

	/**
	 * The password of the user.
	 */
	@NotNull
	@NotBlank
	@Schema(description = "User's password", example = "securePass123")
	private String password;

	/**
	 * The birthdate of the user.
	 */
	@NotNull
	@NotBlank
	@Schema(description = "User's birthdate", example = "1990-01-15")
	private LocalDate birthdate;

	/**
	 * The phone number of the user.
	 */
	@NotNull
	@NotBlank
	@Schema(description = "User's phone number", example = "+34612345678")
	private String phone;

	/**
	 * Constructs a new RegisterUserRequest.
	 */
	public RegisterUserRequest() {
	}

	/**
	 * Returns the first name of the user.
	 *
	 * @return the first name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param name the first name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the last name of the user.
	 *
	 * @return the last name of the user
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param surname the last name of the user
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Returns the email address of the user.
	 *
	 * @return the email address of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the user.
	 *
	 * @param email the email address of the user
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
