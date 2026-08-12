package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.RejectSaleCommand;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.facades.RejectSaleFacade;
import segundum.infrastructure.rest.sale.api.RejectSaleApi;
import segundum.infrastructure.rest.sale.requests.RejectSaleRequest;

/**
 * Represents the controller for rejecting a sale.
 */
@RestController
public class RejectSaleController implements RejectSaleApi {

    /**
     * The facade for rejecting a sale.
     */
    private final RejectSaleFacade facade;

    /**
     * Constructs a new RejectSaleController with the given facade.
     *
     * @param facade the facade for rejecting a sale
     */
    public RejectSaleController(RejectSaleFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<Void> rejectSale(String id, RejectSaleRequest request) {
        facade.run(new RejectSaleCommand(
                SaleId.fromString(id),
                SellerId.fromString(request.getSellerId())));
        return ResponseEntity.noContent().build();
    }

}
