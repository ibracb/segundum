package segundum.infrastructure.persistence.jpa.product;

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

/**
 * Mapper between domain Product and JPA ProductJpaEntity.
 */
public class ProductMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private ProductMapper() {
	}

	/**
	 * Converts a domain Product to a JPA entity.
	 *
	 * @param product the domain product
	 * @return the JPA entity
	 */
	public static ProductJpaEntity toEntity(Product product) {
		PickupLocationEmbeddable pickupEmbeddable = null;
		if (product.getPickupLocation() != null) {
			PickupLocation pl = product.getPickupLocation();
			pickupEmbeddable = new PickupLocationEmbeddable(
					pl.getDescription(), pl.getLatitude(), pl.getLongitude());
		}

		return new ProductJpaEntity(
				product.getProductId().getValue().toString(),
				product.getTitle().getValue(),
				product.getDescription().getValue(),
				product.getPrice().getValue(),
				product.getPublicationDate().getValue(),
				product.getConditionStatus().name(),
				product.getSaleStatus().name(),
				product.getCategoryId().getValue(),
				product.isShippingAvailable(),
				product.getSellerId().getValue().toString(),
				product.getViews(),
				pickupEmbeddable);
	}

	/**
	 * Converts a JPA entity to a domain Product.
	 *
	 * @param entity the JPA entity
	 * @return the domain product
	 */
	public static Product toDomain(ProductJpaEntity entity) {
		PickupLocation pickupLocation = null;
		if (entity.getPickupLocation() != null) {
			PickupLocationEmbeddable pl = entity.getPickupLocation();
			pickupLocation = new PickupLocation(
					pl.getDescription(), pl.getLatitude(), pl.getLongitude());
		}

		return ProductFactory.reconstitute(
				ProductId.fromString(entity.getId()),
				new Title(entity.getTitle()),
				new Description(entity.getDescription()),
				new Price(entity.getPrice()),
				PublicationDate.fromInstant(entity.getPublicationDate()),
				ConditionStatus.valueOf(entity.getConditionStatus()),
				SaleStatus.valueOf(entity.getSaleStatus()),
				CategoryId.fromString(entity.getCategoryId()),
				entity.isShippingAvailable(),
				pickupLocation,
				SellerId.fromString(entity.getSellerId()),
				entity.getViews());
	}

}
