package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.ReserveSaleCommand;
import segundum.application.usecases.ReserveSaleUseCase;
import segundum.domain.events.SaleReserved;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakeEventStore;

class ReserveSaleInteractorTest {

	private FakeEventStore eventStore;
	private FakePublisher publisher;
	private ReserveSaleUseCase interactor;

	@BeforeEach
	void setUp() {
		eventStore = new FakeEventStore();
		publisher = new FakePublisher();
		interactor = new ReserveSaleInteractor(eventStore, publisher);
	}

	@Test
	void shouldReserveSaleAndPublishEvent() {
		SaleId saleId = SaleFixture.saleId();
		SaleFixture.seedProposed(eventStore, saleId);

		interactor.execute(new ReserveSaleCommand(saleId, SellerId.fromUUID(SaleFixture.SELLER)));

		assertEquals(2, eventStore.getAppendedEvents().size());
		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof SaleReserved);
	}

	@Test
	void shouldThrowWhenSaleNotFound() {
		assertThrows(EntityNotFoundException.class, () -> interactor.execute(
				new ReserveSaleCommand(SaleFixture.saleId(), SellerId.fromUUID(SaleFixture.SELLER))));
		assertTrue(publisher.getPublishedEvents().isEmpty());
	}

}
