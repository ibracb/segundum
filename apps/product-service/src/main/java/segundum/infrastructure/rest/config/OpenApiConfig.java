package segundum.infrastructure.rest.config;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

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

	/**
	 * Declares the internal server error response for every documented operation, so that the
	 * same 500 does not have to be repeated endpoint by endpoint.
	 *
	 * @return a customiser that adds the shared 500 response to the OpenAPI document
	 */
	@Bean
	public OpenApiCustomiser internalServerErrorCustomiser() {
		return openAPI -> {
			if (openAPI == null || openAPI.getPaths() == null) {
				return;
			}
			openAPI.getPaths().forEach((path, pathItem) ->
					pathItem.readOperations().forEach(operation -> {
						if (operation.getResponses() == null) {
							operation.setResponses(new ApiResponses());
						}
						operation.getResponses().put("500", internalServerError());
					}));
		};
	}

	/**
	 * Builds the shared OpenAPI model of the internal server error response.
	 *
	 * @return the OpenAPI model for the 500 response
	 */
	private ApiResponse internalServerError() {
		Schema<Object> schema = new Schema<>();
		schema.set$ref("#/components/schemas/ErrorResponse");
		MediaType json = new MediaType();
		json.setSchema(schema);
		Content content = new Content();
		content.addMediaType("application/json", json);
		ApiResponse response = new ApiResponse();
		response.setDescription("Internal server error");
		response.setContent(content);
		return response;
	}

}
