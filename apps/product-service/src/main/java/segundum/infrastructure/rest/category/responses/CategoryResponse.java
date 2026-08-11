package segundum.infrastructure.rest.category.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Category information")
/**
 * Represents the response containing the information of a category.
 */
public class CategoryResponse {

	/**
	 * The identifier of the category.
	 */
	@Schema(description = "Category identifier", example = "1")
	private String id;
	/**
	 * The name of the category.
	 */
	@Schema(description = "Category name", example = "Electronics")
	private String name;
	/**
	 * The path of the category in the hierarchy.
	 */
	@Schema(description = "Category path in the hierarchy (format: |id|id|...)", example = "|1|2|")
	private String path;
	/**
	 * The description of the category.
	 */
	@Schema(description = "Category description", example = "Electronic devices and accessories")
	private String description;
	/**
	 * The identifier of the parent category, if any.
	 */
	@Schema(description = "Parent category identifier", example = "2")
	private String parentId;

	/**
	 * Constructs a new CategoryResponse.
	 */
	public CategoryResponse() {
	}

	/**
	 * Constructs a new CategoryResponse with the given data.
	 *
	 * @param id the category identifier
	 * @param name the category name
	 * @param path the category path
	 * @param description the category description
	 * @param parentId the parent category identifier
	 */
	public CategoryResponse(String id, String name, String path, String description, String parentId) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentId = parentId;
	}

	/**
	 * Returns the identifier of the category.
	 *
	 * @return the category identifier
	 */
	public String getId() {
		return id;
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
	public String getParentId() {
		return parentId;
	}

}
