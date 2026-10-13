package segundum.application.notificationhandlers.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.notifications.ratings.RatedUserRole;
import segundum.application.notifications.ratings.RatingCreatedNotification;
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

class RatingNotificationHandlerInteractorTest {

	private UserRepository repository;
	private FakeLogEmitter logEmitter;
	private RatingNotificationHandlerInteractor interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		logEmitter = new FakeLogEmitter();
		interactor = new RatingNotificationHandlerInteractor(repository, logEmitter);
	}

	private User register(Name name, Surname surname, Email email) {
		User user = UserFactory.create(name, surname, email, Password.plain("Abcdef123"),
				new Birthdate(LocalDate.of(1990, 5, 15)), new Phone("+34612345678"));
		repository.create(user);
		return user;
	}

	@Test
	void shouldRegisterRatingAsPurchaser() {
		User rated = register(new Name("Ana"), new Surname("López"), new Email("ana@email.com"));

		RatingCreatedNotification event = new RatingCreatedNotification(
				rated.getUserId().getValue(), RatedUserRole.PURCHASER, 5);
		interactor.onRatingCreatedNotification(event);

		assertEquals(1, repository.findById(rated.getUserId()).get().getRatingsAsPurchaser());
		assertEquals(5.0, repository.findById(rated.getUserId()).get().getAverageRatingAsPurchaser());
		assertEquals(0, repository.findById(rated.getUserId()).get().getRatingsAsSeller());
		assertTrue(logEmitter.getWarnings().isEmpty());
	}

	@Test
	void shouldRegisterRatingAsSeller() {
		User rated = register(new Name("Juan"), new Surname("Pérez"), new Email("juan@email.com"));

		RatingCreatedNotification event = new RatingCreatedNotification(
				rated.getUserId().getValue(), RatedUserRole.SELLER, 4);
		interactor.onRatingCreatedNotification(event);

		assertEquals(1, repository.findById(rated.getUserId()).get().getRatingsAsSeller());
		assertEquals(4.0, repository.findById(rated.getUserId()).get().getAverageRatingAsSeller());
		assertEquals(0, repository.findById(rated.getUserId()).get().getRatingsAsPurchaser());
		assertTrue(logEmitter.getWarnings().isEmpty());
	}

	@Test
	void shouldAccumulateTheAverageOfTheRatings() {
		User rated = register(new Name("Ana"), new Surname("López"), new Email("ana@email.com"));

		interactor.onRatingCreatedNotification(new RatingCreatedNotification(
				rated.getUserId().getValue(), RatedUserRole.PURCHASER, 5));
		interactor.onRatingCreatedNotification(new RatingCreatedNotification(
				rated.getUserId().getValue(), RatedUserRole.PURCHASER, 4));

		assertEquals(2, repository.findById(rated.getUserId()).get().getRatingsAsPurchaser());
		assertEquals(4.5, repository.findById(rated.getUserId()).get().getAverageRatingAsPurchaser());
		assertTrue(logEmitter.getWarnings().isEmpty());
	}

	@Test
	void shouldDiscardWhenRatedUserNotFound() {
		RatingCreatedNotification event = new RatingCreatedNotification(
				UUID.randomUUID(), RatedUserRole.SELLER, 5);
		interactor.onRatingCreatedNotification(event);

		assertEquals(1, logEmitter.getWarnings().size());
	}

	@Test
	void shouldDiscardWhenScoreIsInvalid() {
		User rated = register(new Name("Juan"), new Surname("Pérez"), new Email("juan@email.com"));

		RatingCreatedNotification event = new RatingCreatedNotification(
				rated.getUserId().getValue(), RatedUserRole.SELLER, 9);
		interactor.onRatingCreatedNotification(event);

		assertEquals(0, repository.findById(rated.getUserId()).get().getRatingsAsSeller());
		assertEquals(0.0, repository.findById(rated.getUserId()).get().getAverageRatingAsSeller());
		assertEquals(1, logEmitter.getWarnings().size());
	}

}
