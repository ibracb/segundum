package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.RejectSaleCommand;
import segundum.application.usecases.RejectSaleUseCase;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.rest.sale.api.RejectSaleApi;
import segundum.infrastructure.rest.sale.requests.RejectSaleRequest;

/**
 * Represents the controller for rejecting a sale.
 */
@RestController
public class RejectSaleController implements RejectSaleApi {

    /**
     * The use case for rejecting a sale.
     */
    private final RejectSaleUseCase rejectSaleUseCase;

    /**
     * Constructs a new RejectSaleController with the given use case.
     *
     * @param rejectSaleUseCase the use case for rejecting a sale
     */
    public RejectSaleController(RejectSaleUseCase rejectSaleUseCase) {
        this.rejectSaleUseCase = rejectSaleUseCase;
    }

    @Override
    public ResponseEntity<Void> rejectSale(String id, RejectSaleRequest request) {
        rejectSaleUseCase.execute(new RejectSaleCommand(
                SaleId.fromString(id),
                SellerId.fromString(request.getSellerId())));
        return ResponseEntity.noContent().build();
    }

}
