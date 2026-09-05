package segundum.application.notificationhandlers.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.notifications.sales.SaleCompletedNotification;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserFactory;
import segundum.domain.repositories.UserRepository;
import segundum.infrastructure.logging.fakes.FakeLogEmitter;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class SaleNotificationHandlerInteractorTest {

	private UserRepository repository;
	private FakeLogEmitter logEmitter;
	private SaleNotificationHandlerInteractor interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		logEmitter = new FakeLogEmitter();
		interactor = new SaleNotificationHandlerInteractor(repository, logEmitter);
	}

	private User register(Name name, Surname surname, Email email) {
		User user = UserFactory.create(name, surname, email, Password.plain("Abcdef123"),
				new Birthdate(LocalDate.of(1990, 5, 15)), new Phone("+34612345678"));
		return repository.create(user);
	}

	@Test
	void shouldIncrementSalesAndPurchases() {
		User seller = register(new Name("Juan"), new Surname("Pérez"), new Email("juan@email.com"));
		User purchaser = register(new Name("Ana"), new Surname("López"), new Email("ana@email.com"));

		SaleCompletedNotification event = new SaleCompletedNotification(purchaser.getUserId().getValue(), seller.getUserId().getValue());
		interactor.onSaleCompletedNotification(event);

		assertEquals(1, repository.findById(seller.getUserId()).get().getSales());
		assertEquals(1, repository.findById(purchaser.getUserId()).get().getPurchases());
		assertTrue(logEmitter.getWarnings().isEmpty());
	}

	@Test
	void shouldDiscardWhenSellerNotFound() {
		User purchaser = register(new Name("Ana"), new Surname("López"), new Email("ana@email.com"));

		SaleCompletedNotification event = new SaleCompletedNotification(purchaser.getUserId().getValue(), UUID.randomUUID());
		interactor.onSaleCompletedNotification(event);

		assertEquals(1, repository.findById(purchaser.getUserId()).get().getPurchases());
		assertEquals(1, logEmitter.getWarnings().size());
	}

	@Test
	void shouldDiscardWhenPurchaserNotFound() {
		User seller = register(new Name("Juan"), new Surname("Pérez"), new Email("juan@email.com"));

		SaleCompletedNotification event = new SaleCompletedNotification(UUID.randomUUID(), seller.getUserId().getValue());
		interactor.onSaleCompletedNotification(event);

		assertEquals(1, repository.findById(seller.getUserId()).get().getSales());
		assertEquals(1, logEmitter.getWarnings().size());
	}

	@Test
	void shouldDiscardBothWhenUsersNotFound() {
		SaleCompletedNotification event = new SaleCompletedNotification(UUID.randomUUID(), UUID.randomUUID());
		interactor.onSaleCompletedNotification(event);

		assertEquals(2, logEmitter.getWarnings().size());
	}

}
