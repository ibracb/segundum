package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.ProductReservationCancelled;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

/**
 * Represents the projection that updates the read side when a product reservation is cancelled.
 */
@Component
public class ProductReservationCancelledProjection {

	/**
	 * The repository used to persist product read documents.
	 */
	private final ProductReadMongoRepository repository;

	/**
	 * Constructs a new ProductReservationCancelledProjection with the given repository.
	 *
	 * @param repository the product read Mongo repository
	 */
	public ProductReservationCancelledProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(ProductReservationCancelled event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			doc.setSaleStatus("FOR_SALE");
			repository.save(doc);
		}
	}

}
