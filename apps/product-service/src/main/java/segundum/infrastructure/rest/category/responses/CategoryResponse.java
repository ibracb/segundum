package segundum.infrastructure.rest.category.responses;

/**
 * Response DTO for category data.
 */
public class CategoryResponse {

	private String id;
	private String name;
	private String path;
	private String description;
	private String parentId;

	public CategoryResponse() {
	}

	public CategoryResponse(String id, String name, String path, String description, String parentId) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getDescription() {
		return description;
	}

	public String getParentId() {
		return parentId;
	}

}
