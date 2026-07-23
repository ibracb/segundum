package segundum.infrastructure.persistence.mongodb.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "categories")
public class CategoryReadDocument {

	@Id
	private String categoryId;

	@Field("name")
	private String name;

	@Field("path")
	private String path;

	@Field("description")
	private String description;

	@Field("parent_category_id")
	private String parentCategoryId;

	public CategoryReadDocument() {
	}

	public CategoryReadDocument(String categoryId, String name, String path,
			String description, String parentCategoryId) {
		this.categoryId = categoryId;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentCategoryId = parentCategoryId;
	}

	public String getCategoryId() {
		return categoryId;
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

	public String getParentCategoryId() {
		return parentCategoryId;
	}

}
