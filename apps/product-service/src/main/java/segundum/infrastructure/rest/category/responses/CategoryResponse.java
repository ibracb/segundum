package segundum.infrastructure.rest.category.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Category information")
public class CategoryResponse {

	@Schema(description = "Category identifier", example = "1")
	private String id;
	@Schema(description = "Category name", example = "Electronics")
	private String name;
	@Schema(description = "Category path in the hierarchy (format: |id|id|...)", example = "|1|2|")
	private String path;
	@Schema(description = "Category description", example = "Electronic devices and accessories")
	private String description;
	@Schema(description = "Parent category identifier", example = "2")
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
