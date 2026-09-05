package segundum.infrastructure.persistence.jpa.event;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleEvent;
import segundum.domain.models.AggregateId;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SaleFactory;
import segundum.domain.models.sale.SaleId;
import segundum.application.outbound.EventStore;

/**
 * Represents the JPA-based implementation of the event store.
 */
@Repository
public class JpaEventStore implements EventStore {

    /**
     * The JPA repository for stored events.
     */
    private final StoredEventJpaRepository eventJpaRepository;

    /**
     * The mapper for converting domain events to and from stored event payloads.
     */
    private final EventMapper eventMapper;

    /**
     * The object mapper for serializing payloads.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new JpaEventStore with the given dependencies.
     *
     * @param eventJpaRepository the JPA repository for stored events
     * @param eventMapper        the mapper for converting domain events to and from stored event payloads
     * @param objectMapper       the object mapper for serializing payloads
     */
    public JpaEventStore(StoredEventJpaRepository eventJpaRepository, EventMapper eventMapper,
            ObjectMapper objectMapper) {
        this.eventJpaRepository = eventJpaRepository;
        this.eventMapper = eventMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public void append(DomainEvent event) {
        SaleEvent saleEvent = (SaleEvent) event;
        StoredEventJpaEntity entity = new StoredEventJpaEntity(
                event.getEventId().toString(),
                saleEvent.getSaleId().asString(),
                event.getType(),
                serialize(eventMapper.toPayload(event)),
                event.getTimestamp());
        eventJpaRepository.save(entity);
    }

    @Override
    public List<DomainEvent> loadEvents(AggregateId aggregateId) {
        return eventJpaRepository.findByAggregateIdOrderByTimestamp(aggregateId.asString())
                .stream()
                .map(eventMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasOpenProposal(ProductId productId, PurchaserId purchaserId) {
        List<StoredEventJpaEntity> rows = eventJpaRepository.findSaleProposedByProductAndPurchaser(
                productId.getValue().toString(), purchaserId.getValue().toString());
        for (StoredEventJpaEntity row : rows) {
            List<DomainEvent> history = loadEvents(SaleId.fromString(row.getAggregateId()));
            OrderStatus status = SaleFactory.loadFromHistory(history).getOrderStatus();
            if (status == OrderStatus.PENDING || status == OrderStatus.RESERVED) {
                return true;
            }
        }
        return false;
    }

    /**
     * Serializes the given payload map into a string.
     *
     * @param payload the payload map
     * @return the serialized payload string
     * @throws IllegalStateException if the payload cannot be serialized
     */
    private String serialize(Map<String, Object> payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Could not serialize event payload", e);
        }
    }

}
