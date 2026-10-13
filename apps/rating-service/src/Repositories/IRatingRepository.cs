using Segundum.Models;

namespace Segundum.Repositories;

/// <summary>
/// Repository of ratings, with the queries needed by the registration and
/// consultation operations.
/// </summary>
public interface IRatingRepository : IRepository<Rating, string>
{
    /// <summary>
    /// Returns whether the given user has already rated the given sale.
    /// </summary>
    Task<bool> ExistsForSaleAndRaterAsync(string saleId, string raterId);

    /// <summary>
    /// Returns the ratings received by a user in the given role.
    /// </summary>
    Task<List<Rating>> GetByRatedUserAsync(string ratedUserId, RatedUserRole role);
}
