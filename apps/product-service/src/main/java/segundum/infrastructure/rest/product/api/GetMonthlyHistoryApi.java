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
import segundum.infrastructure.rest.product.responses.ProductSummaryResponse;

@Tag(name = "Products", description = "Product management endpoints")
@RequestMapping("/products")
public interface GetMonthlyHistoryApi {

	@Operation(summary = "Get monthly product history", description = "Returns products published in a given month, sorted by views")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "History retrieved successfully",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ProductSummaryResponse.class)))
	})
	@GetMapping(value = "/history/year/{year}/month/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PagedModel<EntityModel<ProductSummaryResponse>>> getMonthlyHistory(
			@Parameter(description = "The year") @PathVariable("year") int year,
			@Parameter(description = "The month (1-12)") @PathVariable("month") int month,
			@PageableDefault(size = 20, page = 0) Pageable pageable);

}
