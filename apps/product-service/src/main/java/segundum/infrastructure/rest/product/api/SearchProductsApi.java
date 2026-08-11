package segundum.infrastructure.rest.product.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.product.responses.ProductSearchResultResponse;

/**
 * Represents the REST API for searching products.
 */
@Tag(name = "Products", description = "Product management endpoints")
@RequestMapping("/products")
public interface SearchProductsApi {

	@Operation(summary = "Search products", description = "Search products by category, description, condition and price")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Search results retrieved successfully",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ProductSearchResultResponse.class)))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PagedModel<EntityModel<ProductSearchResultResponse>>> search(
			@RequestParam(required = false) String categoryId,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String condition,
			@RequestParam(required = false) Double maxPrice,
			@PageableDefault(size = 20, page = 0) Pageable pageable);

}
