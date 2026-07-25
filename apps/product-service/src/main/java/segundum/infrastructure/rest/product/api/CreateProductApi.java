package segundum.infrastructure.rest.product.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.handlers.ErrorResponse;
import segundum.infrastructure.rest.product.requests.CreateProductRequest;

@Tag(name = "Products", description = "Product management endpoints")
@RequestMapping("/products")
public interface CreateProductApi {

	@Operation(summary = "Create a new product")
		@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Product created",
					content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "Seller not found",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "409", description = "Seller is not active",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequest request);

}
