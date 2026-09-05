package segundum.application.outbound;

/**
 * Port interface for emitting log messages.
 */
public interface LogEmitter {

	void info(String message);

	void warn(String message);

	void warn(String message, Throwable cause);

	void error(String message, Throwable cause);

}
