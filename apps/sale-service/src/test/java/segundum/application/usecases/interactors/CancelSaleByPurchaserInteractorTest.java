package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.CancelSaleByPurchaserCommand;
import segundum.application.usecases.CancelSaleByPurchaserUseCase;
import segundum.domain.events.SaleCancelledByPurchaser;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SaleId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakeEventStore;

class CancelSaleByPurchaserInteractorTest {

	private FakeEventStore eventStore;
	private FakePublisher publisher;
	private CancelSaleByPurchaserUseCase interactor;

	@BeforeEach
	void setUp() {
		eventStore = new FakeEventStore();
		publisher = new FakePublisher();
		interactor = new CancelSaleByPurchaserInteractor(eventStore, publisher);
	}

	@Test
	void shouldCancelSaleAndPublishEvent() {
		SaleId saleId = SaleFixture.saleId();
		SaleFixture.seedReserved(eventStore, saleId);

		interactor.execute(new CancelSaleByPurchaserCommand(saleId, PurchaserId.fromUUID(SaleFixture.PURCHASER)));

		assertEquals(3, eventStore.getAppendedEvents().size());
		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof SaleCancelledByPurchaser);
	}

	@Test
	void shouldThrowWhenSaleNotFound() {
		assertThrows(EntityNotFoundException.class, () -> interactor.execute(
				new CancelSaleByPurchaserCommand(SaleFixture.saleId(), PurchaserId.fromUUID(SaleFixture.PURCHASER))));
		assertTrue(publisher.getPublishedEvents().isEmpty());
	}

}
