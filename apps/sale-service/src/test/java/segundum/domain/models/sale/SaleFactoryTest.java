package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleProposed;
import segundum.domain.events.SaleReserved;

class SaleFactoryTest {

	private static final UUID SELLER = UUID.fromString("11111111-1111-1111-1111-111111111111");
	private static final UUID PURCHASER = UUID.fromString("22222222-2222-2222-2222-222222222222");

	private SaleProposed buildProposal() {
		return new SaleProposed(
				SaleId.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
				ProductId.fromUUID(UUID.fromString("44444444-4444-4444-4444-444444444444")),
				SellerId.fromUUID(SELLER),
				new SellerName("Juan"), new SellerSurname("Pérez"),
				PurchaserId.fromUUID(PURCHASER),
				new PurchaserName("Ana"), new PurchaserSurname("López"),
				new Price(100), new Title("iPhone 13"), null, DateTime.now());
	}

	@Test
	void shouldCreateSaleWithProposedEvent() {
		Sale sale = SaleFactory.create(
				SaleId.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
				ProductId.fromUUID(UUID.fromString("44444444-4444-4444-4444-444444444444")),
				SellerId.fromUUID(SELLER),
				new SellerName("Juan"), new SellerSurname("Pérez"),
				PurchaserId.fromUUID(PURCHASER),
				new PurchaserName("Ana"), new PurchaserSurname("López"),
				new Price(100), new Title("iPhone 13"), null, DateTime.now());

		assertEquals(OrderStatus.PENDING, sale.getOrderStatus());
		List<DomainEvent> events = sale.getUncommittedEvents();
		assertEquals(1, events.size());
		assertTrue(events.get(0) instanceof SaleProposed);
	}

	@Test
	void shouldLoadFromHistory() {
		List<DomainEvent> history = List.of(buildProposal(), new SaleReserved(
				SaleId.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
				ProductId.fromUUID(UUID.fromString("44444444-4444-4444-4444-444444444444"))));

		Sale sale = SaleFactory.loadFromHistory(history);

		assertEquals(OrderStatus.RESERVED, sale.getOrderStatus());
		assertEquals("Juan", sale.getSellerName().getValue());
		assertEquals("Ana", sale.getPurchaserName().getValue());
		assertEquals("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa", sale.getSaleId().asString());
		assertTrue(sale.getUncommittedEvents().isEmpty());
	}

	@Test
	void shouldLoadFromEmptyHistory() {
		Sale sale = SaleFactory.loadFromHistory(List.of());
		assertNull(sale.getOrderStatus());
	}

}
