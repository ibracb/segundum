package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.ProductUpdated;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

@Component
/**
 * Represents the projection that updates the read side when a product is updated.
 */
public class ProductUpdatedProjection {

	/**
	 * The repository used to persist product read documents.
	 */
	private final ProductReadMongoRepository repository;

	/**
	 * Constructs a new ProductUpdatedProjection with the given repository.
	 *
	 * @param repository the product read Mongo repository
	 */
	public ProductUpdatedProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(ProductUpdated event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			if (event.getPrice() != null) {
				doc.setPrice(event.getPrice().getValue());
			}
			if (event.getDescription() != null) {
				doc.setDescription(event.getDescription().getValue());
			}
			if (event.getConditionStatus() != null) {
				doc.setConditionStatus(event.getConditionStatus().name());
			}
			repository.save(doc);
		}
	}

}
