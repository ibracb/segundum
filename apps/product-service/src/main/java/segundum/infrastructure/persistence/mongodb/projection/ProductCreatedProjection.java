package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.ProductCreated;
import segundum.infrastructure.persistence.mongodb.category.CategoryReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

@Component
public class ProductCreatedProjection {

	private final ProductReadMongoRepository repository;
	private final MongoTemplate mongoTemplate;

	public ProductCreatedProjection(ProductReadMongoRepository repository,
			MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mongoTemplate = mongoTemplate;
	}

	@EventListener
	@Async("projectionTaskExecutor")
	public void on(ProductCreated event) {
		String categoryName = resolveCategoryName(event.getCategoryId().getValue());

		ProductReadDocument doc = new ProductReadDocument(
				event.getProductId().getValue().toString(),
				event.getTitle().getValue(),
				event.getDescription().getValue(),
				event.getPrice().getValue(),
				event.getPublicationDate().getValue(),
				event.getConditionStatus().name(),
				"DRAFT",
				event.getCategoryId().getValue(),
				categoryName,
				event.isShippingAvailable(),
				null,
				event.getSellerId().getValue().toString(),
				0);
		repository.save(doc);
	}

	private String resolveCategoryName(String categoryId) {
		Query query = new Query(Criteria.where("_id").is(categoryId));
		Optional<CategoryReadDocument> category = Optional.ofNullable(
				mongoTemplate.findOne(query, CategoryReadDocument.class));
		return category.map(CategoryReadDocument::getName).orElse(null);
	}

}
