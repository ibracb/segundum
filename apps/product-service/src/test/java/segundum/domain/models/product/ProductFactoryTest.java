package segundum.domain.models.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import segundum.domain.models.category.CategoryId;
import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.seller.SellerId;

import java.util.UUID;

class ProductFactoryTest {

	private final CategoryId categoryId = CategoryId.fromString("1");
	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());

	@Test
	void shouldCreateNewProduct() {
		Product product = ProductFactory.create(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				sellerId);

		assertNotNull(product.getProductId());
		assertEquals(SaleStatus.DRAFT, product.getSaleStatus());
		assertEquals(0, product.getViews());
		assertNull(product.getPickupLocation());
	}

	@Test
	void shouldReconstituteProduct() {
		ProductId productId = ProductId.generate();
		PublicationDate publicationDate = PublicationDate.now();
		PickupLocation pickupLocation = new PickupLocation("Local", 40.4168, -3.7038);

		Product product = ProductFactory.reconstitute(
				productId,
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				publicationDate,
				ConditionStatus.NEW,
				SaleStatus.FOR_SALE,
				categoryId,
				true,
				pickupLocation,
				sellerId,
				10);

		assertEquals(productId, product.getProductId());
		assertEquals(SaleStatus.FOR_SALE, product.getSaleStatus());
		assertEquals(10, product.getViews());
		assertEquals(pickupLocation, product.getPickupLocation());
		assertTrue(product.isShippingAvailable());
	}
}
