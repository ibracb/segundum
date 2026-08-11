package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.CompleteSaleCommand;
import segundum.application.usecases.CompleteSaleUseCase;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.rest.sale.api.CompleteSaleApi;
import segundum.infrastructure.rest.sale.requests.CompleteSaleRequest;

/**
 * Represents the controller for completing a sale.
 */
@RestController
public class CompleteSaleController implements CompleteSaleApi {

    /**
     * The use case for completing a sale.
     */
    private final CompleteSaleUseCase completeSaleUseCase;

    /**
     * Constructs a new CompleteSaleController with the given use case.
     *
     * @param completeSaleUseCase the use case for completing a sale
     */
    public CompleteSaleController(CompleteSaleUseCase completeSaleUseCase) {
        this.completeSaleUseCase = completeSaleUseCase;
    }

    @Override
    public ResponseEntity<Void> completeSale(String id, CompleteSaleRequest request) {
        completeSaleUseCase.execute(new CompleteSaleCommand(
                SaleId.fromString(id),
                SellerId.fromString(request.getSellerId())));
        return ResponseEntity.noContent().build();
    }

}
