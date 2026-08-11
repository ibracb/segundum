package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleCancelledByPurchaser;
import segundum.domain.events.SaleCancelledBySeller;
import segundum.domain.events.SaleCompleted;
import segundum.domain.events.SaleProposed;
import segundum.domain.events.SaleRejected;
import segundum.domain.events.SaleReserved;
import segundum.domain.exceptions.sale.creation.PurchaserCannotBeSellerException;
import segundum.domain.exceptions.sale.status.SaleNotOwnedByPurchaserException;
import segundum.domain.exceptions.sale.status.SaleNotOwnedBySellerException;
import segundum.domain.exceptions.sale.status.SaleNotPendingException;
import segundum.domain.exceptions.sale.status.SaleNotReservedException;

class SaleTest {

	private static final UUID SELLER = UUID.fromString("11111111-1111-1111-1111-111111111111");
	private static final UUID PURCHASER = UUID.fromString("22222222-2222-2222-2222-222222222222");
	private static final UUID OTHER = UUID.fromString("33333333-3333-3333-3333-333333333333");

	private Sale proposedSale() {
		return SaleFactory.create(
				SaleId.generate(),
				ProductId.fromUUID(UUID.fromString("44444444-4444-4444-4444-444444444444")),
				SellerId.fromUUID(SELLER),
				new SellerName("Juan"), new SellerSurname("Pérez"),
				PurchaserId.fromUUID(PURCHASER),
				new PurchaserName("Ana"), new PurchaserSurname("López"),
				new Price(100), new Title("iPhone 13"), null, DateTime.now());
	}

	@Test
	void shouldTransitionFromProposedToReservedToCompleted() {
		Sale sale = proposedSale();
		assertEquals(OrderStatus.PENDING, sale.getOrderStatus());
		sale.reserve(SellerId.fromUUID(SELLER));
		assertEquals(OrderStatus.RESERVED, sale.getOrderStatus());
		sale.complete(SellerId.fromUUID(SELLER));
		assertEquals(OrderStatus.COMPLETED, sale.getOrderStatus());
	}

	@Test
	void shouldTransitionFromProposedToRejected() {
		Sale sale = proposedSale();
		sale.reject(SellerId.fromUUID(SELLER));
		assertEquals(OrderStatus.REJECTED, sale.getOrderStatus());
	}

	@Test
	void shouldCancelBySellerFromReserved() {
		Sale sale = proposedSale();
		sale.reserve(SellerId.fromUUID(SELLER));
		sale.cancelBySeller(SellerId.fromUUID(SELLER));
		assertEquals(OrderStatus.CANCELLED, sale.getOrderStatus());
	}

	@Test
	void shouldCancelByPurchaserFromReserved() {
		Sale sale = proposedSale();
		sale.reserve(SellerId.fromUUID(SELLER));
		sale.cancelByPurchaser(PurchaserId.fromUUID(PURCHASER));
		assertEquals(OrderStatus.CANCELLED, sale.getOrderStatus());
	}

	@Test
	void shouldThrowWhenPurchaserIsSeller() {
		assertThrows(PurchaserCannotBeSellerException.class, () -> SaleFactory.create(
				SaleId.generate(),
				ProductId.fromUUID(UUID.fromString("44444444-4444-4444-4444-444444444444")),
				SellerId.fromUUID(SELLER),
				new SellerName("Juan"), new SellerSurname("Pérez"),
				PurchaserId.fromUUID(SELLER),
				new PurchaserName("Juan"), new PurchaserSurname("Pérez"),
				new Price(100), new Title("iPhone 13"), null, DateTime.now()));
	}

	@Test
	void shouldThrowWhenReservingNonPendingSale() {
		Sale sale = proposedSale();
		sale.reserve(SellerId.fromUUID(SELLER));
		assertThrows(SaleNotPendingException.class, () -> sale.reserve(SellerId.fromUUID(SELLER)));
	}

