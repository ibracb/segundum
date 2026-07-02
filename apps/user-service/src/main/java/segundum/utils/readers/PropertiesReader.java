package segundum.utils.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class for reading properties from a properties file.
 */
public class PropertiesReader {
	
	/**
	 * The properties object that holds the key-value pairs from the properties file.
	 */
	private Properties properties;
	
	/**
	 * Constructs a new PropertiesReader object and loads the properties from the specified file.
	 * 
	 * @param propertyFileName the name of the properties file to load
	 * @throws IOException if an I/O error occurs while reading the properties file
	 */
	public PropertiesReader(String propertyFileName) throws IOException {
		InputStream is = getClass().getClassLoader()
			.getResourceAsStream(propertyFileName);
		this.properties = new Properties();
		this.properties.load(is);
	}
	
	/**
	 * Retrieves the value of the specified property from the loaded properties.
	 * 
	 * @param propertyName the name of the property to retrieve
	 * @return the value of the specified property, or null if the property is not found
	 */
	public String getProperty(String propertyName) {
		return this.properties.getProperty(propertyName);
	}
}