package segundum.infrastructure.messaging.rabbitmq.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base message DTO for user messages.
 * Uses @JsonTypeInfo to automatically deserialize to the correct subtype based on the "type" field.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = UserRegisteredMessage.class, name = "UserRegistered"),
    @JsonSubTypes.Type(value = UserUpdatedMessage.class, name = "UserUpdated"),
    @JsonSubTypes.Type(value = UserDeletedMessage.class, name = "UserDeleted")
})
public abstract class UserMessage {

}
