package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.ReserveSaleCommand;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.facades.ReserveSaleFacade;
import segundum.infrastructure.rest.sale.api.ReserveSaleApi;
import segundum.infrastructure.rest.sale.requests.ReserveSaleRequest;

/**
 * Represents the controller for reserving a sale.
 */
@RestController
public class ReserveSaleController implements ReserveSaleApi {

    /**
     * The facade for reserving a sale.
     */
    private final ReserveSaleFacade facade;

    /**
     * Constructs a new ReserveSaleController with the given facade.
     *
     * @param facade the facade for reserving a sale
     */
    public ReserveSaleController(ReserveSaleFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<Void> reserveSale(String id, ReserveSaleRequest request) {
        facade.run(new ReserveSaleCommand(
                SaleId.fromString(id),
                SellerId.fromString(request.getSellerId())));
        return ResponseEntity.noContent().build();
    }

}
