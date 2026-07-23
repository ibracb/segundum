package segundum.infrastructure.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryXmlModel {

	@XmlAttribute
	private String id;

	@XmlAttribute(name = "path")
	private String path;

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "description")
	private String description;

	@XmlElement(name = "category")
	private List<CategoryXmlModel> subcategories;

	public CategoryXmlModel() {
		this.subcategories = new LinkedList<>();
	}

	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<CategoryXmlModel> getSubcategories() {
		return subcategories;
	}

}
