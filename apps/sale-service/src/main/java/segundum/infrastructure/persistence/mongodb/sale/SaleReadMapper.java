package segundum.infrastructure.persistence.mongodb.sale;

import segundum.application.readmodels.product.PickupLocationReadModel;
import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.application.readmodels.purchaser.PurchaserReadModel;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.readmodels.seller.SellerReadModel;

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

	private static ProductBasicInfoReadModel toProduct(SaleProductDocument product) {
		if (product == null) {
			return null;
		}
		return new ProductBasicInfoReadModel(
				product.getProductId(),
				product.getTitle(),
				product.getPrice(),
				toPickupLocation(product.getPickupLocation()),
				product.getSellerId(),
				null);
	}

	private static PickupLocationReadModel toPickupLocation(SalePickupLocationDocument pickup) {
		if (pickup == null) {
			return null;
		}
		return new PickupLocationReadModel(
				pickup.getDescription(),
				pickup.getLatitude(),
				pickup.getLongitude());
	}

	private static SellerReadModel toSeller(SaleSellerDocument seller) {
		if (seller == null) {
			return null;
		}
		return new SellerReadModel(seller.getId(), seller.getName(), seller.getSurname());
	}

	private static PurchaserReadModel toPurchaser(SalePurchaserDocument purchaser) {
		if (purchaser == null) {
			return null;
		}
		return new PurchaserReadModel(purchaser.getId(), purchaser.getName(), purchaser.getSurname());
	}

}
