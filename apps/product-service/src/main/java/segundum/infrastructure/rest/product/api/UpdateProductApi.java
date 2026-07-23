package segundum.infrastructure.rest.product.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.product.requests.UpdateProductRequest;

@Tag(name = "Products", description = "Product management endpoints")
@RequestMapping("/products")
public interface UpdateProductApi {

	@Operation(summary = "Update a product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Product updated",
					content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Product not found",
					content = @Content),
			@ApiResponse(responseCode = "409", description = "Product is not in DRAFT state",
					content = @Content)
	})
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> updateProduct(
			@Parameter(description = "Product identifier") @PathVariable("id") String id,
			@Valid @RequestBody UpdateProductRequest request);

}
