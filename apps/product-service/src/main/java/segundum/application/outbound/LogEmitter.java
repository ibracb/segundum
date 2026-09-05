package segundum.application.outbound;

/**
 * Represents an emitter for logging messages.
 */
public interface LogEmitter {

	/**
	 * Emits an informational log message.
	 *
	 * @param message the message to log
	 */
	void info(String message);

	/**
	 * Emits a warning log message.
	 *
	 * @param message the message to log
	 */
	void warn(String message);

	/**
	 * Emits a warning log message with a cause.
	 *
	 * @param message the message to log
	 * @param cause the cause of the warning
	 */
	void warn(String message, Throwable cause);

	/**
	 * Emits an error log message with a cause.
	 *
	 * @param message the message to log
	 * @param cause the cause of the error
	 */
	void error(String message, Throwable cause);

}
