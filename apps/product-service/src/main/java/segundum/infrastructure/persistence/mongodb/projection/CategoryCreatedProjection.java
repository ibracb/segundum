package segundum.infrastructure.persistence.mongodb.projection;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.CategoryCreated;
import segundum.infrastructure.persistence.mongodb.category.CategoryReadDocument;
import segundum.infrastructure.persistence.mongodb.category.CategoryReadMongoRepository;

@Component
public class CategoryCreatedProjection {

	private final CategoryReadMongoRepository repository;

	public CategoryCreatedProjection(CategoryReadMongoRepository repository) {
		this.repository = repository;
	}

	@EventListener
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
