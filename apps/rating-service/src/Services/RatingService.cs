using Segundum.Common;
using Segundum.Dtos;
using Segundum.Events;
using Segundum.Models;
using Segundum.Ports;
using Segundum.Repositories;

namespace Segundum.Services;

/// <summary>
/// Implements the registration and consultation of ratings.
/// <p>
/// The registration checks, in this order: that the user has not rated the sale
/// before, that the sale exists and has been completed, and that the user who
/// rates is actually a party of the sale (the purchaser when rating a seller,
/// the seller when rating a purchaser). Once all the checks have been passed,
/// the rating is persisted and the rating created event is published.
/// </p>
/// </summary>
public class RatingService : IRatingService
{
    /// <summary>
    /// The status of the sales that can be rated (OrderStatus.COMPLETED).
    /// </summary>
    private const string CompletedStatus = "COMPLETED";

    private readonly IRatingRepository _repository;
    private readonly IEventPublisher _publisher;
    private readonly ISalePartiesReader _salesClient;
    private readonly ILogger<RatingService> _logger;

    public RatingService(IRatingRepository repository, IEventPublisher publisher,
            ISalePartiesReader salesClient, ILogger<RatingService> logger)
    {
        _repository = repository;
        _publisher = publisher;
        _salesClient = salesClient;
        _logger = logger;
    }

    public async Task<Result<Rating>> RateSellerAsync(RateSellerRequest request)
    {
        Rating.Validate(request.SaleId, request.PurchaserId, request.Score);

        if (await _repository.ExistsForSaleAndRaterAsync(request.SaleId, request.PurchaserId))
            return Result<Rating>.Conflict("The purchaser has already rated this sale.");

        var sale = await CheckSaleAsync(request.SaleId, request.PurchaserId, purchaserIsTheRater: true);
        if (!sale.Success)
            return Result<Rating>.From(sale);

        var rating = Rating.Create(request.SaleId, request.PurchaserId, sale.Value!.SellerId,
                RatedUserRole.Seller, request.Score, request.Comment);

        rating.Id = await _repository.AddAsync(rating);

        await PublishAsync(rating);

        return Result<Rating>.Ok(rating);
    }

    public async Task<Result<Rating>> RatePurchaserAsync(RatePurchaserRequest request)
    {
        Rating.Validate(request.SaleId, request.SellerId, request.Score);

        if (await _repository.ExistsForSaleAndRaterAsync(request.SaleId, request.SellerId))
            return Result<Rating>.Conflict("The seller has already rated this sale.");

        var sale = await CheckSaleAsync(request.SaleId, request.SellerId, purchaserIsTheRater: false);
        if (!sale.Success)
            return Result<Rating>.From(sale);

        var rating = Rating.Create(request.SaleId, request.SellerId, sale.Value!.PurchaserId,
                RatedUserRole.Purchaser, request.Score, request.Comment);

        rating.Id = await _repository.AddAsync(rating);

        await PublishAsync(rating);

        return Result<Rating>.Ok(rating);
    }

    public Task<Rating?> GetAsync(string id) => _repository.GetByIdAsync(id);

    public async Task<List<RatingSummary>> GetSummariesAsSellerAsync(string userId)
    {
        var ratings = await _repository.GetByRatedUserAsync(userId, RatedUserRole.Seller);
        return ratings.Select(ToSummary).ToList();
    }

    public async Task<List<RatingSummary>> GetSummariesAsPurchaserAsync(string userId)
    {
        var ratings = await _repository.GetByRatedUserAsync(userId, RatedUserRole.Purchaser);
        return ratings.Select(ToSummary).ToList();
    }

    /// <summary>
    /// Retrieves the parties of the sale and checks the preconditions of the
    /// registration: the sale must be completed and the user who rates must be
    /// one of its parties. When the sale does not exist, a not found outcome is
    /// returned; when the sales service cannot be reached, the failure is
    /// propagated and handled by the global error handler.
    /// </summary>
    private async Task<Result<SalePartiesResponse>> CheckSaleAsync(string saleId, string raterId, bool purchaserIsTheRater)
    {
        var parties = await _salesClient.GetSalePartiesAsync(saleId);
        if (parties is null)
            return Result<SalePartiesResponse>.NotFound("The sale does not exist.");

        if (!string.Equals(parties.Status, CompletedStatus, StringComparison.OrdinalIgnoreCase))
            return Result<SalePartiesResponse>.Conflict("Only completed sales can be rated.");

        var raterIsAParty = purchaserIsTheRater
                ? string.Equals(parties.PurchaserId, raterId, StringComparison.Ordinal)
                : string.Equals(parties.SellerId, raterId, StringComparison.Ordinal);

        if (!raterIsAParty)
            return Result<SalePartiesResponse>.Error(purchaserIsTheRater
                    ? "The user who rates is not the purchaser of the sale."
                    : "The user who rates is not the seller of the sale.");

        return Result<SalePartiesResponse>.Ok(parties);
    }

    /// <summary>
    /// Publishes the rating created event. The event is best effort: if it
    /// cannot be published, the error is logged and the registration still
    /// succeeds, because the rating is already persisted.
    /// </summary>
    private async Task PublishAsync(Rating rating)
    {
        try
        {
            await _publisher.PublishAsync(new RatingCreated
            {
                EventId = Guid.NewGuid().ToString(),
                RatingId = rating.Id!,
                SaleId = rating.SaleId,
                RaterId = rating.RaterId,
                RatedUserId = rating.RatedUserId,
                RatedUserRole = rating.RatedUserRole,
                Score = rating.Score
            });
        }
        catch (Exception ex)
        {
            _logger.LogError(ex, "The rating created event of rating {RatingId} could not be published", rating.Id);
        }
    }

    private static RatingSummary ToSummary(Rating rating) =>
        new(rating.Id!,
            rating.SaleId,
            rating.RaterId,
            rating.Score,
            rating.Comment);
}
