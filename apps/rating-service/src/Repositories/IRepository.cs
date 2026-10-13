namespace Segundum.Repositories;

/// <summary>
/// Generic repository contract, as in the course tutorial.
/// </summary>
/// <typeparam name="T">the type of the entity</typeparam>
/// <typeparam name="K">the type of the identifier</typeparam>
public interface IRepository<T, K>
{
    Task<K> AddAsync(T entity);
    Task UpdateAsync(T entity);
    Task DeleteAsync(T entity);
    Task<T?> GetByIdAsync(K id);
    Task<List<T>> GetAllAsync();
    Task<List<K>> GetIdsAsync();
}
