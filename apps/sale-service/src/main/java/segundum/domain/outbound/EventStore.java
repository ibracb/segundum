package segundum.domain.outbound;

import java.util.List;

import segundum.domain.events.DomainEvent;
import segundum.domain.models.AggregateId;
import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;

/**
 * Represents the event store for persisting and loading domain events.
 */
public interface EventStore {

    /**
     * Appends the given domain event to the store.
     *
     * @param event the domain event to append
     */
    void append(DomainEvent event);

    /**
     * Loads the domain events of the aggregate identified by the given identifier.
     *
     * @param aggregateId the identifier of the aggregate
     * @return the list of domain events of the aggregate
     */
    List<DomainEvent> loadEvents(AggregateId aggregateId);

    /**
     * Determines whether there is an open proposal for the given product and purchaser.
     *
     * @param productId   the identifier of the product
     * @param purchaserId the identifier of the purchaser
     * @return true if there is an open proposal, false otherwise
     */
    boolean hasOpenProposal(ProductId productId, PurchaserId purchaserId);

}
