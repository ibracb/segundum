package segundum.infrastructure.rest.requests;

/**
 * Represents a request to update a user's profile.
 */
public class UpdateUserProfileRequest {
	
	/**
	 * The unique identifier of the user to be updated.
	 */
	private String userId;
	
	/**
	 * The name of the user to be updated.
	 */
	private String name;
	
	/**
	 * The surname of the user to be updated.
	 */
	private String surname;
	
	/**
	 * The password of the user to be updated.
	 */
	private String password;
	
	/**
	 * The phone number of the user to be updated.
	 */
	private String phone;
	
	/**
	 * Default constructor for UpdateUserProfileRequest.
	 */
	public UpdateUserProfileRequest() {
		// Default constructor
	}
	
	/**
	 * Returns the unique identifier of the user to be updated.
	 * 
	 * @return the unique identifier of the user to be updated
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Sets the unique identifier of the user to be updated.
	 * 
	 * @param userId the unique identifier of the user to be updated
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Returns the name of the user to be updated.
	 * 
	 * @return the name of the user to be updated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user to be updated.
	 * 
	 * @param name the name of the user to be updated
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the surname of the user to be updated.
	 * 
	 * @return the surname of the user to be updated
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname of the user to be updated.
	 * 
	 * @param surname the surname of the user to be updated
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Returns the password of the user to be updated.
	 * 
	 * @return the password of the user to be updated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user to be updated.
	 * 
	 * @param password the password of the user to be updated
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the phone number of the user to be updated.
	 * 
	 * @return the phone number of the user to be updated
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number of the user to be updated.
	 * 
	 * @param phone the phone number of the user to be updated
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
