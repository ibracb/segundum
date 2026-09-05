package segundum.infrastructure.persistence.jpa.event;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleCancelledByPurchaser;
import segundum.domain.events.SaleCancelledBySeller;
import segundum.domain.events.SaleCompleted;
import segundum.domain.events.SaleEvent;
import segundum.domain.events.SaleRejected;
import segundum.domain.events.SaleProposed;
import segundum.domain.events.SaleReserved;
import segundum.domain.models.sale.DateTime;
import segundum.domain.models.sale.PickupLocation;
import segundum.domain.models.sale.Price;
import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.PurchaserName;
import segundum.domain.models.sale.PurchaserSurname;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.domain.models.sale.SellerName;
import segundum.domain.models.sale.SellerSurname;
import segundum.domain.models.sale.Title;

/**
 * Represents the mapper for converting domain events to and from stored event payloads.
 */
@Component
public class EventMapper {

    /**
     * The object mapper for deserializing payloads.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new EventMapper with the given object mapper.
     *
     * @param objectMapper the object mapper for deserializing payloads
     */
    public EventMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Converts the given domain event into a payload map.
     *
     * @param event the domain event
     * @return the payload map
     */
    public Map<String, Object> toPayload(DomainEvent event) {
        if (event instanceof SaleProposed) {
            SaleProposed saleProposed = (SaleProposed) event;
            Map<String, Object> pickupLocation = null;
            if (saleProposed.getPickupLocation() != null) {
                pickupLocation = new HashMap<>();
                pickupLocation.put("description", saleProposed.getPickupLocation().getDescription());
                pickupLocation.put("latitude", saleProposed.getPickupLocation().getLatitude());
                pickupLocation.put("longitude", saleProposed.getPickupLocation().getLongitude());
            }

            Map<String, Object> payload = new HashMap<>();
            payload.put("saleId", saleProposed.getSaleId().asString());
            payload.put("productId", saleProposed.getProductId().getValue().toString());
            payload.put("sellerId", saleProposed.getSellerId().getValue().toString());
            payload.put("sellerName", saleProposed.getSellerName().getValue());
            payload.put("sellerSurname", saleProposed.getSellerSurname().getValue());
            payload.put("purchaserId", saleProposed.getPurchaserId().getValue().toString());
            payload.put("purchaserName", saleProposed.getPurchaserName().getValue());
            payload.put("purchaserSurname", saleProposed.getPurchaserSurname().getValue());
            payload.put("price", saleProposed.getPrice().getValue());
            payload.put("title", saleProposed.getTitle().getValue());
            payload.put("pickupLocation", pickupLocation);
            payload.put("datetime", saleProposed.getDatetime().getValue().toString());
            return payload;
        }
        SaleEvent saleEvent = (SaleEvent) event;
        Map<String, Object> payload = new HashMap<>();
        payload.put("saleId", saleEvent.getSaleId().asString());
        payload.put("productId", saleEvent.getProductId().getValue().toString());
        if (event instanceof SaleCompleted) {
            SaleCompleted saleCompleted = (SaleCompleted) event;
            payload.put("sellerId", saleCompleted.getSellerId().getValue().toString());
            payload.put("purchaserId", saleCompleted.getPurchaserId().getValue().toString());
        }
        return payload;
    }

    /**
     * Converts the given stored event entity into a domain event.
     *
     * @param row the stored event entity
     * @return the domain event
     * @throws UnknownEventTypeException if the event type is not recognized
     */
    public DomainEvent toDomain(StoredEventJpaEntity row) {
        Map<String, Object> payload = readPayload(row.getPayload());
        switch (row.getType()) {
            case "SaleProposed":
                return new SaleProposed(
                        SaleId.fromString((String) payload.get("saleId")),
                        ProductId.fromString((String) payload.get("productId")),
                        SellerId.fromString((String) payload.get("sellerId")),
                        new SellerName((String) payload.get("sellerName")),
                        new SellerSurname((String) payload.get("sellerSurname")),
                        PurchaserId.fromString((String) payload.get("purchaserId")),
                        new PurchaserName((String) payload.get("purchaserName")),
                        new PurchaserSurname((String) payload.get("purchaserSurname")),
                        new Price(asDouble(payload.get("price"))),
                        new Title((String) payload.get("title")),
                        asPickupLocation(payload.get("pickupLocation")),
                        DateTime.fromInstant(Instant.parse((String) payload.get("datetime"))));
            case "SaleReserved":
                return new SaleReserved(
                        SaleId.fromString((String) payload.get("saleId")),
                        ProductId.fromString((String) payload.get("productId")));
            case "SaleRejected":
                return new SaleRejected(
                        SaleId.fromString((String) payload.get("saleId")),
                        ProductId.fromString((String) payload.get("productId")));
            case "SaleCompleted":
                return new SaleCompleted(
                        SaleId.fromString((String) payload.get("saleId")),
                        ProductId.fromString((String) payload.get("productId")),
                        SellerId.fromString((String) payload.get("sellerId")),
                        PurchaserId.fromString((String) payload.get("purchaserId")));
            case "SaleCancelledBySeller":
                return new SaleCancelledBySeller(
                        SaleId.fromString((String) payload.get("saleId")),
                        ProductId.fromString((String) payload.get("productId")));
            case "SaleCancelledByPurchaser":
                return new SaleCancelledByPurchaser(
                        SaleId.fromString((String) payload.get("saleId")),
                        ProductId.fromString((String) payload.get("productId")));
            default:
                throw new UnknownEventTypeException(row.getType());
        }
    }

    /**
     * Reads the given payload string into a map.
     *
     * @param payload the payload string
     * @return the payload map
     * @throws IllegalStateException if the payload cannot be deserialized
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> readPayload(String payload) {
        try {
            return objectMapper.readValue(payload, Map.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Could not deserialize event payload", e);
        }
    }

    /**
     * Converts the given value into a double.
     *
     * @param value the value
     * @return the value as a double
     */
    private double asDouble(Object value) {
        return ((Number) value).doubleValue();
    }

    /**
     * Converts the given value into a pickup location.
     *
     * @param value the payload value
     * @return the pickup location, or null if the value is null
     */
    @SuppressWarnings("unchecked")
    private PickupLocation asPickupLocation(Object value) {
        if (value == null) {
            return null;
        }
        Map<String, Object> pickup = (Map<String, Object>) value;
        return new PickupLocation(
                (String) pickup.get("description"),
                ((Number) pickup.get("latitude")).doubleValue(),
                ((Number) pickup.get("longitude")).doubleValue());
    }

}
