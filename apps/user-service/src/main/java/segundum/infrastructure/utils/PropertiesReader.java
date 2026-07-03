package segundum.infrastructure.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class for reading properties from a properties file and environment variables.
 */
public class PropertiesReader {

	private Properties properties;

	/**
	 * Constructs a new PropertiesReader and loads the properties from the specified file.
	 * @param propertyFileName The name of the properties file to load.
	 * @throws IOException If an error occurs while reading the properties file.
	 */
	public PropertiesReader(String propertyFileName) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		this.properties = new Properties();
		this.properties.load(is);
	}

	/**
	 * Retrieves the value of the specified property from the properties file or environment variables.
	 * @param propertyName The name of the property to retrieve.
	 * @return The value of the property, or null if not found.
	 */
	public String getProperty(String propertyName) {
		String envValue = System.getenv(propertyName);
		if (envValue != null && !envValue.trim().isEmpty()) {
			return envValue;
		}
		String normalizedEnvName = propertyName.toUpperCase().replace('.', '_');
		envValue = System.getenv(normalizedEnvName);
		if (envValue != null && !envValue.trim().isEmpty()) {
			return envValue;
		}
		String propertyValue = this.properties.getProperty(propertyName);
		return resolveEnvironmentPlaceholder(propertyValue);
	}

	/**
	 * Resolves environment variable placeholders in the given value.
	 * @param value The value to resolve.
	 * @return The resolved value, or null if the input value is null.
	 */
	private String resolveEnvironmentPlaceholder(String value) {
		if (value == null) {
			return null;
		}
		String trimmedValue = value.trim();
		if (!trimmedValue.startsWith("${") || !trimmedValue.endsWith("}")) {
			return value;
		}
		String placeholderContent = trimmedValue.substring(2, trimmedValue.length() - 1);
		int defaultSeparatorIndex = placeholderContent.indexOf(':');
		String environmentVariableName = placeholderContent;
		String defaultValue = null;
		if (defaultSeparatorIndex >= 0) {
			environmentVariableName = placeholderContent.substring(0, defaultSeparatorIndex);
			defaultValue = placeholderContent.substring(defaultSeparatorIndex + 1);
		}
		String environmentVariableValue = System.getenv(environmentVariableName);
		if (environmentVariableValue != null && !environmentVariableValue.trim().isEmpty()) {
			return environmentVariableValue;
		}
		return defaultValue;
	}

}
