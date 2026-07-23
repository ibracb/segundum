package segundum.application.eventhandlers.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.eventhandlers.users.interactors.UserUpdatedInteractor;
import segundum.application.events.users.UserUpdated;
import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.infrastructure.logging.fakes.FakeLogEmitter;
import segundum.infrastructure.persistence.fakes.repositories.FakeSellerRepository;

class UserUpdatedInteractorTest {

	private FakeSellerRepository sellerRepository;
	private FakeLogEmitter logEmitter;
	private UserUpdatedInteractor interactor;

	@BeforeEach
	void setUp() {
		sellerRepository = new FakeSellerRepository();
		logEmitter = new FakeLogEmitter();
		interactor = new UserUpdatedInteractor(sellerRepository, logEmitter);
	}

	@Test
	void shouldUpdateSellerNameAndSurname() {
		UUID userId = UUID.randomUUID();
		SellerId sellerId = SellerId.fromUUID(userId);
		Seller seller = SellerFactory.create(sellerId,
				new Name("Juan"),
				new Surname("Pérez"),
				new Email("juan@email.com"));
		sellerRepository.create(seller);

		UserUpdated event = new UserUpdated(userId, "Carlos", "García");
		interactor.handle(event);

		Seller updated = sellerRepository.findById(sellerId).get();
		assertEquals("Carlos", updated.getName().getValue());
		assertEquals("García", updated.getSurname().getValue());
	}

	@Test
	void shouldNotUpdateWhenSellerNotFound() {
		UUID userId = UUID.randomUUID();
		UserUpdated event = new UserUpdated(userId, "Carlos", "García");

		interactor.handle(event);

		assertTrue(sellerRepository.findById(SellerId.fromUUID(userId)).isEmpty());
	}

	@Test
	void shouldLogWarningOnInvalidEvent() {
		UserUpdated event = new UserUpdated(null, null, null);

		interactor.handle(event);

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}
}
