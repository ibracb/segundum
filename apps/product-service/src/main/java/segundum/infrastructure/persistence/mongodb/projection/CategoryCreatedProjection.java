package segundum.infrastructure.persistence.mongodb.projection;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.CategoryCreated;
import segundum.infrastructure.persistence.mongodb.category.CategoryReadDocument;
import segundum.infrastructure.persistence.mongodb.category.CategoryReadMongoRepository;

@Component
/**
 * Represents the projection that updates the read side when a category is created.
 */
public class CategoryCreatedProjection {

	/**
	 * The repository used to persist category read documents.
	 */
	private final CategoryReadMongoRepository repository;

	/**
	 * Constructs a new CategoryCreatedProjection with the given repository.
	 *
	 * @param repository the category read Mongo repository
	 */
	public CategoryCreatedProjection(CategoryReadMongoRepository repository) {
		this.repository = repository;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(CategoryCreated event) {
		CategoryReadDocument doc = new CategoryReadDocument(
				event.getCategoryId().getValue(),
				event.getName().getValue(),
				event.getPath().getValue(),
				event.getDescription() != null ? event.getDescription().getValue() : null,
				event.getParentCategoryId() != null ? event.getParentCategoryId().getValue() : null);
		repository.save(doc);
	}

}
