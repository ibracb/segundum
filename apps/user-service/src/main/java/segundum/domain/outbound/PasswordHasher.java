package segundum.domain.outbound;

import segundum.domain.models.user.Password;

/**
 * Output port for password hashing operations.
 * <p>
 * Defines the contract for hashing and verifying passwords.
 * Implementations reside in the infrastructure layer.
 * </p>
 */
public interface PasswordHasher {

	/**
	 * Hashes a plain-text password.
	 *
	 * @param password the plain-text password to hash
	 * @return the hashed password
	 */
	String hash(Password password);

	/**
	 * Verifies that a plain-text password matches a previously hashed password.
	 *
	 * @param password        the plain-text password to verify
	 * @param hashedPassword the hashed password to compare against
	 * @return true if the password matches, false otherwise
	 */
	boolean matches(Password password, String hashedPassword);

}
