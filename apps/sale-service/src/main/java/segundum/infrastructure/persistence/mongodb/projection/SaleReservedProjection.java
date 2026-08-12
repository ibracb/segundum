package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.stereotype.Component;

import segundum.domain.events.SaleReserved;
import segundum.infrastructure.persistence.mongodb.sale.SaleReadDocument;
import segundum.infrastructure.persistence.mongodb.sale.SaleReadMongoRepository;

/**
 * Represents the projection that updates the read model when a sale is reserved.
 */
@Component
public class SaleReservedProjection {

	/**
	 * The repository for the sale read model.
	 */
	private final SaleReadMongoRepository repository;

	/**
	 * Constructs a new SaleReservedProjection with the given repository.
	 *
	 * @param repository the repository for the sale read model
	 */
	public SaleReservedProjection(SaleReadMongoRepository repository) {
		this.repository = repository;
	}

	/**
	 * Updates the read model of the reserved sale.
	 *
	 * @param event the reserved event
	 */
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(SaleReserved event) {
		String saleId = event.getSaleId().asString();
		Optional<SaleReadDocument> optional = repository.findById(saleId);
		if (optional.isPresent()) {
			SaleReadDocument doc = optional.get();
			doc.setStatus("RESERVED");
			repository.save(doc);
		}
	}

}
