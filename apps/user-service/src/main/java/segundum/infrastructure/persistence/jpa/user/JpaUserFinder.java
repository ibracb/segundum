package segundum.infrastructure.persistence.jpa.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import segundum.application.readmodels.user.UserProfileReadModel;
import segundum.application.readmodels.user.UserStatsReadModel;
import segundum.application.readmodels.user.UserInfoReadModel;
import segundum.application.readmodels.user.UserNameReadModel;
import segundum.application.finders.UserFinder;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.persistence.jpa.helpers.EntityManagerHelper;

/**
 * JPA implementation of the UserFinder interface.
 * <p>
 * Uses JPQL SELECT with named aliases and Tuple mapping to project
 * directly to read models, loading only the fields needed for each query.
 * </p>
 */
public class JpaUserFinder implements UserFinder {

	/**
	 * Retrieves the EntityManager instance for database operations.
	 *
	 * @return the EntityManager instance
	 */
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@Override
	public Optional<UserNameReadModel> findNameById(UserId id) {
		EntityManager em = getEntityManager();
		TypedQuery<UserNameReadModel> query = em.createQuery(
				"SELECT new segundum.application.readmodels.user.UserNameReadModel(" +
						"u.id, u.name, u.surname) " +
						"FROM UserJpaEntity u WHERE u.id = :id",
				UserNameReadModel.class);
		query.setParameter("id", id.getValue().toString());
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Optional<UserProfileReadModel> findProfileById(UserId id) {
		EntityManager em = getEntityManager();
		TypedQuery<UserProfileReadModel> query = em.createQuery(
				"SELECT new segundum.application.readmodels.user.UserProfileReadModel(" +
						"u.id, u.name, u.surname, u.email, u.phone) " +
						"FROM UserJpaEntity u WHERE u.id = :id",
				UserProfileReadModel.class);
		query.setParameter("id", id.getValue().toString());
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Optional<UserStatsReadModel> findStatsById(UserId id) {
		EntityManager em = getEntityManager();
		TypedQuery<UserStatsReadModel> query = em.createQuery(
				"SELECT new segundum.application.readmodels.user.UserStatsReadModel(" +
						"u.id, u.purchases, u.sales) " +
						"FROM UserJpaEntity u WHERE u.id = :id",
				UserStatsReadModel.class);
		query.setParameter("id", id.getValue().toString());
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<UserInfoReadModel> findAllUserInfo() {
		EntityManager em = getEntityManager();
		TypedQuery<UserJpaEntity> query = em.createQuery(
				"SELECT u FROM UserJpaEntity u ORDER BY u.registrationDate DESC",
				UserJpaEntity.class);
		return query.getResultList().stream()
				.map(entity -> new UserInfoReadModel(
						entity.getId(),
						entity.getName(),
						entity.getSurname(),
						entity.getEmail(),
						entity.getBirthdate(),
						entity.getPhone(),
						entity.getStatus(),
						entity.getRegistrationDate().toInstant(),
						entity.getRoles().stream()
								.map(role -> role.name())
								.collect(Collectors.toList())))
				.collect(Collectors.toList());
	}

}
