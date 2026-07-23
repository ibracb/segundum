package segundum.infrastructure.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuration class for OpenAPI documentation.
 */
@Configuration
public class OpenApiConfig {
	
	/**
	 * Configures the OpenAPI documentation for the Product Service API.
	 *
	 * @return an OpenAPI instance with API information
	 */
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.info(new Info()
						.title("SegundUM - Product Service API")
						.description("REST API for managing products, categories, and sellers in the marketplace")
						.version("1.0.0"));
	}

}
