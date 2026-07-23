package segundum.infrastructure.logging.fakes;

import java.util.ArrayList;
import java.util.List;

import segundum.domain.outbound.LogEmitter;

public class FakeLogEmitter implements LogEmitter {

	private final List<String> warnings = new ArrayList<>();

	@Override
	public void info(String message) {
	}

	@Override
	public void warn(String message) {
		warnings.add(message);
	}

	@Override
	public void warn(String message, Throwable cause) {
		warnings.add(message);
	}

	@Override
	public void error(String message, Throwable cause) {
	}

	public List<String> getWarnings() {
		return warnings;
	}
}
