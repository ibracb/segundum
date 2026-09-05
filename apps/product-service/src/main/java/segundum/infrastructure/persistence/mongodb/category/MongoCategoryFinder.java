package segundum.infrastructure.persistence.mongodb.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.finders.CategoryFinder;
import segundum.domain.models.category.CategoryId;

/**
 * Represents the MongoDB implementation of the category read repository.
 */
public class MongoCategoryFinder implements CategoryFinder {

	/**
	 * The MongoDB template.
	 */
	private final MongoTemplate mongoTemplate;

	/**
	 * Constructs a new MongoCategoryFinder with the given template.
	 *
	 * @param mongoTemplate the MongoDB template
	 */
	public MongoCategoryFinder(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<CategoryReadModel> findRootCategories() {
		Query query = new Query(Criteria.where("parent_category_id").isNull());
		return mongoTemplate.find(query, CategoryReadDocument.class)
				.stream()
				.map(MongoCategoryFinder::toReadModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<CategoryReadModel> findChildrenByParentCategoryId(CategoryId parentId) {
		Query query = new Query(Criteria.where("parent_category_id").is(parentId.getValue()));
		return mongoTemplate.find(query, CategoryReadDocument.class)
				.stream()
				.map(MongoCategoryFinder::toReadModel)
				.collect(Collectors.toList());
	}

	/**
	 * Maps a category read document to a category read model.
	 *
	 * @param doc the category read document
	 * @return the category read model
	 */
	private static CategoryReadModel toReadModel(CategoryReadDocument doc) {
		return new CategoryReadModel(
				doc.getCategoryId(),
				doc.getName(),
				doc.getPath(),
				doc.getDescription(),
				doc.getParentCategoryId());
	}

}
