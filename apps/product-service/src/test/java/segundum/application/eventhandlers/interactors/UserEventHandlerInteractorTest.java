package segundum.application.eventhandlers.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.eventhandlers.UserEventHandler;
import segundum.application.events.users.UserDeactivated;
import segundum.application.events.users.UserRegistered;
import segundum.application.events.users.UserUpdated;
import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.infrastructure.logging.fakes.FakeLogEmitter;
import segundum.infrastructure.persistence.fakes.repositories.FakeSellerRepository;

class UserEventHandlerInteractorTest {

	private FakeSellerRepository sellerRepository;
	private FakeLogEmitter logEmitter;
	private UserEventHandler handler;

	@BeforeEach
	void setUp() {
		sellerRepository = new FakeSellerRepository();
		logEmitter = new FakeLogEmitter();
		handler = new UserEventHandlerInteractor(sellerRepository, logEmitter);
	}

	@Test
	void shouldCreateSellerFromRegisteredEvent() {
		UUID userId = UUID.randomUUID();
		UserRegistered event = new UserRegistered(userId, "Juan", "Pérez", "juan@email.com");

		handler.onUserRegistered(event);

		SellerId sellerId = SellerId.fromUUID(userId);
		assertTrue(sellerRepository.existsById(sellerId));
		assertEquals("Juan", sellerRepository.findById(sellerId).get().getName().getValue());
		assertEquals("Pérez", sellerRepository.findById(sellerId).get().getSurname().getValue());
	}

	@Test
	void shouldNotCreateSellerWhenAlreadyExists() {
		UUID userId = UUID.randomUUID();
		SellerId sellerId = SellerId.fromUUID(userId);
		Seller existing = SellerFactory.create(
				sellerId,
				new Name("Juan"),
				new Surname("Pérez"),
				new Email("juan@email.com"));
		sellerRepository.create(existing);

		UserRegistered event = new UserRegistered(userId, "Juan", "Pérez", "juan@email.com");
		handler.onUserRegistered(event);

		assertTrue(sellerRepository.findById(sellerId).isPresent());
	}

	@Test
	void shouldLogWarningOnInvalidRegisteredEvent() {
		UserRegistered event = new UserRegistered(null, null, null, null);

		handler.onUserRegistered(event);

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
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
		handler.onUserUpdated(event);

		Seller updated = sellerRepository.findById(sellerId).get();
		assertEquals("Carlos", updated.getName().getValue());
		assertEquals("García", updated.getSurname().getValue());
	}

	@Test
	void shouldNotUpdateWhenSellerNotFound() {
		UUID userId = UUID.randomUUID();
		UserUpdated event = new UserUpdated(userId, "Carlos", "García");

		handler.onUserUpdated(event);

		assertTrue(sellerRepository.findById(SellerId.fromUUID(userId)).isEmpty());
	}

	@Test
	void shouldLogWarningOnInvalidUpdatedEvent() {
		UserUpdated event = new UserUpdated(null, null, null);

		handler.onUserUpdated(event);

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}

	@Test
	void shouldDeactivateSeller() {
		UUID userId = UUID.randomUUID();
		SellerId sellerId = SellerId.fromUUID(userId);
		Seller seller = SellerFactory.create(sellerId,
				new Name("Juan"),
				new Surname("Pérez"),
				new Email("juan@email.com"));
		sellerRepository.create(seller);

		UserDeactivated event = new UserDeactivated(userId);
		handler.onUserDeactivated(event);

		assertTrue(sellerRepository.findById(sellerId).isPresent());
		assertFalse(sellerRepository.findById(sellerId).get().isActive());
	}

	@Test
	void shouldNotDeactivateWhenSellerNotFound() {
		UUID userId = UUID.randomUUID();
		UserDeactivated event = new UserDeactivated(userId);

		handler.onUserDeactivated(event);

		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}

	@Test
	void shouldLogWarningOnInvalidDeactivatedEvent() {
		UserDeactivated event = new UserDeactivated(null);

		handler.onUserDeactivated(event);

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}
}
