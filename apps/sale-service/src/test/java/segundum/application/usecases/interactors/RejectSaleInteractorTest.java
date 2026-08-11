package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RejectSaleCommand;
import segundum.application.usecases.RejectSaleUseCase;
import segundum.domain.events.SaleRejected;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakeEventStore;

class RejectSaleInteractorTest {

	private FakeEventStore eventStore;
	private FakePublisher publisher;
	private RejectSaleUseCase interactor;

	@BeforeEach
	void setUp() {
		eventStore = new FakeEventStore();
		publisher = new FakePublisher();
		interactor = new RejectSaleInteractor(eventStore, publisher);
	}

	@Test
	void shouldRejectSaleAndPublishEvent() {
		SaleId saleId = SaleFixture.saleId();
		SaleFixture.seedProposed(eventStore, saleId);

		interactor.execute(new RejectSaleCommand(saleId, SellerId.fromUUID(SaleFixture.SELLER)));

		assertEquals(2, eventStore.getAppendedEvents().size());
		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof SaleRejected);
	}

	@Test
	void shouldThrowWhenSaleNotFound() {
		assertThrows(EntityNotFoundException.class, () -> interactor.execute(
				new RejectSaleCommand(SaleFixture.saleId(), SellerId.fromUUID(SaleFixture.SELLER))));
		assertTrue(publisher.getPublishedEvents().isEmpty());
	}

}
