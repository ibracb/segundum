package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.CompleteSaleCommand;
import segundum.application.usecases.CompleteSaleUseCase;
import segundum.domain.events.SaleCompleted;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakeEventStore;

class CompleteSaleInteractorTest {

	private FakeEventStore eventStore;
	private FakePublisher publisher;
	private CompleteSaleUseCase interactor;

	@BeforeEach
	void setUp() {
		eventStore = new FakeEventStore();
		publisher = new FakePublisher();
		interactor = new CompleteSaleInteractor(eventStore, publisher);
	}

	@Test
	void shouldCompleteSaleAndPublishEvent() {
		SaleId saleId = SaleFixture.saleId();
		SaleFixture.seedReserved(eventStore, saleId);

		interactor.execute(new CompleteSaleCommand(saleId, SellerId.fromUUID(SaleFixture.SELLER)));

		assertEquals(3, eventStore.getAppendedEvents().size());
		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof SaleCompleted);
	}

	@Test
	void shouldThrowWhenSaleNotFound() {
		assertThrows(EntityNotFoundException.class, () -> interactor.execute(
				new CompleteSaleCommand(SaleFixture.saleId(), SellerId.fromUUID(SaleFixture.SELLER))));
		assertTrue(publisher.getPublishedEvents().isEmpty());
	}

}
