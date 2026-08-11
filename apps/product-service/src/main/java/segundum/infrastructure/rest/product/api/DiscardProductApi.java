package segundum.infrastructure.rest.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

/**
 * Represents the REST API for discarding a draft product.
 */
@Tag(name = "Products", description = "Product management endpoints")
@RequestMapping("/products")
public interface DiscardProductApi {

	@Operation(summary = "Discard a draft product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Product discarded",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Product not found",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "409", description = "Product is not in DRAFT state",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@DeleteMapping("/{id}/discard")
	ResponseEntity<Void> discardProduct(
			@Parameter(description = "Product identifier") @PathVariable("id") String id);

}
