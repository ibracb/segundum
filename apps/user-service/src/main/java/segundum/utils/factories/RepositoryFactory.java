package segundum.utils.factories;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import segundum.domain.repositories.UserRepository;
import segundum.utils.exceptions.RepositoryInstantiationException;
import segundum.utils.readers.PropertiesReader;

/**
 * A factory class for creating instances of UserRepository implementations based on entity classes.
 */
public class RepositoryFactory {
	
	/**
	 * Private constructor to prevent instantiation of the RepositoryFactory class.
	 */
    private RepositoryFactory() {
    }
    
    /**
	 * The name of the properties file that contains the mapping of entity classes to repository classes.
	 */
    private static final String PROPERTIES = "repositories.properties";
    
    /**
     * Returns an instance of the UserRepository implementation for the given entity class.
     * @param <R>	The type of the UserRepository implementation to return.
     * @param entity	The entity class for which to obtain the UserRepository implementation.
     * @return An instance of the UserRepository implementation for the given entity class.
     */
    @SuppressWarnings("unchecked")
    public static <R extends UserRepository> R getUserRepository(Class<?> entity) {
        try {
            PropertiesReader properties = new PropertiesReader(PROPERTIES);
            String entityClass = properties.getProperty(entity.getName());
            if (entityClass == null) {
                throw new RepositoryInstantiationException(
                    "No repository class configured for entity: " + entity.getName(), null);
            }
            return (R) Class.forName(entityClass).getConstructor().newInstance();
        } catch (IOException e) {
            throw new RepositoryInstantiationException(
                "Error reading properties file for entity: " + entity.getName(), e);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            throw new RepositoryInstantiationException(
                "User repository couldn't be obtained: " + entity.getName(), e);
        }
    }

}