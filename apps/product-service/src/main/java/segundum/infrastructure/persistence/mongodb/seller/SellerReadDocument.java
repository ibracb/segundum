package segundum.infrastructure.persistence.mongodb.seller;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MongoDB read model document for sellers.
 */
@Document(collection = "sellers")
public class SellerReadDocument {

	@Id
	private String sellerId;

	@Field("name")
	private String name;

	@Field("surname")
	private String surname;

	@Field("email")
	private String email;

	@Field("active")
	private boolean active;

	public SellerReadDocument() {
	}

	public SellerReadDocument(String sellerId, String name, String surname, String email, boolean active) {
		this.sellerId = sellerId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.active = active;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
