package segundum.infrastructure.persistence.jpa.event;

/**
 * Exception thrown when the event type is not recognized.
 */
@SuppressWarnings("serial")
public class UnknownEventTypeException extends RuntimeException {

    /**
     * Constructs a new UnknownEventTypeException for the given type.
     *
     * @param type the unknown event type
     */
    public UnknownEventTypeException(String type) {
        super("Unknown event type: " + type);
    }

}
