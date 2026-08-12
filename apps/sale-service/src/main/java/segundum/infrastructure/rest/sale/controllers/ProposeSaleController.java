package segundum.infrastructure.rest.sale.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import segundum.application.commands.ProposeSaleCommand;
import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SaleId;
import segundum.infrastructure.facades.ProposeSaleFacade;
import segundum.infrastructure.rest.sale.api.ProposeSaleApi;
import segundum.infrastructure.rest.sale.requests.ProposeSaleRequest;

/**
 * Represents the controller for proposing a new sale.
 */
@RestController
public class ProposeSaleController implements ProposeSaleApi {

    /**
     * The facade for proposing a sale.
     */
    private final ProposeSaleFacade facade;

    /**
     * Constructs a new ProposeSaleController with the given facade.
     *
     * @param facade the facade for proposing a sale
     */
    public ProposeSaleController(ProposeSaleFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<Void> proposeSale(ProposeSaleRequest request) {
        ProposeSaleCommand command = new ProposeSaleCommand(
                ProductId.fromString(request.getProductId()),
                PurchaserId.fromString(request.getPurchaserId()));
        SaleId saleId = facade.run(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saleId.getValue().toString())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
