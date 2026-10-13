package segundum.infrastructure.persistence.mongodb.sale;

import segundum.application.dtos.product.PickupLocationData;
import segundum.application.dtos.product.ProductBasicInfo;
import segundum.application.dtos.purchaser.PurchaserData;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.readmodels.sale.SaleDetailReadModel;
import segundum.application.readmodels.sale.SalePartiesReadModel;
import segundum.application.dtos.seller.SellerData;

/**
 * Maps a SaleReadDocument to the application read models.
 */
final class SaleReadMapper {

	private SaleReadMapper() {
	}

	static SaleAsPurchaserReadModel toSaleAsPurchaser(SaleReadDocument doc) {
		return new SaleAsPurchaserReadModel(
				doc.getId(),
				doc.getStatus(),
				doc.getDatetime(),
				toProduct(doc.getProduct()),
				toSeller(doc.getSeller()));
	}

	static SaleAsSellerReadModel toSaleAsSeller(SaleReadDocument doc) {
		return new SaleAsSellerReadModel(
				doc.getId(),
				doc.getStatus(),
				doc.getDatetime(),
				toProduct(doc.getProduct()),
				toPurchaser(doc.getPurchaser()));
	}

	static SaleDetailReadModel toSaleDetail(SaleReadDocument doc) {
		return new SaleDetailReadModel(
				doc.getId(),
				doc.getStatus(),
				doc.getDatetime(),
				toProduct(doc.getProduct()),
				toSeller(doc.getSeller()),
				toPurchaser(doc.getPurchaser()));
	}

	static SalePartiesReadModel toSaleParties(SaleReadDocument doc) {
		return new SalePartiesReadModel(
				doc.getId(),
				doc.getPurchaserId(),
				doc.getSellerId(),
				doc.getStatus());
	}

	private static ProductBasicInfo toProduct(SaleProductDocument product) {
		if (product == null) {
			return null;
		}
		return new ProductBasicInfo(
				product.getProductId(),
				product.getTitle(),
				product.getPrice(),
				toPickupLocation(product.getPickupLocation()),
				product.getSellerId(),
				null);
	}

	private static PickupLocationData toPickupLocation(SalePickupLocationDocument pickup) {
		if (pickup == null) {
			return null;
		}
		return new PickupLocationData(
				pickup.getDescription(),
				pickup.getLatitude(),
				pickup.getLongitude());
	}

	private static SellerData toSeller(SaleSellerDocument seller) {
		if (seller == null) {
			return null;
		}
		return new SellerData(seller.getId(), seller.getName(), seller.getSurname());
	}

	private static PurchaserData toPurchaser(SalePurchaserDocument purchaser) {
		if (purchaser == null) {
			return null;
		}
		return new PurchaserData(purchaser.getId(), purchaser.getName(), purchaser.getSurname());
	}

}
