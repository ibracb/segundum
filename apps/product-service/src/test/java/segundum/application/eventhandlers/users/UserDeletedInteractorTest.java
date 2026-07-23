package segundum.application.eventhandlers.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.eventhandlers.users.interactors.UserDeletedInteractor;
import segundum.application.events.users.UserDeleted;
import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.infrastructure.logging.fakes.FakeLogEmitter;
import segundum.infrastructure.persistence.fakes.repositories.FakeSellerRepository;

class UserDeletedInteractorTest {

	private FakeSellerRepository sellerRepository;
	private FakeLogEmitter logEmitter;
	private UserDeletedInteractor interactor;

	@BeforeEach
	void setUp() {
		sellerRepository = new FakeSellerRepository();
		logEmitter = new FakeLogEmitter();
		interactor = new UserDeletedInteractor(sellerRepository, logEmitter);
	}

	@Test
	void shouldDeleteSeller() {
		UUID userId = UUID.randomUUID();
		SellerId sellerId = SellerId.fromUUID(userId);
		Seller seller = SellerFactory.create(sellerId,
				new Name("Juan"),
				new Surname("Pérez"),
				new Email("juan@email.com"));
		sellerRepository.create(seller);

		UserDeleted event = new UserDeleted(userId);
		interactor.handle(event);

		assertTrue(sellerRepository.findById(sellerId).get().isDeleted());
	}

	@Test
	void shouldNotDeleteWhenSellerNotFound() {
		UUID userId = UUID.randomUUID();
		UserDeleted event = new UserDeleted(userId);

		interactor.handle(event);

		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}
	
	@Test
	void shouldLogWarningOnInvalidEvent() {
		UserDeleted event = new UserDeleted(null);

		interactor.handle(event);

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}
	
}
