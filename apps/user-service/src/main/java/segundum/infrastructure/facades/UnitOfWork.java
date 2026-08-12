package segundum.infrastructure.facades;

import java.util.function.Supplier;

import javax.persistence.EntityManager;

import segundum.infrastructure.persistence.jpa.helpers.EntityManagerHelper;

/**
 * Executes a unit of work within a single JPA transaction.
 * <p>
 * This is an infrastructure helper that owns the transaction boundary. It is
 * used by the facades so that all the writes performed by a single operation
 * are committed atomically.
 * </p>
 */
public final class UnitOfWork {

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private UnitOfWork() {
	}

	/**
	 * Executes the given work within a single transaction.
	 *
	 * @param work the work to execute
	 * @return the result of the work
	 */
	public static <T> T run(Supplier<T> work) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		try {
			T result = work.get();
			em.getTransaction().commit();
			return result;
		}
		catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	/**
	 * Executes the given work within a single transaction without a result.
	 *
	 * @param work the work to execute
	 */
	public static void runVoid(Runnable work) {
		run(() -> {
			work.run();
			return null;
		});
	}

}
