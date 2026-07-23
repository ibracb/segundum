package segundum.domain.models.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.product.salestatus.ProductNotDraftException;
import segundum.domain.exceptions.product.salestatus.ProductNotForSaleException;
import segundum.domain.exceptions.product.shipping.ShippingAlreadyDisabledException;
import segundum.domain.exceptions.product.shipping.ShippingAlreadyEnabledException;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.seller.SellerId;

import java.util.UUID;

class ProductTest {

	private final CategoryId categoryId = CategoryId.fromString("1");
	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());

	private Product createDraftProduct() {
		return ProductFactory.create(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				sellerId);
	}

	@Test
	void shouldCreateProductWithDraftStatus() {
		Product product = createDraftProduct();
		assertNotNull(product.getProductId());
		assertEquals("iPhone 14", product.getTitle().getValue());
		assertEquals("Un iPhone en perfecto estado", product.getDescription().getValue());
		assertEquals(29.99, product.getPrice().getValue());
		assertEquals(ConditionStatus.NEW, product.getConditionStatus());
		assertEquals(SaleStatus.DRAFT, product.getSaleStatus());
		assertEquals(categoryId, product.getCategoryId());
		assertFalse(product.isShippingAvailable());
		assertNull(product.getPickupLocation());
		assertEquals(sellerId, product.getSellerId());
		assertEquals(0, product.getViews());
	}

	@Test
	void shouldPutForSale() {
		Product product = createDraftProduct();
		product.putForSale();
		assertEquals(SaleStatus.FOR_SALE, product.getSaleStatus());
	}

	@Test
	void shouldThrowWhenPutForSaleNotDraft() {
		Product product = createDraftProduct();
		product.putForSale();
		assertThrows(ProductNotDraftException.class, product::putForSale);
	}

	@Test
	void shouldTakeDown() {
		Product product = createDraftProduct();
		product.putForSale();
		product.takeDown();
		assertEquals(SaleStatus.DRAFT, product.getSaleStatus());
	}

	@Test
	void shouldThrowWhenTakeDownNotForSale() {
		Product product = createDraftProduct();
		assertThrows(ProductNotForSaleException.class, product::takeDown);
	}

	@Test
	void shouldReserve() {
		Product product = createDraftProduct();
		product.putForSale();
		product.reserve();
		assertEquals(SaleStatus.RESERVED, product.getSaleStatus());
	}

	@Test
	void shouldDiscard() {
		Product product = createDraftProduct();
		product.discard();
		assertEquals(SaleStatus.DELETED, product.getSaleStatus());
	}

	@Test
	void shouldThrowWhenDiscardNotDraft() {
		Product product = createDraftProduct();
		product.putForSale();
		assertThrows(ProductNotDraftException.class, product::discard);
	}

	@Test
	void shouldRemove() {
		Product product = createDraftProduct();
		product.putForSale();
		product.remove();
		assertEquals(SaleStatus.DELETED, product.getSaleStatus());
	}

	@Test
	void shouldThrowWhenRemoveNotForSale() {
		Product product = createDraftProduct();
		assertThrows(ProductNotForSaleException.class, product::remove);
	}

	@Test
	void shouldIncrementViews() {
		Product product = createDraftProduct();
		product.putForSale();
		product.incrementViews();
		product.incrementViews();
		assertEquals(2, product.getViews());
	}

	@Test
	void shouldThrowWhenIncrementViewsNotForSale() {
		Product product = createDraftProduct();
		assertThrows(ProductNotForSaleException.class, product::incrementViews);
	}

	@Test
	void shouldChangePrice() {
		Product product = createDraftProduct();
		product.changePrice(new Price(49.99));
		assertEquals(49.99, product.getPrice().getValue());
	}

	@Test
	void shouldThrowWhenChangePriceNotDraft() {
		Product product = createDraftProduct();
		product.putForSale();
		assertThrows(ProductNotDraftException.class, () -> product.changePrice(new Price(49.99)));
	}

	@Test
	void shouldChangeDescription() {
		Product product = createDraftProduct();
		product.changeDescription(new Description("Nueva descripción"));
		assertEquals("Nueva descripción", product.getDescription().getValue());
	}

	@Test
	void shouldChangeConditionStatus() {
		Product product = createDraftProduct();
		product.changeConditionStatus(ConditionStatus.GOOD);
		assertEquals(ConditionStatus.GOOD, product.getConditionStatus());
	}

	@Test
	void shouldAssignPickupLocation() {
		Product product = createDraftProduct();
		PickupLocation location = new PickupLocation("Local", 40.4168, -3.7038);
		product.assignPickupLocation(location);
		assertEquals(location, product.getPickupLocation());
	}

	@Test
	void shouldEnableShipping() {
		Product product = createDraftProduct();
		product.enableShipping();
		assertTrue(product.isShippingAvailable());
	}

	@Test
	void shouldThrowWhenEnableShippingAlreadyEnabled() {
		Product product = createDraftProduct();
		product.enableShipping();
		assertThrows(ShippingAlreadyEnabledException.class, product::enableShipping);
	}

	@Test
	void shouldDisableShipping() {
		Product product = createDraftProduct();
		product.enableShipping();
		product.disableShipping();
		assertFalse(product.isShippingAvailable());
	}

	@Test
	void shouldThrowWhenDisableShippingAlreadyDisabled() {
		Product product = createDraftProduct();
		assertThrows(ShippingAlreadyDisabledException.class, product::disableShipping);
	}
}
