package segundum.infrastructure.rest.sale.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import segundum.application.commands.ProposeSaleCommand;
import segundum.application.usecases.ProposeSaleUseCase;
import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SaleId;
import segundum.infrastructure.rest.sale.api.ProposeSaleApi;
import segundum.infrastructure.rest.sale.requests.ProposeSaleRequest;

/**
 * Represents the controller for proposing a new sale.
 */
@RestController
public class ProposeSaleController implements ProposeSaleApi {

    /**
     * The use case for proposing a sale.
     */
    private final ProposeSaleUseCase proposeSaleUseCase;

    /**
     * Constructs a new ProposeSaleController with the given use case.
     *
     * @param proposeSaleUseCase the use case for proposing a sale
     */
    public ProposeSaleController(ProposeSaleUseCase proposeSaleUseCase) {
        this.proposeSaleUseCase = proposeSaleUseCase;
    }

    @Override
    public ResponseEntity<Void> proposeSale(ProposeSaleRequest request) {
        ProposeSaleCommand command = new ProposeSaleCommand(
                ProductId.fromString(request.getProductId()),
                PurchaserId.fromString(request.getPurchaserId()));
        SaleId saleId = proposeSaleUseCase.execute(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saleId.getValue().toString())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
