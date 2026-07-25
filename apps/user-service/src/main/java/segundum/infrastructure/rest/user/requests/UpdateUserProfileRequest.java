package segundum.infrastructure.rest.user.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to update a user's profile")
public class UpdateUserProfileRequest {

	@Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String userId;

	@Schema(description = "Updated first name", example = "John")
	private String name;

	@Schema(description = "Updated last name", example = "Doe")
	private String surname;

	@Schema(description = "Updated password", example = "newSecurePass456")
	private String password;

	@Schema(description = "Updated phone number", example = "+34698765432")
	private String phone;

	public UpdateUserProfileRequest() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
