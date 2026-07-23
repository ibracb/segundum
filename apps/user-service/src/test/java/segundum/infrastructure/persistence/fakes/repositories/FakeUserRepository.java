package segundum.infrastructure.persistence.fakes.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import segundum.domain.models.user.Email;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.repositories.UserRepository;

/**
 * Fake implementation of UserRepository for testing purposes.
 */
public class FakeUserRepository implements UserRepository {

	private final Map<UserId, User> users = new HashMap<>();

	@Override
	public User create(User user) {
		users.put(user.getUserId(), user);
		return user;
	}

	@Override
	public User update(User user) {
		users.put(user.getUserId(), user);
		return user;
	}

	@Override
	public Optional<User> findById(UserId id) {
		return Optional.ofNullable(users.get(id));
	}

	@Override
	public void delete(UserId id) {
		User user = users.get(id);
		if (user != null) {
			user.delete();
		}
	}
	
	@Override
	public boolean existsById(UserId id) {
		User user = users.get(id);
		return user != null && !user.isDeleted();
	}

	@Override
	public boolean existsByEmail(Email email) {
		return users.values().stream()
				.anyMatch(u -> !u.isDeleted() && u.getEmail().getValue().equals(email.getValue()));
	}

	@Override
	public boolean existsByPhone(Phone phone) {
		return users.values().stream()
				.anyMatch(u -> u.getPhone().getValue().equals(phone.getValue()));
	}

}
