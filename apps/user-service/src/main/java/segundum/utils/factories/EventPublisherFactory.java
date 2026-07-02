package segundum.utils.factories;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import segundum.domain.events.DomainEventPublisher;
import segundum.utils.exceptions.PublisherInstantiationException;
import segundum.utils.readers.PropertiesReader;

/**
 * A factory class for creating instances of DomainEventPublisher implementations based on the interface class.
 */
public class EventPublisherFactory {

	/**
	 * The name of the properties file that contains the mapping of interface classes to publisher classes.
	 */
    private static final String PROPERTIES = "publishers.properties";

    /**
	 * Private constructor to prevent instantiation of the PublisherFactory class.
	 */
    private EventPublisherFactory() {
    }
    
    /**
	 * Returns an instance of the DomainEventPublisher implementation for the given publisher interface.
	 * @param <T>	The type of the DomainEventPublisher implementation to return.
	 * @param publisherInterface	The publisher interface class for which to obtain the implementation.
	 * @return An instance of the DomainEventPublisher implementation for the given publisher interface.
	 */
    @SuppressWarnings("unchecked")
    public static <T extends DomainEventPublisher> T getPublisher(Class<?> publisherInterface) {
        try {
            PropertiesReader properties = new PropertiesReader(PROPERTIES);
            String publisherClass = properties.getProperty(publisherInterface.getName());
            if (publisherClass == null) {
                throw new PublisherInstantiationException(
                    "No publisher class configured for interface: " + publisherInterface.getName(), null);
            }
            return (T) Class.forName(publisherClass).getConstructor().newInstance();
        } catch (IOException e) {
            throw new PublisherInstantiationException(
                "Error reading properties file for: " + publisherInterface.getName(), e);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            throw new PublisherInstantiationException(
                "Publisher couldn't be obtained: " + publisherInterface.getName(), e);
        }
    }
    
}
