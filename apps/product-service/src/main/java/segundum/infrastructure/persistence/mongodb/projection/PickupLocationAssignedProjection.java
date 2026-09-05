package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.PickupLocationAssigned;
import segundum.infrastructure.persistence.mongodb.product.PickupLocationDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

/**
 * Represents the projection that updates the read side when a pickup location is assigned to a product.
 */
@Component
public class PickupLocationAssignedProjection {

	/**
	 * The repository used to persist product read documents.
	 */
	private final ProductReadMongoRepository repository;

	/**
	 * Constructs a new PickupLocationAssignedProjection with the given repository.
	 *
	 * @param repository the product read Mongo repository
	 */
	public PickupLocationAssignedProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(PickupLocationAssigned event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			PickupLocationDocument pickupDoc = new PickupLocationDocument(
					event.getPickupLocation().getDescription(),
					event.getPickupLocation().getLatitude(),
					event.getPickupLocation().getLongitude());
			doc.setPickupLocation(pickupDoc);
			repository.save(doc);
		}
	}

}
