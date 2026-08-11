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
import segundum.infrastructure.rest.sale.responses.SaleAsSellerResponse;

/**
 * Represents the API for fetching the sales of a seller.
 */
@Tag(name = "Sales", description = "Sale management endpoints")
@RequestMapping("/sales")
public interface GetSalesBySellerApi {

    /**
     * Fetches the sales of a seller.
     *
     * @param sellerId the identifier of the seller
     * @param status   the optional sale status filter
     * @param pageable the pagination information
     * @return a page of sales as seen by the seller
     */
    @Operation(summary = "Get sales by seller", description = "Returns a page of sales offered by the given seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sales retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SaleAsSellerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid seller identifier",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/seller/{sellerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PagedModel<EntityModel<SaleAsSellerResponse>>> getSalesBySeller(
            @Parameter(description = "Seller identifier") @PathVariable("sellerId") String sellerId,
            @Parameter(description = "Optional sale status filter (PENDING, RESERVED, REJECTED, CANCELLED, COMPLETED)")
            @RequestParam(value = "status", required = false) String status,
            @PageableDefault(size = 20, page = 0) Pageable pageable);

}
