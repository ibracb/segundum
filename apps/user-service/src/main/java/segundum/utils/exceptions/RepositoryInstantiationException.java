package segundum.utils.exceptions;

/**
 * Exception thrown when there is an error instantiating a repository.
 */
@SuppressWarnings("serial")
public class RepositoryInstantiationException extends RuntimeException {
	
	/**
	 * Constructs a new RepositoryInstantiationException with the specified detail message and cause.
	 * @param message	The detail message
	 * @param cause	The cause of the exception
	 */
    public RepositoryInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
