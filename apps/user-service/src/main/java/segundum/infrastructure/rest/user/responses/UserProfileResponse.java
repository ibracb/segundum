package segundum.infrastructure.rest.user.responses;

/**
 * Represents the public profile data of a user in the system.
 */
public class UserProfileResponse {

	private String id;

	private String name;

	private String surname;

	private String email;

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
