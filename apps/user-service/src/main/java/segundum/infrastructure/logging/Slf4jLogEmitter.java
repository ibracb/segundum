package segundum.infrastructure.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segundum.application.outbound.LogEmitter;

/**
 * Represents a log emitter backed by SLF4J.
 */
public class Slf4jLogEmitter implements LogEmitter {

	/**
	 * The SLF4J logger used to emit log messages.
	 */
	private final Logger log;

	/**
	 * Constructs a new Slf4jLogEmitter for the given class.
	 *
	 * @param clazz the class for which log messages are emitted
	 */
	public Slf4jLogEmitter(Class<?> clazz) {
		this.log = LoggerFactory.getLogger(clazz);
	}

	@Override
	public void info(String message) {
		log.info(message);
	}

	@Override
	public void warn(String message) {
		log.warn(message);
	}

	@Override
	public void warn(String message, Throwable cause) {
		log.warn(message, cause);
	}

	@Override
	public void error(String message, Throwable cause) {
		log.error(message, cause);
	}

}
