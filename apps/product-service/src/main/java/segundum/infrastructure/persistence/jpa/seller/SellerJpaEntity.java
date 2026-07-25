package segundum.infrastructure.persistence.jpa.seller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JPA entity representing a seller in the persistence layer.
 */
@Entity
@Table(name = "sellers")
public class SellerJpaEntity {

	/**
	 * The seller identifier.
	 */
	@Id
	@Column(name = "id", nullable = false)
	private String id;

	/**
	 * The seller name.
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/**
	 * The seller surname.
	 */
	@Column(name = "surname", nullable = false)
	private String surname;

	/**
	 * The seller email.
	 */
	@Column(name = "email", nullable = false, updatable = false)
	private String email;

	/**
	 * The status of the seller (ACTIVE, INACTIVE).
	 */
	@Column(name = "status", nullable = false)
	private String status;

	/**
	 * Default constructor required by JPA.
	 */
	protected SellerJpaEntity() {
	}

	/**
	 * Constructs a new SellerJpaEntity with the given attributes.
	 *
	 * @param id the seller identifier
	 * @param name the seller name
	 * @param surname the seller surname
	 * @param email the seller email
	 * @param status the seller status
	 */
	public SellerJpaEntity(String id, String name, String surname, String email, String status) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.status = status;
	}

	/**
	 * Returns the seller identifier.
	 *
	 * @return the seller identifier
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the seller name.
	 *
	 * @return the seller name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the seller surname.
	 *
	 * @return the seller surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Returns the seller email.
	 *
	 * @return the seller email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the status of the seller.
	 *
	 * @return the seller status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the seller.
	 *
	 * @param status the seller status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
