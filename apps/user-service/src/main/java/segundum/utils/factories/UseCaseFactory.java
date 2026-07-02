package segundum.utils.factories;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import segundum.utils.exceptions.UseCaseInstantiationException;
import segundum.utils.readers.PropertiesReader;

/**
 * A factory class for creating instances of use case implementations based on use case interfaces.
 */
public class UseCaseFactory {

	/**
	 * Private constructor to prevent instantiation of the UseCaseFactory class.
	 */
    private UseCaseFactory() {
    }

    /**
	 * The name of the properties file that contains the mapping of use case interfaces to use case classes.
	 */
    private static final String PROPERTIES = "usecases.properties";

    /**
     * Returns an instance of the use case implementation for the given use case interface.
     * @param <T>	The type of the use case implementation to return.
     * @param useCaseInterface	The use case interface class for which to obtain the implementation.
     * @return An instance of the use case implementation for the given use case interface.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getUseCase(Class<?> useCaseInterface) {
        try {
            PropertiesReader properties = new PropertiesReader(PROPERTIES);
            String useCaseClass = properties.getProperty(useCaseInterface.getName());
            if (useCaseClass == null) {
                throw new UseCaseInstantiationException(
                    "No use case class configured for interface: " + useCaseInterface.getName(), null);
            }
            return (T) Class.forName(useCaseClass).getConstructor().newInstance();
        } catch (IOException e) {
            throw new UseCaseInstantiationException(
                "Error reading properties file for interface: " + useCaseInterface.getName(), e);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            throw new UseCaseInstantiationException(
                "Use case couldn't be obtained: " + useCaseInterface.getName(), e);
        }
    }

}
