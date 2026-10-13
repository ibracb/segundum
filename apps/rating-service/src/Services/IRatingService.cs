using Segundum.Common;
using Segundum.Dtos;
using Segundum.Models;

namespace Segundum.Services;

/// <summary>
/// Use cases of the ratings context: registration and consultation of ratings.
/// </summary>
public interface IRatingService
{
    /// <summary>
    /// Rates the seller of a sale: registers the rating given by a purchaser
    /// to the seller.
    /// </summary>
    Task<Result<Rating>> RateSellerAsync(RateSellerRequest request);

    /// <summary>
    /// Rates the purchaser of a sale: registers the rating given by a seller
    /// to the purchaser.
    /// </summary>
    Task<Result<Rating>> RatePurchaserAsync(RatePurchaserRequest request);

    /// <summary>
    /// Retrieves a rating by its identifier, or null when it does not exist.
    /// </summary>
    Task<Rating?> GetAsync(string id);

    /// <summary>
    /// Returns the ratings received by a user as a seller.
    /// </summary>
    Task<List<RatingSummary>> GetSummariesAsSellerAsync(string userId);

    /// <summary>
    /// Returns the ratings received by a user as a purchaser.
    /// </summary>
    Task<List<RatingSummary>> GetSummariesAsPurchaserAsync(string userId);
}