	@Test
	void shouldThrowWhenRejectingNonPendingSale() {
		Sale sale = proposedSale();
		sale.reserve(SellerId.fromUUID(SELLER));
		assertThrows(SaleNotPendingException.class, () -> sale.reject(SellerId.fromUUID(SELLER)));
	}

	@Test
	void shouldThrowWhenCompletingNonReservedSale() {
		Sale sale = proposedSale();
		assertThrows(SaleNotReservedException.class, () -> sale.complete(SellerId.fromUUID(SELLER)));
	}

	@Test
	void shouldThrowWhenCancellingNonReservedSale() {
		Sale sale = proposedSale();
		assertThrows(SaleNotReservedException.class, () -> sale.cancelBySeller(SellerId.fromUUID(SELLER)));
		assertThrows(SaleNotReservedException.class, () -> sale.cancelByPurchaser(PurchaserId.fromUUID(PURCHASER)));
	}

	@Test
	void shouldThrowWhenReservedByNonOwnerSeller() {
		Sale sale = proposedSale();
		assertThrows(SaleNotOwnedBySellerException.class, () -> sale.reserve(SellerId.fromUUID(OTHER)));
	}

	@Test
	void shouldThrowWhenCancelledByNonOwnerSeller() {
		Sale sale = proposedSale();
		sale.reserve(SellerId.fromUUID(SELLER));
		assertThrows(SaleNotOwnedBySellerException.class, () -> sale.cancelBySeller(SellerId.fromUUID(OTHER)));
	}

	@Test
	void shouldThrowWhenCancelledByNonOwnerPurchaser() {
		Sale sale = proposedSale();
		sale.reserve(SellerId.fromUUID(SELLER));
		assertThrows(SaleNotOwnedByPurchaserException.class,
				() -> sale.cancelByPurchaser(PurchaserId.fromUUID(OTHER)));
	}

	@Test
	void shouldEmitCorrespondingEventsOnTransition() {
		Sale sale = proposedSale();
		List<DomainEvent> proposedEvents = sale.getUncommittedEvents();
		assertEquals(1, proposedEvents.size());
		assertTrue(proposedEvents.get(0) instanceof SaleProposed);

		sale.reserve(SellerId.fromUUID(SELLER));
		List<DomainEvent> reservedEvents = sale.getUncommittedEvents();
		assertEquals(1, reservedEvents.size());
		assertTrue(reservedEvents.get(0) instanceof SaleReserved);

		sale.complete(SellerId.fromUUID(SELLER));
		assertTrue(sale.getUncommittedEvents().get(0) instanceof SaleCompleted);
	}

	@Test
	void shouldEmitRejectedEvent() {
		Sale sale = proposedSale();
		sale.getUncommittedEvents();
		sale.reject(SellerId.fromUUID(SELLER));
		assertTrue(sale.getUncommittedEvents().get(0) instanceof SaleRejected);
	}

	@Test
	void shouldEmitCancelledEvents() {
		Sale bySeller = proposedSale();
		bySeller.getUncommittedEvents();
		bySeller.reserve(SellerId.fromUUID(SELLER));
		bySeller.getUncommittedEvents();
		bySeller.cancelBySeller(SellerId.fromUUID(SELLER));
		assertTrue(bySeller.getUncommittedEvents().get(0) instanceof SaleCancelledBySeller);

		Sale byPurchaser = proposedSale();
		byPurchaser.getUncommittedEvents();
		byPurchaser.reserve(SellerId.fromUUID(SELLER));
		byPurchaser.getUncommittedEvents();
		byPurchaser.cancelByPurchaser(PurchaserId.fromUUID(PURCHASER));
		assertTrue(byPurchaser.getUncommittedEvents().get(0) instanceof SaleCancelledByPurchaser);
	}

	@Test
	void shouldClearUncommittedEventsAfterReading() {
		Sale sale = proposedSale();
		sale.getUncommittedEvents();
		assertTrue(sale.getUncommittedEvents().isEmpty());
	}

}
