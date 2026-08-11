package segundum.infrastructure.rest.sale.api;

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
import segundum.infrastructure.rest.sale.requests.ProposeSaleRequest;

/**
 * Represents the API for proposing a new sale.
 */
@Tag(name = "Sales", description = "Sale management endpoints")
@RequestMapping("/sales")
public interface ProposeSaleApi {

    /**
     * Proposes a new sale.
     *
     * @param request the propose sale request
     * @return an empty response
     */
    @Operation(summary = "Propose a new sale")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sale proposed",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Purchaser cannot be the seller",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "502", description = "External service error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> proposeSale(@Valid @RequestBody ProposeSaleRequest request);

}
