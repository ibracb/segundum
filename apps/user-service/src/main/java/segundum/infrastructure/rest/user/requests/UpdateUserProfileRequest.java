package segundum.infrastructure.rest.user.requests;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a request to update a user's profile.
 */
@Schema(description = "Request to update a user's profile")
public class UpdateUserProfileRequest {

	/**
	 * The unique identifier of the user.
	 */
	@Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String userId;

	/**
	 * The updated first name of the user.
	 */
	@Schema(description = "Updated first name", example = "John")
	private String name;

	/**
	 * The updated last name of the user.
	 */
	@Schema(description = "Updated last name", example = "Doe")
	private String surname;

	/**
	 * The updated password of the user.
	 */
	@Schema(description = "Updated password", example = "newSecurePass456")
	private String password;

	/**
	 * The updated phone number of the user.
	 */
	@Schema(description = "Updated phone number", example = "+34698765432")
	private String phone;

	/**
	 * Constructs a new UpdateUserProfileRequest.
	 */
	public UpdateUserProfileRequest() {
	}

	/**
	 * Returns the unique identifier of the user.
	 *
	 * @return the unique identifier of the user
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the unique identifier of the user.
	 *
	 * @param userId the unique identifier of the user
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Returns the updated first name of the user.
	 *
	 * @return the updated first name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the updated first name of the user.
	 *
	 * @param name the updated first name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the updated last name of the user.
	 *
	 * @return the updated last name of the user
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the updated last name of the user.
	 *
	 * @param surname the updated last name of the user
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Returns the updated password of the user.
	 *
	 * @return the updated password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the updated password of the user.
	 *
	 * @param password the updated password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the updated phone number of the user.
	 *
	 * @return the updated phone number of the user
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the updated phone number of the user.
	 *
	 * @param phone the updated phone number of the user
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
