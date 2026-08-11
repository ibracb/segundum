package segundum.infrastructure.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
/**
 * Represents the configuration for asynchronous processing.
 */
public class AsyncConfig {

	/**
	 * Creates the executor used for projection tasks.
	 *
	 * @return the projection task executor
	 */
	@Bean("projectionTaskExecutor")
	public Executor projectionTaskExecutor() {
		return Executors.newSingleThreadExecutor();
	}

}
