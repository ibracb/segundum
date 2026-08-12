package segundum.infrastructure.xml;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.domain.events.CategoryCreated;
import segundum.domain.models.category.Category;
import segundum.domain.models.category.CategoryFactory;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.category.Description;
import segundum.domain.models.category.Name;
import segundum.domain.models.category.Path;
import segundum.domain.outbound.CategoryHierarchyLoader;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.CategoryWriteRepository;

/**
 * Represents the loader that reads a category hierarchy from an XML source.
 */
@Component
public class XmlCategoryHierarchyLoader implements CategoryHierarchyLoader {

	/**
	 * The repository used to write categories.
	 */
	private final CategoryWriteRepository categoryWriteRepository;
	/**
	 * The publisher used to publish domain events.
	 */
	private final DomainEventPublisher domainEventPublisher;

	/**
	 * Constructs a new XmlCategoryHierarchyLoader with the given dependencies.
	 *
	 * @param categoryWriteRepository the repository used to write categories
	 * @param domainEventPublisher the publisher used to publish domain events
	 */
	public XmlCategoryHierarchyLoader(CategoryWriteRepository categoryWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		this.categoryWriteRepository = categoryWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}
	
	@Override
	@Transactional
	public void load(String source) {
		if (source == null) {
			throw new IllegalArgumentException("Invalid path to load a category hierarchy");
		}
		try {
			JAXBContext context = JAXBContext.newInstance(CategoryXmlModel.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			CategoryXmlModel model;
			if (source.startsWith("classpath:")) {
				String resourcePath = source.substring("classpath:".length());
				InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
				if (inputStream == null) {
					throw new IllegalArgumentException("Classpath resource not found: " + resourcePath);
				}
				model = (CategoryXmlModel) unmarshaller.unmarshal(inputStream);
			} else {
				model = (CategoryXmlModel) unmarshaller.unmarshal(new File(source));
			}
			saveRecursively(model, null);
		}
		catch (JAXBException e) {
			throw new XmlException("Error loading category hierarchy from: " + source, e);
		}
	}

	/**
	 * Saves the given category model and its subcategories recursively.
	 *
	 * @param model the category model to save
	 * @param parentCategoryId the identifier of the parent category
	 */
	private void saveRecursively(CategoryXmlModel model, CategoryId parentCategoryId) {
		CategoryId categoryId = CategoryId.fromString(model.getId());
		if (categoryWriteRepository.existsById(categoryId)) {
			return;
		}
		Name name = new Name(model.getName());
		Path path = new Path(model.getPath());
		Description description = new Description(model.getDescription());
		Category category = CategoryFactory.create(categoryId, name, path, description, parentCategoryId);
		categoryWriteRepository.create(category);

		domainEventPublisher.publish(new CategoryCreated(
				categoryId, name, path, description, parentCategoryId));

		model.getSubcategories().forEach(subcategory -> {
			saveRecursively(subcategory, categoryId);
		});
	}

}
