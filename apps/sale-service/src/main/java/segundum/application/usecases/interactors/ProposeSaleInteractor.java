package segundum.application.usecases.interactors;

import java.util.List;

import segundum.application.commands.ProposeSaleCommand;
import segundum.application.gateways.GetProductBasicInfo;
import segundum.application.gateways.GetPurchaser;
import segundum.application.gateways.GetSeller;
import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.application.readmodels.purchaser.PurchaserReadModel;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.application.usecases.ProposeSaleUseCase;
import segundum.domain.events.DomainEvent;
import segundum.domain.exceptions.sale.creation.ProductNotForSaleException;
import segundum.domain.exceptions.sale.creation.SaleAlreadyProposedException;
import segundum.domain.models.sale.DateTime;
import segundum.domain.models.sale.PickupLocation;
import segundum.domain.models.sale.Price;
import segundum.domain.models.sale.PurchaserName;
import segundum.domain.models.sale.PurchaserSurname;
import segundum.domain.models.sale.Sale;
import segundum.domain.models.sale.SaleFactory;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.domain.models.sale.SellerName;
import segundum.domain.models.sale.SellerSurname;
import segundum.domain.models.sale.Title;
import segundum.application.outbound.DomainEventPublisher;
import segundum.application.outbound.EventStore;

/**
 * Represents the interactor for proposing a sale.
 */
public class ProposeSaleInteractor implements ProposeSaleUseCase {
	
	/**
	 * The sale status value that indicates a product is for sale.
	 */
	private static final String FOR_SALE = "FOR_SALE";
	
    /**
     * The gateway for fetching basic product information.
     */
    private final GetProductBasicInfo productGateway;

    /**
     * The gateway for fetching a seller.
     */
    private final GetSeller sellerGateway;

    /**
     * The gateway for fetching a purchaser.
     */
    private final GetPurchaser purchaserGateway;

    /**
     * The event store for appending domain events.
     */
    private final EventStore eventStore;

    /**
     * The domain event publisher.
     */
    private final DomainEventPublisher eventPublisher;

    /**
     * Constructs a new ProposeSaleInteractor with the given dependencies.
     *
     * @param productGateway  the gateway for fetching basic product information
     * @param sellerGateway   the gateway for fetching a seller
     * @param purchaserGateway the gateway for fetching a purchaser
     * @param eventStore      the event store for appending domain events
     * @param eventPublisher  the domain event publisher
     */
    public ProposeSaleInteractor(GetProductBasicInfo productGateway, GetSeller sellerGateway,
            GetPurchaser purchaserGateway, EventStore eventStore, DomainEventPublisher eventPublisher) {
        this.productGateway = productGateway;
        this.sellerGateway = sellerGateway;
        this.purchaserGateway = purchaserGateway;
        this.eventStore = eventStore;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public SaleId execute(ProposeSaleCommand command) {
        ProductBasicInfoReadModel product = productGateway.fetch(command.getProductId());
        if(!FOR_SALE.equals(product.getSaleStatus())) {
        	throw new ProductNotForSaleException();
        }
        if (eventStore.hasOpenProposal(command.getProductId(), command.getPurchaserId())) {
            throw new SaleAlreadyProposedException();
        }
        SellerReadModel seller = sellerGateway.fetch(SellerId.fromString(product.getSellerId()));
        PurchaserReadModel purchaser = purchaserGateway.fetch(command.getPurchaserId());
        SaleId saleId = SaleId.generate();
        PickupLocation pickupLocation = product.getPickupLocation() != null
                ? new PickupLocation(product.getPickupLocation().getDescription(),
                        product.getPickupLocation().getLatitude(),
                        product.getPickupLocation().getLongitude())
                : null;
        Sale sale = SaleFactory.create(saleId, command.getProductId(),
                SellerId.fromString(product.getSellerId()),
                new SellerName(seller.getName()), new SellerSurname(seller.getSurname()),
                command.getPurchaserId(),
                new PurchaserName(purchaser.getName()), new PurchaserSurname(purchaser.getSurname()),
                new Price(product.getPrice()), new Title(product.getTitle()),
                pickupLocation,
                DateTime.now());
        List<DomainEvent> events = sale.getUncommittedEvents();
        events.forEach(eventStore::append);
        events.forEach(eventPublisher::publish);
        return saleId;
    }

}
