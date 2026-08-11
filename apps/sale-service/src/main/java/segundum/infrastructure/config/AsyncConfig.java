package segundum.infrastructure.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Represents the asynchronous configuration for the sale service.
 */
@Configuration
@EnableAsync
public class AsyncConfig {

	/**
	 * Creates the projection task executor bean.
	 *
	 * @return the projection task executor
	 */
	@Bean("projectionTaskExecutor")
	public Executor projectionTaskExecutor() {
		return Executors.newSingleThreadExecutor();
	}

}
