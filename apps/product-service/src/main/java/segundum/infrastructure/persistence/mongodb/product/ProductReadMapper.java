package segundum.infrastructure.persistence.mongodb.product;

import segundum.application.readmodels.product.PickupLocationReadModel;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.readmodels.product.SellerProduct;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductFactory;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.product.PublicationDate;
import segundum.domain.models.product.SaleStatus;
import segundum.domain.models.product.Title;
import segundum.domain.models.seller.SellerId;

public class ProductReadMapper {

	private ProductReadMapper() {
	}

	public static Product toDomain(ProductReadDocument doc) {
		PickupLocation pickupLocation = null;
		if (doc.getPickupLocation() != null) {
			PickupLocationDocument pld = doc.getPickupLocation();
			pickupLocation = new PickupLocation(pld.getDescription(), pld.getLatitude(), pld.getLongitude());
		}

		return ProductFactory.reconstitute(
				ProductId.fromString(doc.getProductId()),
				new Title(doc.getTitle()),
				new Description(doc.getDescription()),
				new Price(doc.getPrice()),
				PublicationDate.fromInstant(doc.getPublicationDate()),
				ConditionStatus.valueOf(doc.getConditionStatus()),
				SaleStatus.valueOf(doc.getSaleStatus()),
				CategoryId.fromString(doc.getCategoryId()),
				doc.isShippingAvailable(),
				pickupLocation,
				SellerId.fromString(doc.getSellerId()),
				doc.getViews());
	}

	public static ProductSummary toSummary(ProductReadDocument doc) {
		return new ProductSummary(
				doc.getProductId(),
				doc.getTitle(),
				doc.getPrice(),
				doc.getPublicationDate(),
				doc.getCategoryName(),
				doc.getViews());
	}

	public static ProductSearchResult toSearchResult(ProductReadDocument doc) {
		return new ProductSearchResult(
				doc.getProductId(),
				doc.getTitle(),
				doc.getPrice(),
				doc.getConditionStatus(),
				doc.getCategoryName());
	}

	public static ProductDetail toDetail(ProductReadDocument doc) {
		PickupLocationReadModel pickupLocation = null;
		if (doc.getPickupLocation() != null) {
			PickupLocationDocument pld = doc.getPickupLocation();
			pickupLocation = new PickupLocationReadModel(pld.getDescription(), pld.getLatitude(), pld.getLongitude());
		}

		return new ProductDetail(
				doc.getProductId(),
				doc.getTitle(),
				doc.getDescription(),
				doc.getPrice(),
				doc.getPublicationDate(),
				doc.getConditionStatus(),
				doc.getCategoryName(),
				doc.isShippingAvailable(),
				pickupLocation,
				doc.getSellerId(),
				doc.getViews());
	}

	public static SellerProduct toSellerProduct(ProductReadDocument doc) {
		return new SellerProduct(
				doc.getProductId(),
				doc.getTitle(),
				doc.getPrice(),
				doc.getConditionStatus(),
				doc.getPublicationDate(),
				doc.getCategoryName(),
				doc.getViews());
	}

}
