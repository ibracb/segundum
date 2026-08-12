package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.CompleteSaleCommand;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.facades.CompleteSaleFacade;
import segundum.infrastructure.rest.sale.api.CompleteSaleApi;
import segundum.infrastructure.rest.sale.requests.CompleteSaleRequest;

/**
 * Represents the controller for completing a sale.
 */
@RestController
public class CompleteSaleController implements CompleteSaleApi {

    /**
     * The facade for completing a sale.
     */
    private final CompleteSaleFacade facade;

    /**
     * Constructs a new CompleteSaleController with the given facade.
     *
     * @param facade the facade for completing a sale
     */
    public CompleteSaleController(CompleteSaleFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<Void> completeSale(String id, CompleteSaleRequest request) {
        facade.run(new CompleteSaleCommand(
                SaleId.fromString(id),
                SellerId.fromString(request.getSellerId())));
        return ResponseEntity.noContent().build();
    }

}
