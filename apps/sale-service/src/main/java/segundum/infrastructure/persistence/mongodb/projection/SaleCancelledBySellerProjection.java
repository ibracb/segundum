package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.SaleCancelledBySeller;
import segundum.infrastructure.persistence.mongodb.sale.SaleReadDocument;
import segundum.infrastructure.persistence.mongodb.sale.SaleReadMongoRepository;

/**
 * Represents the projection that updates the read model when a sale is cancelled by the seller.
 */
@Component
public class SaleCancelledBySellerProjection {

	/**
	 * The repository for the sale read model.
	 */
	private final SaleReadMongoRepository repository;

	/**
	 * Constructs a new SaleCancelledBySellerProjection with the given repository.
	 *
	 * @param repository the repository for the sale read model
	 */
	public SaleCancelledBySellerProjection(SaleReadMongoRepository repository) {
		this.repository = repository;
	}

	/**
	 * Updates the read model of the cancelled sale.
	 *
	 * @param event the cancelled by seller event
	 */
	@EventListener
	@Async("projectionTaskExecutor")
	public void on(SaleCancelledBySeller event) {
		String saleId = event.getSaleId().asString();
		Optional<SaleReadDocument> optional = repository.findById(saleId);
		if (optional.isPresent()) {
			SaleReadDocument doc = optional.get();
			doc.setStatus("CANCELLED");
			repository.save(doc);
		}
	}

}
