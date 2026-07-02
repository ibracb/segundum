package segundum.domain.repositories;

import java.util.Optional;

import segundum.domain.models.user.Email;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;

/**
 * Repository interface for managing User entities.
 */
public interface UserRepository {
	
	/**
	 * Creates a new user in the repository.
	 *
	 * @param user the user to create
	 * @return the created user
	 */
	User create(User user);
	
	/**
	 * Updates an existing user in the repository.
	 *
	 * @param user the user to update
	 * @return the updated user
	 */
	User update(User user);
	
	/**
	 * Finds a user by their unique identifier.
	 *
	 * @param id the unique identifier of the user
	 * @return an Optional containing the found user, or empty if not found
	 */
	Optional<User> findById(UserId id);
	
	/**
	 * Deletes a user from the repository by their unique identifier.
	 *
	 * @param id the unique identifier of the user to delete
	 */
	void delete(UserId id);

	/**
	 * Checks if a user exists in the repository by their email.
	 *
	 * @param email the email of the user to check
	 * @return true if a user with the given email exists, false otherwise
	 */
	boolean existsByEmail(Email email);
	
	/**
	 * Checks if a user exists in the repository by their phone number.
	 *
	 * @param phone the phone number of the user to check
	 * @return true if a user with the given phone number exists, false otherwise
	 */
	boolean existsByPhone(Phone phone);

}
