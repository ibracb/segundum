package segundum.infrastructure.rest.sale.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.errors.responses.ErrorResponse;
import segundum.infrastructure.rest.sale.responses.SalePartiesResponse;

/**
 * Represents the API for retrieving the parties of a sale.
 * <p>
 * Internal endpoint: it does not require authentication, is meant for
 * microservice-to-microservice communication over the service network,
 * and is blocked at the API gateway.
 * </p>
 */
@Tag(name = "Sales", description = "Sale management endpoints")
@RequestMapping("/sales")
public interface GetSalePartiesApi {

    /**
     * Fetches the parties of a sale.
     *
     * @param id the identifier of the sale
     * @return the purchaser, the seller and the current status of the sale
     */
    @Operation(summary = "Get sale parties",
            description = "Returns the parties of a sale (purchaser and seller) and its current status. "
                    + "Internal endpoint for microservice-to-microservice communication; blocked at the API gateway.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale parties retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SalePartiesResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid sale identifier",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Blocked at the API gateway: always 403, with or without token",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Hidden
    @GetMapping(value = "/{id}/parties", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SalePartiesResponse> getSaleParties(
            @Parameter(description = "The sale identifier") @PathVariable("id") String id);

}
