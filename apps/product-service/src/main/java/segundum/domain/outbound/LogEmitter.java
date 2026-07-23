package segundum.domain.outbound;

public interface LogEmitter {

	void info(String message);

	void warn(String message);

	void warn(String message, Throwable cause);

	void error(String message, Throwable cause);

}
