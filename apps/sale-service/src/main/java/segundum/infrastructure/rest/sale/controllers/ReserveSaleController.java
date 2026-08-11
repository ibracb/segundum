package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.ReserveSaleCommand;
import segundum.application.usecases.ReserveSaleUseCase;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.rest.sale.api.ReserveSaleApi;
import segundum.infrastructure.rest.sale.requests.ReserveSaleRequest;

/**
 * Represents the controller for reserving a sale.
 */
@RestController
public class ReserveSaleController implements ReserveSaleApi {

    /**
     * The use case for reserving a sale.
     */
    private final ReserveSaleUseCase reserveSaleUseCase;

    /**
     * Constructs a new ReserveSaleController with the given use case.
     *
     * @param reserveSaleUseCase the use case for reserving a sale
     */
    public ReserveSaleController(ReserveSaleUseCase reserveSaleUseCase) {
        this.reserveSaleUseCase = reserveSaleUseCase;
    }

    @Override
    public ResponseEntity<Void> reserveSale(String id, ReserveSaleRequest request) {
        reserveSaleUseCase.execute(new ReserveSaleCommand(
                SaleId.fromString(id),
                SellerId.fromString(request.getSellerId())));
        return ResponseEntity.noContent().build();
    }

}
