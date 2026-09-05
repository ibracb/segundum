package segundum.application.notificationhandlers.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.notificationhandlers.UserNotificationHandler;
import segundum.application.notifications.users.UserDeactivatedNotification;
import segundum.application.notifications.users.UserRegisteredNotification;
import segundum.application.notifications.users.UserUpdatedNotification;
import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.infrastructure.logging.fakes.FakeLogEmitter;
import segundum.infrastructure.persistence.fakes.repositories.FakeSellerRepository;

class UserNotificationHandlerInteractorTest {

	private FakeSellerRepository sellerRepository;
	private FakeLogEmitter logEmitter;
	private UserNotificationHandler handler;

	@BeforeEach
	void setUp() {
		sellerRepository = new FakeSellerRepository();
		logEmitter = new FakeLogEmitter();
		handler = new UserNotificationHandlerInteractor(sellerRepository, logEmitter);
	}

	@Test
	void shouldCreateSellerFromRegisteredEvent() {
		UUID userId = UUID.randomUUID();
		UserRegisteredNotification event = new UserRegisteredNotification(userId, "Juan", "Pérez", "juan@email.com");

		handler.onUserRegisteredNotification(event);

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

		UserRegisteredNotification event = new UserRegisteredNotification(userId, "Juan", "Pérez", "juan@email.com");
		handler.onUserRegisteredNotification(event);

		assertTrue(sellerRepository.findById(sellerId).isPresent());
	}

	@Test
	void shouldLogWarningOnInvalidRegisteredEvent() {
		UserRegisteredNotification event = new UserRegisteredNotification(null, null, null, null);

		handler.onUserRegisteredNotification(event);

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

		UserUpdatedNotification event = new UserUpdatedNotification(userId, "Carlos", "García");
		handler.onUserUpdatedNotification(event);

		Seller updated = sellerRepository.findById(sellerId).get();
		assertEquals("Carlos", updated.getName().getValue());
		assertEquals("García", updated.getSurname().getValue());
	}

	@Test
	void shouldNotUpdateWhenSellerNotFound() {
		UUID userId = UUID.randomUUID();
		UserUpdatedNotification event = new UserUpdatedNotification(userId, "Carlos", "García");

		handler.onUserUpdatedNotification(event);

		assertTrue(sellerRepository.findById(SellerId.fromUUID(userId)).isEmpty());
	}

	@Test
	void shouldLogWarningOnInvalidUpdatedEvent() {
		UserUpdatedNotification event = new UserUpdatedNotification(null, null, null);

		handler.onUserUpdatedNotification(event);

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

		UserDeactivatedNotification event = new UserDeactivatedNotification(userId);
		handler.onUserDeactivatedNotification(event);

		assertTrue(sellerRepository.findById(sellerId).isPresent());
		assertFalse(sellerRepository.findById(sellerId).get().isActive());
	}

	@Test
	void shouldNotDeactivateWhenSellerNotFound() {
		UUID userId = UUID.randomUUID();
		UserDeactivatedNotification event = new UserDeactivatedNotification(userId);

		handler.onUserDeactivatedNotification(event);

		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}

	@Test
	void shouldLogWarningOnInvalidDeactivatedEvent() {
		UserDeactivatedNotification event = new UserDeactivatedNotification(null);

		handler.onUserDeactivatedNotification(event);

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}
}
