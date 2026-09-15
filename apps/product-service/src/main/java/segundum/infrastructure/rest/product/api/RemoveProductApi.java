package segundum.infrastructure.rest.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.handlers.ErrorResponse;

/**
 * Represents the REST API for removing a product.
 */
@Tag(name = "Products", description = "Product management endpoints")
@RequestMapping("/products")
public interface RemoveProductApi {

	@PreAuthorize("hasAuthority('USER') and @productFinder.findById(T(segundum.domain.models.product.ProductId).fromString(#id)).orElse(null).sellerId == principal")
	@Operation(summary = "Remove a product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Product removed",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Product not found",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "409", description = "Product is not FOR_SALE",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@DeleteMapping("/{id}/remove")
	ResponseEntity<Void> removeProduct(
			@Parameter(description = "Product identifier") @PathVariable("id") String id);

}
