package segundum.infrastructure.persistence.jpa.category;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * JPA entity representing a category in the persistence layer.
 */
@Entity
@Table(name = "categories")
public class CategoryJpaEntity {

	/**
	 * The category identifier.
	 */
	@Id
	private String id;

	/**
	 * The category name.
	 */
	@Column(name = "name", nullable = false, updatable = false)
	private String name;

	/**
	 * The materialized path of the category hierarchy.
	 */
	@Lob
	@Column(name = "path", nullable = false, updatable = false)
	private String path;

	/**
	 * The category description.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "description", nullable = true)
	private String description;

	/**
	 * The parent category identifier.
	 */
	@Column(name = "parent_category_id", nullable = true, updatable = false)
	private String parentCategoryId;

	/**
	 * Default constructor required by JPA.
	 */
	protected CategoryJpaEntity() {
	}

	/**
	 * Constructs a new CategoryJpaEntity with the given attributes.
	 *
	 * @param id the category identifier
	 * @param name the category name
	 * @param path the materialized path
	 * @param description the category description (nullable)
	 * @param parentCategoryId the parent category identifier (nullable)
	 */
	public CategoryJpaEntity(String id, String name, String path, String description, String parentCategoryId) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentCategoryId = parentCategoryId;
	}

	/**
	 * Returns the category identifier.
	 *
	 * @return the category identifier
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the category name.
	 *
	 * @return the category name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the materialized path.
	 *
	 * @return the materialized path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Returns the category description.
	 *
	 * @return the category description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the parent category identifier.
	 *
	 * @return the parent category identifier
	 */
	public String getParentCategoryId() {
		return parentCategoryId;
	}

}
