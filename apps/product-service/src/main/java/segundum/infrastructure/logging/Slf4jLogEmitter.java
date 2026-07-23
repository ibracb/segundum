package segundum.infrastructure.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segundum.domain.outbound.LogEmitter;

public class Slf4jLogEmitter implements LogEmitter {

	private final Logger log;

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
