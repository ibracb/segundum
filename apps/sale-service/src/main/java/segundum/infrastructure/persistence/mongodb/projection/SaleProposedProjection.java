package segundum.infrastructure.persistence.mongodb.projection;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.stereotype.Component;

import segundum.domain.events.SaleProposed;
import segundum.infrastructure.persistence.mongodb.sale.SalePickupLocationDocument;
import segundum.infrastructure.persistence.mongodb.sale.SaleProductDocument;
import segundum.infrastructure.persistence.mongodb.sale.SalePurchaserDocument;
import segundum.infrastructure.persistence.mongodb.sale.SaleReadDocument;
import segundum.infrastructure.persistence.mongodb.sale.SaleReadMongoRepository;
import segundum.infrastructure.persistence.mongodb.sale.SaleSellerDocument;

/**
 * Represents the projection that creates the read model when a sale is proposed.
 */
@Component
public class SaleProposedProjection {

	/**
	 * The repository for the sale read model.
	 */
	private final SaleReadMongoRepository repository;

	/**
	 * Constructs a new SaleProposedProjection with the given repository.
	 *
	 * @param repository the repository for the sale read model
	 */
	public SaleProposedProjection(SaleReadMongoRepository repository) {
		this.repository = repository;
	}

	/**
	 * Creates the read model of the proposed sale.
	 *
	 * @param event the proposed event
	 */
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(SaleProposed event) {
		SaleReadDocument doc = new SaleReadDocument();
		doc.setId(event.getSaleId().asString());
		doc.setStatus("PENDING");
		doc.setDatetime(event.getDatetime().getValue().toString());

		String sellerId = event.getSellerId().getValue().toString();
		String purchaserId = event.getPurchaserId().getValue().toString();

		SalePickupLocationDocument pickup = null;
		if (event.getPickupLocation() != null) {
			pickup = new SalePickupLocationDocument();
			pickup.setDescription(event.getPickupLocation().getDescription());
			pickup.setLatitude(event.getPickupLocation().getLatitude());
			pickup.setLongitude(event.getPickupLocation().getLongitude());
		}

		SaleProductDocument product = new SaleProductDocument();
		product.setProductId(event.getProductId().getValue().toString());
		product.setTitle(event.getTitle().getValue());
		product.setPrice(event.getPrice().getValue());
		product.setPickupLocation(pickup);
		product.setSellerId(sellerId);

		SaleSellerDocument seller = new SaleSellerDocument();
		seller.setId(sellerId);
		seller.setName(event.getSellerName().getValue());
		seller.setSurname(event.getSellerSurname().getValue());

		SalePurchaserDocument purchaser = new SalePurchaserDocument();
		purchaser.setId(purchaserId);
		purchaser.setName(event.getPurchaserName().getValue());
		purchaser.setSurname(event.getPurchaserSurname().getValue());

		doc.setProduct(product);
		doc.setSellerId(sellerId);
		doc.setPurchaserId(purchaserId);
		doc.setSeller(seller);
		doc.setPurchaser(purchaser);

		repository.save(doc);
	}

}
