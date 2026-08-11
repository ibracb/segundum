package segundum.infrastructure.rest.sale.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.handlers.ErrorResponse;
import segundum.infrastructure.rest.sale.requests.CancelSaleBySellerRequest;

/**
 * Represents the API for cancelling a sale by the seller.
 */
@Tag(name = "Sales", description = "Sale management endpoints")
@RequestMapping("/sales")
public interface CancelSaleBySellerApi {

    /**
     * Cancels a sale by the seller.
     *
     * @param id      the identifier of the sale
     * @param request the cancellation request
     * @return an empty response
     */
    @Operation(summary = "Cancel a sale by the seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sale cancelled",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Sale is not owned by seller or not in PENDING status",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping(value = "/{id}/cancel-by-seller", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> cancelSaleBySeller(
            @Parameter(description = "Sale identifier") @PathVariable("id") String id,
            @Valid @RequestBody CancelSaleBySellerRequest request);

}
