package segundum.infrastructure.xml;

/**
 * Exception thrown when an XML processing error occurs.
 */
@SuppressWarnings("serial")
public class XmlException extends RuntimeException {
	
	/**
	 * Constructs a new XmlException with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public XmlException(String message, Throwable cause) {
		super(message, cause);
	}

}
