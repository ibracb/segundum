package segundum.infrastructure.rest.sale.api;

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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.handlers.ErrorResponse;
import segundum.infrastructure.rest.sale.responses.SaleDetailResponse;

/**
 * Represents the API for searching sales with optional filters.
 */
@Tag(name = "Sales", description = "Sale management endpoints")
@RequestMapping("/sales")
public interface SearchSalesApi {

    /**
     * Searches sales with optional filters.
     *
     * @param purchaserId the optional purchaser identifier
     * @param sellerId    the optional seller identifier
     * @param status      the optional sale status filter
     * @param pageable    the pagination information
     * @return a page of sales with full detail
     */
    @Operation(summary = "Search sales", description = "Returns a page of sales matching the optional filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sales retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SaleDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid parameter",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PagedModel<EntityModel<SaleDetailResponse>>> searchSales(
            @Parameter(description = "Optional purchaser identifier")
            @RequestParam(value = "purchaserId", required = false) String purchaserId,
            @Parameter(description = "Optional seller identifier")
            @RequestParam(value = "sellerId", required = false) String sellerId,
            @Parameter(description = "Optional sale status filter (PENDING, RESERVED, REJECTED, CANCELLED, COMPLETED)")
            @RequestParam(value = "status", required = false) String status,
            @PageableDefault(size = 20, page = 0) Pageable pageable);

}
