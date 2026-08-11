package segundum.infrastructure.persistence.mongodb.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "categories")
/**
 * Represents a category document stored in MongoDB.
 */
public class CategoryReadDocument {

	/**
	 * The identifier of the category.
	 */
	@Id
	private String categoryId;

	/**
	 * The name of the category.
	 */
	@Field("name")
	private String name;

	/**
	 * The path of the category.
	 */
	@Field("path")
	private String path;

	/**
	 * The description of the category.
	 */
	@Field("description")
	private String description;

	/**
	 * The identifier of the parent category, if any.
	 */
	@Field("parent_category_id")
	@Indexed
	private String parentCategoryId;

	/**
	 * Constructs a new CategoryReadDocument.
	 */
	public CategoryReadDocument() {
	}

	/**
	 * Constructs a new CategoryReadDocument with the given data.
	 *
	 * @param categoryId the category identifier
	 * @param name the category name
	 * @param path the category path
	 * @param description the category description
	 * @param parentCategoryId the parent category identifier
	 */
	public CategoryReadDocument(String categoryId, String name, String path,
			String description, String parentCategoryId) {
		this.categoryId = categoryId;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentCategoryId = parentCategoryId;
	}

	/**
	 * Returns the identifier of the category.
	 *
	 * @return the category identifier
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * Returns the name of the category.
	 *
	 * @return the category name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the path of the category.
	 *
	 * @return the category path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Returns the description of the category.
	 *
	 * @return the category description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the identifier of the parent category, if any.
	 *
	 * @return the parent category identifier
	 */
	public String getParentCategoryId() {
		return parentCategoryId;
	}

}
