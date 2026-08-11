package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.CancelSaleBySellerCommand;
import segundum.application.usecases.CancelSaleBySellerUseCase;
import segundum.domain.events.SaleCancelledBySeller;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakeEventStore;

class CancelSaleBySellerInteractorTest {

	private FakeEventStore eventStore;
	private FakePublisher publisher;
	private CancelSaleBySellerUseCase interactor;

	@BeforeEach
	void setUp() {
		eventStore = new FakeEventStore();
		publisher = new FakePublisher();
		interactor = new CancelSaleBySellerInteractor(eventStore, publisher);
	}

	@Test
	void shouldCancelSaleAndPublishEvent() {
		SaleId saleId = SaleFixture.saleId();
		SaleFixture.seedReserved(eventStore, saleId);

		interactor.execute(new CancelSaleBySellerCommand(saleId, SellerId.fromUUID(SaleFixture.SELLER)));

		assertEquals(3, eventStore.getAppendedEvents().size());
		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof SaleCancelledBySeller);
	}

	@Test
	void shouldThrowWhenSaleNotFound() {
		assertThrows(EntityNotFoundException.class, () -> interactor.execute(
				new CancelSaleBySellerCommand(SaleFixture.saleId(), SellerId.fromUUID(SaleFixture.SELLER))));
		assertTrue(publisher.getPublishedEvents().isEmpty());
	}

}
