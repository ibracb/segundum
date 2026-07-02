package segundum.infrastructure.rest.responses;

/**
 * Represents the response data for a user in the system.
 */
public class UserResponse {
	
	/**
	 * The unique identifier of the user.
	 */
	private String id;
	
	/**
	 * The name of the user.
	 */
	private String name;
	
	/**
	 * The surname of the user.
	 */
	private String surname;
	
	/**
	 * The email of the user.
	 */
	private String email;
	
	/**
	 * The birthdate of the user.
	 */
	private String birthdate;
	
	/**
	 * The phone number of the user.
	 */
	private String phone;
	
	/**
	 * The number of purchases made by the user.
	 */
	private long purchases;
	
	/**
	 * The number of sales made by the user.
	 */
	private long sales;
	
	/**
	 * Constructs a new UserResponse with the given parameters.
	 * 
	 * @param id the unique identifier of the user
	 * @param name the name of the user
	 * @param surname the surname of the user
	 * @param email the email of the user
	 * @param birthdate the birthdate of the user
	 * @param phone the phone number of the user
	 * @param purchases the number of purchases made by the user
	 * @param sales the number of sales made by the user
	 */
	public UserResponse(String id, String name, String surname, String email, String birthdate, String phone, long purchases, long sales) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthdate = birthdate;
		this.phone = phone;
		this.purchases = purchases;
		this.sales = sales;
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
	 * Returns the name of the user.
	 * 
	 * @return the name of the user
	 */
	public String getName() {
		return name;
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
	 * Returns the email of the user.
	 * 
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Returns the birthdate of the user.
	 * 
	 * @return the birthdate of the user
	 */
	public String getBirthdate() {
		return birthdate;
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
	
}
