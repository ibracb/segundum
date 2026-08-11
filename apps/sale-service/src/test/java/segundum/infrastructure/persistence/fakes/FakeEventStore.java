package segundum.infrastructure.persistence.fakes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleEvent;
import segundum.domain.events.SaleProposed;
import segundum.domain.models.AggregateId;
import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.outbound.EventStore;

/**
 * In-memory implementation of the EventStore for testing.
 */
public class FakeEventStore implements EventStore {

	private final Map<String, List<DomainEvent>> eventsByAggregate = new LinkedHashMap<>();
	private final List<DomainEvent> allEvents = new ArrayList<>();

	@Override
	public void append(DomainEvent event) {
		String key = ((SaleEvent) event).getSaleId().asString();
		eventsByAggregate.computeIfAbsent(key, k -> new ArrayList<>()).add(event);
		allEvents.add(event);
	}

	@Override
	public List<DomainEvent> loadEvents(AggregateId aggregateId) {
		return new ArrayList<>(eventsByAggregate.getOrDefault(aggregateId.asString(), List.of()));
	}

	@Override
	public boolean hasOpenProposal(ProductId productId, PurchaserId purchaserId) {
		return allEvents.stream()
				.filter(SaleProposed.class::isInstance)
				.map(SaleProposed.class::cast)
				.anyMatch(proposal -> proposal.getProductId().equals(productId)
						&& proposal.getPurchaserId().equals(purchaserId));
	}

	public List<DomainEvent> getAppendedEvents() {
		return new ArrayList<>(allEvents);
	}

	public void clear() {
		eventsByAggregate.clear();
		allEvents.clear();
	}

}
