package segundum.infrastructure.rest.sale.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.queries.GetSalesByPurchaserQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.usecases.GetSalesByPurchaserUseCase;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;
import segundum.infrastructure.rest.sale.api.GetSalesByPurchaserApi;
import segundum.infrastructure.rest.sale.assemblers.SaleModelAssembler;
import segundum.infrastructure.rest.sale.responses.SaleAsPurchaserResponse;

/**
 * Represents the controller for fetching the sales of a purchaser.
 */
@RestController
public class GetSalesByPurchaserController implements GetSalesByPurchaserApi {

    /**
     * The use case for fetching the sales of a purchaser.
     */
    private final GetSalesByPurchaserUseCase useCase;

    /**
     * The assembler for converting sale read models into entity models.
     */
    private final SaleModelAssembler assembler;

    /**
     * Constructs a new GetSalesByPurchaserController with the given dependencies.
     *
     * @param useCase   the use case for fetching the sales of a purchaser
     * @param assembler the assembler for converting sale read models into entity models
     */
    public GetSalesByPurchaserController(GetSalesByPurchaserUseCase useCase, SaleModelAssembler assembler) {
        this.useCase = useCase;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<SaleAsPurchaserResponse>>> getSalesByPurchaser(
            String purchaserId, String status, Pageable pageable) {
        GetSalesByPurchaserQuery query = new GetSalesByPurchaserQuery(
                PurchaserId.fromString(purchaserId),
                parseStatus(status),
                pageable.getPageNumber(), pageable.getPageSize());
        Page<SaleAsPurchaserReadModel> page = useCase.execute(query);
        List<EntityModel<SaleAsPurchaserResponse>> models = page.getContent().stream()
                .map(assembler::toSaleAsPurchaserModel)
                .collect(Collectors.toList());
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getPageSize(), page.getPageNumber(), page.getTotalElements(), page.getTotalPages());
        return ResponseEntity.ok(PagedModel.of(models, metadata));
    }

    /**
     * Parses the given status string into an order status.
     *
     * @param status the status string
     * @return the order status, or null if the status is blank
     * @throws IllegalArgumentException if the status is not a valid order status
     */
    private OrderStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return null;
        }
        String value = status.trim();
        for (OrderStatus candidate : OrderStatus.values()) {
            if (candidate.name().equals(value)) {
                return candidate;
            }
        }
        throw new IllegalArgumentException("Invalid sale status '" + value
                + "'. Valid values: PENDING, RESERVED, REJECTED, CANCELLED, COMPLETED");
    }

}
