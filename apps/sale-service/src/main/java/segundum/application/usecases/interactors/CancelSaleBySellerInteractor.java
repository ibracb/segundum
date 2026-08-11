package segundum.application.usecases.interactors;

import java.util.List;

import segundum.application.commands.CancelSaleBySellerCommand;
import segundum.application.usecases.CancelSaleBySellerUseCase;
import segundum.domain.events.DomainEvent;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.sale.Sale;
import segundum.domain.models.sale.SaleFactory;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.outbound.EventStore;

/**
 * Represents the interactor for cancelling a sale by the seller.
 */
public class CancelSaleBySellerInteractor implements CancelSaleBySellerUseCase {

    /**
     * The event store for loading and appending domain events.
     */
    private final EventStore eventStore;

    /**
     * The domain event publisher.
     */
    private final DomainEventPublisher eventPublisher;

    /**
     * Constructs a new CancelSaleBySellerInteractor with the given dependencies.
     *
     * @param eventStore     the event store for loading and appending domain events
     * @param eventPublisher the domain event publisher
     */
    public CancelSaleBySellerInteractor(EventStore eventStore, DomainEventPublisher eventPublisher) {
        this.eventStore = eventStore;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void execute(CancelSaleBySellerCommand command) {
        List<DomainEvent> history = eventStore.loadEvents(command.getSaleId());
        if (history.isEmpty()) {
            throw new EntityNotFoundException("Sale", command.getSaleId().asString());
        }

        Sale sale = SaleFactory.loadFromHistory(history);

        sale.cancelBySeller(command.getSellerId());

        List<DomainEvent> events = sale.getUncommittedEvents();
        events.forEach(eventStore::append);
        events.forEach(eventPublisher::publish);
    }

}
