package segundum.infrastructure.rest.sale.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import segundum.infrastructure.rest.sale.responses.SaleAsPurchaserResponse;

/**
 * Represents the API for fetching the sales of a purchaser.
 */
@Tag(name = "Sales", description = "Sale management endpoints")
@RequestMapping("/sales")
public interface GetSalesByPurchaserApi {

    /**
     * Fetches the sales of a purchaser.
     *
     * @param purchaserId the identifier of the purchaser
     * @param status      the optional sale status filter
     * @param pageable    the pagination information
     * @return a page of sales as seen by the purchaser
     */
    @Operation(summary = "Get sales by purchaser", description = "Returns a page of sales made by the given purchaser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sales retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SaleAsPurchaserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid purchaser identifier",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/purchaser/{purchaserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PagedModel<EntityModel<SaleAsPurchaserResponse>>> getSalesByPurchaser(
            @Parameter(description = "Purchaser identifier") @PathVariable("purchaserId") String purchaserId,
            @Parameter(description = "Optional sale status filter (PENDING, RESERVED, REJECTED, CANCELLED, COMPLETED)")
            @RequestParam(value = "status", required = false) String status,
            @PageableDefault(size = 20, page = 0) Pageable pageable);

}
