package segundum.infrastructure.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a category as defined in an XML file.
 */
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryXmlModel {

	/**
	 * The unique identifier of the category.
	 */
	@XmlAttribute
	private String id;

	/**
	 * The path of the category.
	 */
	@XmlAttribute(name = "path")
	private String path;

	/**
	 * The name of the category.
	 */
	@XmlElement(name = "name")
	private String name;

	/**
	 * The description of the category.
	 */
	@XmlElement(name = "description")
	private String description;

	/**
	 * The subcategories of the category.
	 */
	@XmlElement(name = "category")
	private List<CategoryXmlModel> subcategories;

	/**
	 * Constructs a new CategoryXmlModel with an empty list of subcategories.
	 */
	public CategoryXmlModel() {
		this.subcategories = new LinkedList<>();
	}

	/**
	 * Returns the unique identifier of the category.
	 *
	 * @return the unique identifier of the category
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the path of the category.
	 *
	 * @return the path of the category
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Returns the name of the category.
	 *
	 * @return the name of the category
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of the category.
	 *
	 * @return the description of the category
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the subcategories of the category.
	 *
	 * @return the subcategories of the category
	 */
	public List<CategoryXmlModel> getSubcategories() {
		return subcategories;
	}

}
