package segundum.infrastructure.persistence.mongodb.sale;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Denormalized seller information embedded in a sale document.
 */
public class SaleSellerDocument {

	@Field("id")
	private String id;

	@Field("name")
	private String name;

	@Field("surname")
	private String surname;

	public SaleSellerDocument() {
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getSurname() { return surname; }
	public void setSurname(String surname) { this.surname = surname; }

}
