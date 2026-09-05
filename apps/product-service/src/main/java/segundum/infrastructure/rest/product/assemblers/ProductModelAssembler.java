package segundum.infrastructure.rest.product.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import segundum.application.readmodels.product.ProductDetail;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.readmodels.product.SellerProduct;
import segundum.infrastructure.rest.product.controllers.GetProductDetailController;
import segundum.infrastructure.rest.product.responses.PickupLocationResponse;
import segundum.infrastructure.rest.product.responses.ProductDetailResponse;
import segundum.infrastructure.rest.product.responses.ProductSearchResultResponse;
import segundum.infrastructure.rest.product.responses.ProductSummaryResponse;
import segundum.infrastructure.rest.product.responses.SellerProductResponse;

/**
 * Represents the assembler that builds product HATEOAS models.
 */
@Component
public class ProductModelAssembler {

	/**
	 * Builds a model for a product detail.
	 *
	 * @param product the product detail
	 * @return the product detail model
	 */
	public EntityModel<ProductDetailResponse> toDetailModel(ProductDetail product) {
		PickupLocationResponse pickup = product.getPickupLocation() != null
				? new PickupLocationResponse(
						product.getPickupLocation().getDescription(),
						product.getPickupLocation().getLatitude(),
						product.getPickupLocation().getLongitude())
				: null;
		ProductDetailResponse response = new ProductDetailResponse(
				product.getProductId(),
				product.getTitle(),
				product.getDescription(),
				product.getPrice(),
				product.getPublicationDate().toString(),
				product.getConditionStatus(),
				product.getCategoryName(),
				product.isShippingAvailable(),
				pickup,
				product.getSellerId(),
				product.getViews());
		EntityModel<ProductDetailResponse> model = EntityModel.of(response);
		model.add(linkTo(methodOn(GetProductDetailController.class)
				.getProductDetail(product.getProductId())).withSelfRel());
		return model;
	}

	/**
	 * Builds a model for a product search result.
	 *
	 * @param result the product search result
	 * @return the product search result model
	 */
	public EntityModel<ProductSearchResultResponse> toSearchResultModel(ProductSearchResult result) {
		ProductSearchResultResponse response = new ProductSearchResultResponse(
				result.getProductId(),
				result.getTitle(),
				result.getPrice(),
				result.getConditionStatus(),
				result.getCategoryName());
		EntityModel<ProductSearchResultResponse> model = EntityModel.of(response);
		model.add(linkTo(methodOn(GetProductDetailController.class)
				.getProductDetail(result.getProductId())).withSelfRel());
		return model;
	}

	/**
	 * Builds a model for a product summary.
	 *
	 * @param summary the product summary
	 * @return the product summary model
	 */
	public EntityModel<ProductSummaryResponse> toSummaryModel(ProductSummary summary) {
		ProductSummaryResponse response = new ProductSummaryResponse(
				summary.getProductId(),
				summary.getTitle(),
				summary.getPrice(),
				summary.getPublicationDate().toString(),
				summary.getCategoryName(),
				summary.getViews());
		EntityModel<ProductSummaryResponse> model = EntityModel.of(response);
		model.add(linkTo(methodOn(GetProductDetailController.class)
				.getProductDetail(summary.getProductId())).withSelfRel());
		return model;
	}

	/**
	 * Builds a model for a seller product.
	 *
	 * @param product the seller product
	 * @return the seller product model
	 */
	public EntityModel<SellerProductResponse> toSellerProductModel(SellerProduct product) {
		SellerProductResponse response = new SellerProductResponse(
				product.getProductId(),
				product.getTitle(),
				product.getPrice(),
				product.getConditionStatus(),
				product.getPublicationDate().toString(),
				product.getCategoryName(),
				product.getViews());
		EntityModel<SellerProductResponse> model = EntityModel.of(response);
		model.add(linkTo(methodOn(GetProductDetailController.class)
				.getProductDetail(product.getProductId())).withSelfRel());
		return model;
	}

}
