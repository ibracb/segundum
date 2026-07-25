package segundum.infrastructure.rest.product.api;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import segundum.infrastructure.rest.handlers.ErrorResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.product.responses.ProductDetailResponse;

@Tag(name = "Products", description = "Product management endpoints")
@RequestMapping("/products")
public interface GetProductDetailApi {

	@Operation(summary = "Get product detail", description = "Returns full details of a product by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product found",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ProductDetailResponse.class))),
			@ApiResponse(responseCode = "404", description = "Product not found",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<EntityModel<ProductDetailResponse>> getProductDetail(
			@Parameter(description = "The product identifier") @PathVariable("id") String id);

}
