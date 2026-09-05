package segundum.infrastructure.persistence.fakes.finders;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import segundum.application.readmodels.user.UserInfoReadModel;
import segundum.application.readmodels.user.UserNameReadModel;
import segundum.application.readmodels.user.UserProfileReadModel;
import segundum.application.readmodels.user.UserStatsReadModel;
import segundum.application.finders.UserFinder;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

/**
 * Fake implementation of UserFinder for testing purposes.
 * <p>
 * Wraps a FakeUserRepository and projects domain entities to read models.
 * </p>
 */
public class FakeUserFinder implements UserFinder {

	private final FakeUserRepository userRepository;

	/**
	 * Constructs a new FakeUserFinder backed by the given user repository.
	 *
	 * @param userRepository the fake user repository to read from
	 */
	public FakeUserFinder(FakeUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<UserNameReadModel> findNameById(UserId id) {
		return userRepository.findById(id).map(this::toNameReadModel);
	}

	@Override
	public Optional<UserProfileReadModel> findProfileById(UserId id) {
		return userRepository.findById(id).map(this::toProfileReadModel);
	}

	@Override
	public Optional<UserStatsReadModel> findStatsById(UserId id) {
		return userRepository.findById(id).map(this::toStatsReadModel);
	}
	
	@Override
	public List<UserInfoReadModel> findAllUserInfo() {
		return userRepository.getAll().stream()
				.map(this::toInfoReadModel)
				.sorted((a, b) -> b.getRegistrationDate().compareTo(a.getRegistrationDate()))
				.collect(Collectors.toList());
	}

	private UserNameReadModel toNameReadModel(User user) {
		return new UserNameReadModel(
				user.getUserId().getValue().toString(),
				user.getName().getValue(),
				user.getSurname().getValue()
		);
	}

	private UserProfileReadModel toProfileReadModel(User user) {
		return new UserProfileReadModel(
				user.getUserId().getValue().toString(),
				user.getName().getValue(),
				user.getSurname().getValue(),
				user.getEmail().getValue(),
				user.getPhone().getValue()
		);
	}

	private UserStatsReadModel toStatsReadModel(User user) {
		return new UserStatsReadModel(
				user.getUserId().getValue().toString(),
				user.getPurchases(),
				user.getSales()
		);
	}
	
	private UserInfoReadModel toInfoReadModel(User user) {
		return new UserInfoReadModel(
				user.getUserId().getValue().toString(),
				user.getName().getValue(),
				user.getSurname().getValue(),
				user.getEmail().getValue(),
				user.getBirthdate().getValue(),
				user.getPhone().getValue(),
				user.getStatus().name(),
				user.getRegistrationDate().getValue(),
				user.getUserRoles().stream().map(role -> role.name()).collect(Collectors.toList())
		);
	}

}
