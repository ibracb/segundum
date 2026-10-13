package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

import segundum.application.queries.GetSalePartiesQuery;
import segundum.application.readmodels.sale.SalePartiesReadModel;
import segundum.application.usecases.GetSalePartiesUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.sale.SaleId;
import segundum.infrastructure.rest.sale.api.GetSalePartiesApi;
import segundum.infrastructure.rest.sale.responses.SalePartiesResponse;

/**
 * Represents the controller for retrieving the parties of a sale.
 * <p>
 * Internal endpoint: it does not require authentication and is blocked at
 * the API gateway, so it can only be reached from the service network. It
 * is hidden from the OpenAPI document as well, because it is not part of
 * the public API of the platform.
 * </p>
 */
@Hidden
@RestController
public class GetSalePartiesController implements GetSalePartiesApi {

    /**
     * The use case for retrieving the parties of a sale.
     */
    private final GetSalePartiesUseCase useCase;

    /**
     * Constructs a new GetSalePartiesController with the given use case.
     *
     * @param useCase the use case for retrieving the parties of a sale
     */
    public GetSalePartiesController(GetSalePartiesUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public ResponseEntity<SalePartiesResponse> getSaleParties(String id) {
        SaleId saleId = SaleId.fromString(id);
        SalePartiesReadModel parties = useCase.execute(new GetSalePartiesQuery(saleId))
                .orElseThrow(() -> new EntityNotFoundException("Sale", "ID", id));
        return ResponseEntity.ok(
                new SalePartiesResponse(parties.getPurchaserId(), parties.getSellerId(), parties.getStatus()));
    }

}
