package segundum.infrastructure.persistence.mongodb.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.repositories.CategoryReadRepository;
import segundum.domain.models.category.CategoryId;

public class MongoCategoryReadRepository implements CategoryReadRepository {

	private final MongoTemplate mongoTemplate;

	public MongoCategoryReadRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<CategoryReadModel> findRootCategories() {
		Query query = new Query(Criteria.where("parent_category_id").isNull());
		return mongoTemplate.find(query, CategoryReadDocument.class)
				.stream()
				.map(MongoCategoryReadRepository::toReadModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<CategoryReadModel> findChildrenByParentCategoryId(CategoryId parentId) {
		Query query = new Query(Criteria.where("parent_category_id").is(parentId.getValue()));
		return mongoTemplate.find(query, CategoryReadDocument.class)
				.stream()
				.map(MongoCategoryReadRepository::toReadModel)
				.collect(Collectors.toList());
	}

	private static CategoryReadModel toReadModel(CategoryReadDocument doc) {
		return new CategoryReadModel(
				doc.getCategoryId(),
				doc.getName(),
				doc.getPath(),
				doc.getDescription(),
				doc.getParentCategoryId());
	}

}
