package segundum.infrastructure.rest.user.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Public profile data of a user")
public class UserProfileResponse {

	@Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String id;

	@Schema(description = "User's first name", example = "John")
	private String name;

	@Schema(description = "User's last name", example = "Doe")
	private String surname;

	@Schema(description = "User's email address", example = "john.doe@example.com")
	private String email;

	@Schema(description = "User's phone number", example = "+34612345678")
	private String phone;

	public UserProfileResponse(String id, String name, String surname, String email, String phone) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

}
