package segundum.application.usecases.interactors;

import java.util.List;
import java.util.stream.Collectors;

import segundum.application.commands.AuthenticateUserCommand;
import segundum.application.readmodels.user.AuthenticatedUserReadModel;
import segundum.application.usecases.AuthenticateUserUseCase;
import segundum.application.outbound.PasswordHasher;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserRole;
import segundum.domain.repositories.UserRepository;

/**
 * Interactor for authenticating a user.
 * Verifies credentials against the repository and returns user data.
 */
public class AuthenticateUserInteractor implements AuthenticateUserUseCase {

    /**
     * The repository for managing users.
     */
    private final UserRepository userRepository;

    /**
     * The password hasher for verifying passwords.
     */
    private final PasswordHasher passwordHasher;

    /**
     * Constructs a new AuthenticateUserInteractor with the given dependencies.
     *
     * @param userRepository the repository for managing users
     * @param passwordHasher the password hasher for verifying passwords
     */
    public AuthenticateUserInteractor(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public AuthenticatedUserReadModel execute(AuthenticateUserCommand command) {
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User", "email", command.getEmail().getValue()));

        if (!user.isActive()) {
            throw new EntityNotFoundException("User", "email", command.getEmail().getValue());
        }

        Password plainPassword = Password.plain(command.getPassword());
        if (!passwordHasher.matches(plainPassword, user.getPassword().getValue())) {
            throw new segundum.domain.exceptions.user.authentication.BadCredentialsException();
        }

        String fullName = user.getName().getValue() + " " + user.getSurname().getValue();
        List<String> roles = user.getUserRoles().stream()
                .map(UserRole::name)
                .collect(Collectors.toList());

        return new AuthenticatedUserReadModel(
                user.getUserId().getValue().toString(),
                fullName,
                roles);
    }

}
