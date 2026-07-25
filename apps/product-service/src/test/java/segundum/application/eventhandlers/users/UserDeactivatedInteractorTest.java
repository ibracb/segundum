package segundum.application.eventhandlers.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.eventhandlers.users.interactors.UserDeactivatedInteractor;
import segundum.application.events.users.UserDeactivated;
import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.infrastructure.logging.fakes.FakeLogEmitter;
import segundum.infrastructure.persistence.fakes.repositories.FakeSellerRepository;

class UserDeactivatedInteractorTest {

	private FakeSellerRepository sellerRepository;
	private FakeLogEmitter logEmitter;
	private UserDeactivatedInteractor interactor;

	@BeforeEach
	void setUp() {
		sellerRepository = new FakeSellerRepository();
		logEmitter = new FakeLogEmitter();
		interactor = new UserDeactivatedInteractor(sellerRepository, logEmitter);
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
		interactor.handle(event);

		assertTrue(sellerRepository.findById(sellerId).isPresent());
		assertFalse(sellerRepository.findById(sellerId).get().isActive());
	}

	@Test
	void shouldNotDeactivateWhenSellerNotFound() {
		UUID userId = UUID.randomUUID();
		UserDeactivated event = new UserDeactivated(userId);

		interactor.handle(event);

		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}
	
	@Test
	void shouldLogWarningOnInvalidEvent() {
		UserDeactivated event = new UserDeactivated(null);

		interactor.handle(event);

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}
	
}
