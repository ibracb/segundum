package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.ProductTakenDown;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

/**
 * Represents the projection that updates the read side when a product is taken down.
 */
@Component
public class ProductTakenDownProjection {

	/**
	 * The repository used to persist product read documents.
	 */
	private final ProductReadMongoRepository repository;

	/**
	 * Constructs a new ProductTakenDownProjection with the given repository.
	 *
	 * @param repository the product read Mongo repository
	 */
	public ProductTakenDownProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(ProductTakenDown event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			doc.setSaleStatus("DRAFT");
			repository.save(doc);
		}
	}

}
