package segundum.infrastructure.messaging.messages;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.UserDeactivated;
import segundum.domain.events.UserRegistered;
import segundum.domain.events.UserUpdated;

/**
 * Represents a mapper that converts domain events into their corresponding message representations.
 */
public class UserMessageMapper {

    /**
     * Maps the given domain event to its corresponding message.
     *
     * @param event the domain event to map
     * @return the message representation of the event
     * @throws IllegalArgumentException if the event type is not supported
     */
    public Object map(DomainEvent event) {
        if (event instanceof UserRegistered) return map((UserRegistered) event);
        else if (event instanceof UserUpdated) return map((UserUpdated) event);
        else if (event instanceof UserDeactivated) return map((UserDeactivated) event);
        else throw new IllegalArgumentException("Unsupported event type: " + event.getClass().getSimpleName());
    }

    /**
     * Maps the given user registered event to a message.
     *
     * @param event the user registered event to map
     * @return the user registered message
     */
    private UserRegisteredMessage map(UserRegistered event) {
        return new UserRegisteredMessage(
                event.getEventId().toString(),
                event.getType(),
                event.getTimestamp().toString(),
                event.getUserId().getValue().toString(),
                event.getName().getValue(),
                event.getSurname().getValue(),
                event.getEmail().getValue());
    }

    /**
     * Maps the given user updated event to a message.
     *
     * @param event the user updated event to map
     * @return the user updated message
     */
    private UserUpdatedMessage map(UserUpdated event) {
        return new UserUpdatedMessage(
                event.getEventId().toString(),
                event.getType(),
                event.getTimestamp().toString(),
                event.getUserId().getValue().toString(),
                event.getName().getValue(),
                event.getSurname().getValue());
    }

    /**
     * Maps the given user deactivated event to a message.
     *
     * @param event the user deactivated event to map
     * @return the user deactivated message
     */
    private UserDeactivatedMessage map(UserDeactivated event) {
        return new UserDeactivatedMessage(
                event.getEventId().toString(),
                event.getType(),
                event.getTimestamp().toString(),
                event.getUserId().getValue().toString());
    }

}
