package segundum.application.eventhandlers.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.eventhandlers.users.interactors.UserRegisteredInteractor;
import segundum.application.events.users.UserRegistered;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.logging.fakes.FakeLogEmitter;
import segundum.infrastructure.persistence.fakes.repositories.FakeSellerRepository;

class UserRegisteredInteractorTest {

	private FakeSellerRepository sellerRepository;
	private FakeLogEmitter logEmitter;
	private UserRegisteredInteractor interactor;

	@BeforeEach
	void setUp() {
		sellerRepository = new FakeSellerRepository();
		logEmitter = new FakeLogEmitter();
		interactor = new UserRegisteredInteractor(sellerRepository, logEmitter);
	}

	@Test
	void shouldCreateSellerFromEvent() {
		UUID userId = UUID.randomUUID();
		UserRegistered event = new UserRegistered(userId, "Juan", "Pérez", "juan@email.com");

		interactor.handle(event);

		SellerId sellerId = SellerId.fromUUID(userId);
		assertTrue(sellerRepository.existsById(sellerId));
		assertEquals("Juan", sellerRepository.findById(sellerId).get().getName().getValue());
		assertEquals("Pérez", sellerRepository.findById(sellerId).get().getSurname().getValue());
	}

	@Test
	void shouldNotCreateSellerWhenAlreadyExists() {
		UUID userId = UUID.randomUUID();
		SellerId sellerId = SellerId.fromUUID(userId);
		segundum.domain.models.seller.Seller existing = segundum.domain.models.seller.SellerFactory.create(
				sellerId,
				new segundum.domain.models.seller.Name("Juan"),
				new segundum.domain.models.seller.Surname("Pérez"),
				new segundum.domain.models.seller.Email("juan@email.com"));
		sellerRepository.create(existing);

		UserRegistered event = new UserRegistered(userId, "Juan", "Pérez", "juan@email.com");
		interactor.handle(event);

		assertTrue(sellerRepository.findById(sellerId).isPresent());
	}

	@Test
	void shouldLogWarningOnInvalidEvent() {
		UserRegistered event = new UserRegistered(null, null, null, null);

		interactor.handle(event);

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}
}
