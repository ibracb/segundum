package segundum.infrastructure.persistence.jpa.event;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Represents a stored domain event entity.
 */
@Entity
@Table(name = "event_store")
public class StoredEventJpaEntity {

    /**
     * The unique identifier of the event.
     */
    @Id
    private String id;

    /**
     * The identifier of the aggregate.
     */
    @Column(name = "aggregate_id", nullable = false, updatable = false)
    private String aggregateId;

    /**
     * The type of the event.
     */
    @Column(name = "type", nullable = false, updatable = false)
    private String type;

    /**
     * The payload of the event.
     */
    @Lob
    @Column(name = "payload", nullable = false, updatable = false)
    private String payload;

    /**
     * The timestamp of the event.
     */
    @Column(name = "timestamp", nullable = false, updatable = false)
    private Instant timestamp;

    /**
     * Constructs a new empty StoredEventJpaEntity for JPA.
     */
    protected StoredEventJpaEntity() {
    }

    /**
     * Constructs a new StoredEventJpaEntity with the given values.
     *
     * @param id          the unique identifier of the event
     * @param aggregateId the identifier of the aggregate
     * @param type        the type of the event
     * @param payload     the payload of the event
     * @param timestamp   the timestamp of the event
     */
    public StoredEventJpaEntity(String id, String aggregateId, String type, String payload, Instant timestamp) {
        this.id = id;
        this.aggregateId = aggregateId;
        this.type = type;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    /**
     * Returns the unique identifier of the event.
     *
     * @return the unique identifier of the event
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the identifier of the aggregate.
     *
     * @return the identifier of the aggregate
     */
    public String getAggregateId() {
        return aggregateId;
    }

    /**
     * Returns the type of the event.
     *
     * @return the type of the event
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the payload of the event.
     *
     * @return the payload of the event
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Returns the timestamp of the event.
     *
     * @return the timestamp of the event
     */
    public Instant getTimestamp() {
        return timestamp;
    }

}
