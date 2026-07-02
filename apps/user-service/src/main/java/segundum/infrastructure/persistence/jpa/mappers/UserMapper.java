package segundum.infrastructure.persistence.jpa.mappers;

import org.mindrot.jbcrypt.BCrypt;

import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserFactory;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.persistence.jpa.entities.UserJpaEntity;

/**
 * Mapper class for converting between User domain objects and UserJpaEntity objects.
 */
public class UserMapper {
	
	/**
	 * Private constructor to prevent instantiation of the UserMapper class.
	 */
    private UserMapper() {
    }
    
    /**
	 * Converts a User domain object to a UserJpaEntity for persistence.
	 *
	 * @param user the User domain object to convert
	 * @return the corresponding UserJpaEntity
	 */
    public static UserJpaEntity toEntity(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword().getValue(), BCrypt.gensalt());
        return new UserJpaEntity(
                user.getUserId().getValue().toString(),
                user.getName().getValue(),
                user.getSurname().getValue(),
                user.getEmail().getValue(),
                hashedPassword,
                user.getBirthdate().getValue(),
                user.getPhone().getValue(),
                user.getPurchases(),
                user.getSales()
        );
    }
    
    /**
     * Converts a UserJpaEntity to a User domain object.
     * @param entity the UserJpaEntity to convert
     * @return the corresponding User domain object
     */
    public static User toDomain(UserJpaEntity entity) {
        return UserFactory.reconstitute(
                UserId.fromString(entity.getId()),
                new Name(entity.getName()),
                new Surname(entity.getSurname()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                new Birthdate(entity.getBirthdate()),
                new Phone(entity.getPhone()),
                entity.getPurchases(),
                entity.getSales()
        );
    }
    
}
