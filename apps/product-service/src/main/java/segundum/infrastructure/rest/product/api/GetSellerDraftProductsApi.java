package segundum.infrastructure.rest.product.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.handlers.ErrorResponse;
import segundum.infrastructure.rest.product.responses.SellerProductResponse;

/**
 * Represents the REST API for retrieving the draft products of a seller.
 */
@Tag(name = "Products", description = "Product management endpoints")
@RequestMapping("/sellers")
public interface GetSellerDraftProductsApi {

	@Operation(summary = "Get seller draft products", description = "Returns all draft products for a given seller")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Draft products retrieved successfully",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = SellerProductResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid seller identifier",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "Seller not found",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "409", description = "Seller is not active",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping(value = "/{id}/products/drafts", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PagedModel<EntityModel<SellerProductResponse>>> getSellerDraftProducts(
			@Parameter(description = "The seller identifier") @PathVariable("id") String id,
			@PageableDefault(size = 20, page = 0) Pageable pageable);

}
