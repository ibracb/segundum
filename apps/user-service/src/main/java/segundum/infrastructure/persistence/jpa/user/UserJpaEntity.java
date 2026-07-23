package segundum.infrastructure.persistence.jpa.user;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a user entity in the database.
 */
@Entity
@Table(name = "users")
public class UserJpaEntity {
	
	/**
	 * The unique identifier of the user.
	 */
	@Id
	private String id;
	
	/**
	 * The name of the user.
	 */
	@Column(name = "name", nullable = false)
	private String name;
	
	/**
	 * The surname of the user.
	 */
	@Column(name = "surname", nullable = false)
	private String surname;
	
	/**
	 * The email of the user.
	 */
	@Column(name = "email", nullable = false, updatable = false)
	private String email;
	
	/**
	 * The password of the user.
	 */
	@Column(name = "password", nullable = false)
	private String password;
	
	/**
	 * The birthdate of the user.
	 */
	@Column(name = "birthdate", nullable = false, updatable = false, columnDefinition = "DATE")
	private LocalDate birthdate;
	
	/**
	 * The phone number of the user.
	 */
	@Column(name = "phone", unique = true)
	private String phone;
	
	/**
	 * The number of purchases made by the user.
	 */
	@Column(name = "purchases", nullable = false)
	private long purchases;
	
	/**
	 * The number of sales made by the user.
	 */
	@Column(name = "sales", nullable = false)
	private long sales;

	/**
	 * The status of the user (ACTIVE, DELETED).
	 */
	@Column(name = "status", nullable = false)
	private String status;

	/**
	 * Constructs a new UserEntity with the given parameters.
	 * 
	 * @param id the unique identifier of the user
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
	public UserJpaEntity(String id, String name, String surname, String email, String password, LocalDate birthdate, String phone, long purchases, long sales, String status) {
		this.id = id;
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
	 * Default constructor for JPA.
	 */
	protected UserJpaEntity() {
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
	 * Returns the password of the user.
	 * 
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
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

	/**
	 * Returns the status of the user.
	 *
	 * @return the user status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the user.
	 *
	 * @param status the user status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
