package segundum.infrastructure.security;

import org.mindrot.jbcrypt.BCrypt;

import segundum.domain.models.user.Password;
import segundum.domain.outbound.PasswordHasher;

/**
 * BCrypt implementation of the PasswordHasher port.
 */
public class BCryptPasswordHasher implements PasswordHasher {

	@Override
	public String hash(Password password) {
		return BCrypt.hashpw(password.getValue(), BCrypt.gensalt());
	}

	@Override
	public boolean matches(Password password, String hashedPassword) {
		return BCrypt.checkpw(password.getValue(), hashedPassword);
	}

}
