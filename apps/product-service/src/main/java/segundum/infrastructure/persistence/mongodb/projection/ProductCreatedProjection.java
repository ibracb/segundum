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
/**
 * Represents the projection that updates the read side when a product is created.
 */
public class ProductCreatedProjection {

	/**
	 * The repository used to persist product read documents.
	 */
	private final ProductReadMongoRepository repository;
	/**
	 * The MongoDB template.
	 */
	private final MongoTemplate mongoTemplate;

	/**
	 * Constructs a new ProductCreatedProjection with the given dependencies.
	 *
	 * @param repository the product read Mongo repository
	 * @param mongoTemplate the MongoDB template
	 */
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

	/**
	 * Resolves the name of a category by its identifier.
	 *
	 * @param categoryId the category identifier
	 * @return the category name, or null if the category is not found
	 */
	private String resolveCategoryName(String categoryId) {
		Query query = new Query(Criteria.where("_id").is(categoryId));
		Optional<CategoryReadDocument> category = Optional.ofNullable(
				mongoTemplate.findOne(query, CategoryReadDocument.class));
		return category.map(CategoryReadDocument::getName).orElse(null);
	}

}
