package segundum.infrastructure.rest.sale.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.queries.SearchSalesQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.sale.SaleDetailReadModel;
import segundum.application.usecases.SearchSalesUseCase;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.rest.sale.api.SearchSalesApi;
import segundum.infrastructure.rest.sale.assemblers.SaleModelAssembler;
import segundum.infrastructure.rest.sale.responses.SaleDetailResponse;

/**
 * Represents the controller for searching sales with optional filters.
 */
@RestController
public class SearchSalesController implements SearchSalesApi {

    private final SearchSalesUseCase useCase;
    private final SaleModelAssembler assembler;

    /**
     * Constructs a new SearchSalesController with the given dependencies.
     *
     * @param useCase   the use case for searching sales
     * @param assembler the assembler for converting sale read models into entity models
     */
    public SearchSalesController(SearchSalesUseCase useCase, SaleModelAssembler assembler) {
        this.useCase = useCase;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<SaleDetailResponse>>> searchSales(
            String purchaserId, String sellerId, String status, Pageable pageable) {
        SearchSalesQuery query = new SearchSalesQuery(
                purchaserId != null && !purchaserId.isBlank() ? PurchaserId.fromString(purchaserId) : null,
                sellerId != null && !sellerId.isBlank() ? SellerId.fromString(sellerId) : null,
                parseStatus(status),
                pageable.getPageNumber(), pageable.getPageSize());
        Page<SaleDetailReadModel> page = useCase.execute(query);
        List<EntityModel<SaleDetailResponse>> models = page.getContent().stream()
                .map(assembler::toSaleDetailModel)
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
