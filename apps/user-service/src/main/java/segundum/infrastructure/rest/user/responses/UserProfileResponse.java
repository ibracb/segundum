package segundum.infrastructure.rest.user.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the public profile data of a user.
 */
@Schema(description = "Public profile data of a user")
public class UserProfileResponse {

	/**
	 * The unique identifier of the user.
	 */
	@Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String id;

	/**
	 * The first name of the user.
	 */
	@Schema(description = "User's first name", example = "John")
	private String name;

	/**
	 * The last name of the user.
	 */
	@Schema(description = "User's last name", example = "Doe")
	private String surname;

	/**
	 * The email address of the user.
	 */
	@Schema(description = "User's email address", example = "john.doe@example.com")
	private String email;

	/**
	 * The phone number of the user.
	 */
	@Schema(description = "User's phone number", example = "+34612345678")
	private String phone;

	/**
	 * Constructs a new UserProfileResponse with the given parameters.
	 *
	 * @param id      the unique identifier of the user
	 * @param name    the first name of the user
	 * @param surname the last name of the user
	 * @param email   the email address of the user
	 * @param phone   the phone number of the user
	 */
	public UserProfileResponse(String id, String name, String surname, String email, String phone) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * Returns the unique identifier of the user.
	 *
	 * @return the unique identifier of the user
	 */
	public String getId() {
		return id;
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
	 * Returns the last name of the user.
	 *
	 * @return the last name of the user
	 */
	public String getSurname() {
		return surname;
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
	 * Returns the phone number of the user.
	 *
	 * @return the phone number of the user
	 */
	public String getPhone() {
		return phone;
	}

}
