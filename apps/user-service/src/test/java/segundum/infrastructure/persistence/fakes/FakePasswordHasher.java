package segundum.infrastructure.persistence.fakes;

import segundum.domain.models.user.Password;
import segundum.application.outbound.PasswordHasher;

/**
 * Fake implementation of PasswordHasher for testing purposes.
 * Returns a deterministic hash without using BCrypt.
 */
public class FakePasswordHasher implements PasswordHasher {

	private static final String HASH_PREFIX = "hashed:";

	@Override
	public String hash(Password password) {
		return HASH_PREFIX + password.getValue();
	}

	@Override
	public boolean matches(Password password, String hashedPassword) {
		return hash(password).equals(hashedPassword);
	}

}
