package segundum.infrastructure.persistence.jpa.helpers;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A utility class that provides methods to manage the EntityManager instances for JPA.
 * It uses a ThreadLocal variable to ensure that each thread has its own EntityManager instance.
 * The EntityManagerFactory is initialized with persistence properties that can be overridden by environment variables.
 */
public class EntityManagerHelper {
	
	/**
	 * The EntityManagerFactory instance used to create EntityManager instances.
	 */
	private static EntityManagerFactory entityManagerFactory;
	
	/**
	 * A ThreadLocal variable to hold the EntityManager for each thread.
	 */
	private static final ThreadLocal<EntityManager> entityManagerHolder;
	
	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private EntityManagerHelper() {
	}

	/**
	 * Initializes the EntityManagerFactory and ThreadLocal variable.
	 * The persistence properties can be overridden by environment variables.
	 */
	static {
		Map<String, Object> persistenceProperties = new HashMap<>();
		overridePersistenceProperty(persistenceProperties, "javax.persistence.jdbc.url", "USER_SERVICE_DATASOURCE_URL");
		overridePersistenceProperty(persistenceProperties, "javax.persistence.jdbc.user", "USER_SERVICE_DATASOURCE_USERNAME");
		overridePersistenceProperty(persistenceProperties, "javax.persistence.jdbc.password", "USER_SERVICE_DATASOURCE_PASSWORD");
		overridePersistenceProperty(persistenceProperties, "javax.persistence.jdbc.driver", "USER_SERVICE_DATASOURCE_DRIVER");
		entityManagerFactory = Persistence.createEntityManagerFactory("users", persistenceProperties);
		entityManagerHolder = new ThreadLocal<>();
	}
	
	/**
	 * Overrides the persistence property with the value from the environment variable if it exists.
	 * 
	 * @param persistenceProperties The map of persistence properties to be overridden.
	 * @param propertyName The name of the persistence property to override.
	 * @param environmentVariableName The name of the environment variable to check for a value.
	 */
	private static void overridePersistenceProperty(Map<String, Object> persistenceProperties, String propertyName,
			String environmentVariableName) {
		String environmentVariableValue = getEnvironmentValue(propertyName, environmentVariableName);
		if (environmentVariableValue != null && !environmentVariableValue.trim().isEmpty()) {
			persistenceProperties.put(propertyName, environmentVariableValue);
		}
	}

	/**
	 * Retrieves the value of the environment variable corresponding to the given property name.
	 * It first checks for an exact match, then a normalized version of the property name, and finally
	 * checks for an explicitly provided environment variable name.
	 * 
	 * @param propertyName The name of the persistence property.
	 * @param explicitEnvironmentVariableName The explicit environment variable name to check if no match is found.
	 * @return The value of the environment variable, or null if not found.
	 */
	private static String getEnvironmentValue(String propertyName, String explicitEnvironmentVariableName) {
		String value = System.getenv(propertyName);
		if (value != null && !value.trim().isEmpty()) {
			return value;
		}
		String normalizedName = propertyName.toUpperCase().replace('.', '_');
		value = System.getenv(normalizedName);
		if (value != null && !value.trim().isEmpty()) {
			return value;
		}
		return System.getenv(explicitEnvironmentVariableName);
	}
	
	/**
	 * Retrieves the EntityManager for the current thread. If no EntityManager exists or if it is closed,
	 * a new EntityManager is created and stored in the ThreadLocal variable.
	 * 
	 * @return The EntityManager for the current thread.
	 */
	public static EntityManager getEntityManager() {
		EntityManager entityManager = entityManagerHolder.get();
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = entityManagerFactory.createEntityManager();
			entityManagerHolder.set(entityManager);
		}
		return entityManager;
	}

	/**
	 * Closes the EntityManager for the current thread and removes it from the ThreadLocal variable.
	 * If no EntityManager exists, this method does nothing.
	 */
	public static void closeEntityManager() {
		EntityManager entityManager = entityManagerHolder.get();
		if (entityManager != null) {
			entityManagerHolder.remove();
			entityManager.close();
		}
	}
	
}