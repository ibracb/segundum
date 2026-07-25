package segundum.infrastructure.persistence.jpa.user;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import segundum.domain.models.user.Email;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.repositories.UserRepository;
import segundum.infrastructure.persistence.jpa.helpers.EntityManagerHelper;

/**
 * JPA implementation of the UserRepository interface.
 * <p>
 * This is a pure persistence adapter. Password hashing is handled
 * by the application layer (interactors) via the PasswordHasher port.
 * </p>
 */
public class JpaUserRepository implements UserRepository {

	/**
	 * Retrieves the EntityManager instance for database operations.
	 *
	 * @return the EntityManager instance
	 */
    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    @Override
    public User create(User user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(UserMapper.toEntity(user));
            em.getTransaction().commit();
            return user;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public User update(User user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(UserMapper.toEntity(user));
            em.getTransaction().commit();
            return user;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public Optional<User> findById(UserId id) {
        EntityManager em = getEntityManager();
        UserJpaEntity entity = em.find(UserJpaEntity.class, id.getValue().toString());
        return Optional.ofNullable(entity).map(UserMapper::toDomain);
    }

    @Override
	public boolean existsById(UserId id) {
    			EntityManager em = getEntityManager();
		TypedQuery<Long> query = em.createQuery(
				"SELECT COUNT(u) FROM UserJpaEntity u WHERE u.id = :id", Long.class);
		query.setParameter("id", id.getValue().toString());
		return query.getSingleResult() > 0;
	}

    @Override
    public boolean existsByEmail(Email email) {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM UserJpaEntity u WHERE u.email = :email", Long.class);
        query.setParameter("email", email.getValue());
        return query.getSingleResult() > 0;
    }

    @Override
    public boolean existsByPhone(Phone phone) {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM UserJpaEntity u WHERE u.phone = :phone", Long.class);
        query.setParameter("phone", phone.getValue());
        return query.getSingleResult() > 0;
    }
    
}
