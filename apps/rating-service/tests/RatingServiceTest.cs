using Segundum.Common;
using Segundum.Dtos;
using Segundum.Events;
using Segundum.Fakes;
using Segundum.Models;
using Xunit;

namespace Segundum.Services;

public class RatingServiceTest
{
    private const string SaleId = "sale-1";
    private const string PurchaserId = "purchaser-1";
    private const string SellerId = "seller-1";

    private readonly FakeRatingRepository _repository = new();
    private readonly FakeEventPublisher _publisher = new();
    private readonly FakeSalePartiesReader _salesClient = new();
    private readonly FakeLogger<RatingService> _logger = new();
    private readonly IRatingService _service;

    public RatingServiceTest()
    {
        _service = new RatingService(_repository, _publisher, _salesClient, _logger);
        _salesClient.Sale = new SalePartiesResponse(PurchaserId, SellerId, "COMPLETED");
    }

    private RatingCreated SinglePublishedEvent()
    {
        Assert.Single(_publisher.Published);
        return Assert.IsType<RatingCreated>(_publisher.Published[0]);
    }

    [Fact]
    public async Task RateSeller_ShouldPersistAndPublish_WhenValid()
    {
        Result<Rating> result = await _service.RateSellerAsync(
                new RateSellerRequest(SaleId, PurchaserId, 5, "Muy buen trato"));

        Assert.True(result.Success);
        Assert.Equal(200, result.StatusCode);
        Assert.Null(result.Message);
        Assert.Equal(SaleId, result.Value!.SaleId);
        Assert.Equal(PurchaserId, result.Value.RaterId);
        Assert.Equal(SellerId, result.Value.RatedUserId);
        Assert.Equal(RatedUserRole.Seller, result.Value.RatedUserRole);
        Assert.Equal(5, result.Value.Score);
        Assert.Equal("Muy buen trato", result.Value.Comment);
        Assert.False(string.IsNullOrEmpty(result.Value.Id));

        Assert.Single(await _repository.GetAllAsync());

        RatingCreated published = SinglePublishedEvent();
        Assert.Equal("RatingCreated", published.Type);
        Assert.False(string.IsNullOrEmpty(published.EventId));
        Assert.Equal(result.Value.Id, published.RatingId);
        Assert.Equal(SaleId, published.SaleId);
        Assert.Equal(PurchaserId, published.RaterId);
        Assert.Equal(SellerId, published.RatedUserId);
        Assert.Equal(RatedUserRole.Seller, published.RatedUserRole);
        Assert.Equal(5, published.Score);
    }

    [Fact]
    public async Task RateSeller_ShouldReturnConflict_WhenPurchaserAlreadyRated()
    {
        await _service.RateSellerAsync(new RateSellerRequest(SaleId, PurchaserId, 5, null));

        Result<Rating> second = await _service.RateSellerAsync(
                new RateSellerRequest(SaleId, PurchaserId, 2, null));

        Assert.False(second.Success);
        Assert.Equal(409, second.StatusCode);
        Assert.Equal("The purchaser has already rated this sale.", second.Message);
        Assert.Single(await _repository.GetAllAsync());
        SinglePublishedEvent();
    }

    [Fact]
    public async Task RateSeller_ShouldReturnNotFound_WhenSaleDoesNotExist()
    {
        _salesClient.Sale = null;

        Result<Rating> result = await _service.RateSellerAsync(
                new RateSellerRequest(SaleId, PurchaserId, 5, null));

        Assert.False(result.Success);
        Assert.Equal(404, result.StatusCode);
        Assert.Equal("The sale does not exist.", result.Message);
        Assert.Empty(await _repository.GetAllAsync());
        Assert.Empty(_publisher.Published);
    }

    [Fact]
    public async Task RateSeller_ShouldReturnConflict_WhenSaleIsNotCompleted()
    {
        _salesClient.Sale = new SalePartiesResponse(PurchaserId, SellerId, "RESERVED");

        Result<Rating> result = await _service.RateSellerAsync(
                new RateSellerRequest(SaleId, PurchaserId, 5, null));

        Assert.False(result.Success);
        Assert.Equal(409, result.StatusCode);
        Assert.Equal("Only completed sales can be rated.", result.Message);
        Assert.Empty(await _repository.GetAllAsync());
    }

    [Fact]
    public async Task RateSeller_ShouldReturnError_WhenRaterIsNotThePurchaser()
    {
        Result<Rating> result = await _service.RateSellerAsync(
                new RateSellerRequest(SaleId, "intruder-1", 5, null));

        Assert.False(result.Success);
        Assert.Equal(400, result.StatusCode);
        Assert.Equal("The user who rates is not the purchaser of the sale.", result.Message);
        Assert.Empty(await _repository.GetAllAsync());
        Assert.Empty(_publisher.Published);
    }

    [Fact]
    public async Task RateSeller_ShouldThrowAndNotPersist_WhenScoreIsInvalid()
    {
        await Assert.ThrowsAsync<ArgumentException>(() =>
                _service.RateSellerAsync(new RateSellerRequest(SaleId, PurchaserId, 6, null)));

        Assert.Empty(await _repository.GetAllAsync());
        Assert.Empty(_publisher.Published);
    }

    [Fact]
    public async Task RateSeller_ShouldSucceedAndLog_WhenPublisherFails()
    {
        _publisher.ThrowOnPublish = true;

        Result<Rating> result = await _service.RateSellerAsync(
                new RateSellerRequest(SaleId, PurchaserId, 4, null));

        Assert.True(result.Success);
        Assert.Single(await _repository.GetAllAsync());
        Assert.Single(_logger.Errors);
        Assert.Contains("could not be published", _logger.Errors[0]);
    }

    [Fact]
    public async Task RateSeller_ShouldRejectRater_WhenTheIdDiffersOnlyByCase()
    {
        Result<Rating> result = await _service.RateSellerAsync(
                new RateSellerRequest(SaleId, PurchaserId.ToUpperInvariant(), 5, null));

        Assert.False(result.Success);
        Assert.Equal(400, result.StatusCode);
        Assert.Equal("The user who rates is not the purchaser of the sale.", result.Message);
        Assert.Empty(await _repository.GetAllAsync());
        Assert.Empty(_publisher.Published);
    }

    [Fact]
    public async Task RatePurchaser_ShouldPersistAndPublish_WhenValid()
    {
        Result<Rating> result = await _service.RatePurchaserAsync(
                new RatePurchaserRequest(SaleId, SellerId, 3, "Todo bien"));

        Assert.True(result.Success);
        Assert.Equal(200, result.StatusCode);
        Assert.Null(result.Message);
        Assert.Equal(SaleId, result.Value!.SaleId);
        Assert.Equal(SellerId, result.Value.RaterId);
        Assert.Equal(PurchaserId, result.Value.RatedUserId);
        Assert.Equal(RatedUserRole.Purchaser, result.Value.RatedUserRole);
        Assert.Equal(3, result.Value.Score);
        Assert.Equal("Todo bien", result.Value.Comment);
        Assert.False(string.IsNullOrEmpty(result.Value.Id));

        Assert.Single(await _repository.GetAllAsync());

        RatingCreated published = SinglePublishedEvent();
        Assert.Equal(result.Value.Id, published.RatingId);
        Assert.Equal(SellerId, published.RaterId);
        Assert.Equal(PurchaserId, published.RatedUserId);
        Assert.Equal(RatedUserRole.Purchaser, published.RatedUserRole);
        Assert.Equal(3, published.Score);
    }

    [Fact]
    public async Task RatePurchaser_ShouldReturnConflict_WhenSellerAlreadyRated()
    {
        await _service.RatePurchaserAsync(new RatePurchaserRequest(SaleId, SellerId, 3, null));

        Result<Rating> second = await _service.RatePurchaserAsync(
                new RatePurchaserRequest(SaleId, SellerId, 4, null));

        Assert.False(second.Success);
        Assert.Equal(409, second.StatusCode);
        Assert.Equal("The seller has already rated this sale.", second.Message);
        Assert.Single(await _repository.GetAllAsync());
        SinglePublishedEvent();
    }

    [Fact]
    public async Task RatePurchaser_ShouldReturnError_WhenRaterIsNotTheSeller()
    {
        Result<Rating> result = await _service.RatePurchaserAsync(
                new RatePurchaserRequest(SaleId, "intruder-1", 3, null));

        Assert.False(result.Success);
        Assert.Equal(400, result.StatusCode);
        Assert.Equal("The user who rates is not the seller of the sale.", result.Message);
        Assert.Empty(await _repository.GetAllAsync());
        Assert.Empty(_publisher.Published);
    }

    [Fact]
    public async Task GetSummariesAsSeller_ShouldReturnOnlyTheRatingsWhereTheUserIsTheSeller()
    {
        await _repository.AddAsync(Rating.Create(SaleId, PurchaserId, SellerId, RatedUserRole.Seller, 5, "Bien"));
        await _repository.AddAsync(Rating.Create("sale-2", PurchaserId, SellerId, RatedUserRole.Seller, 2, "Regular"));
        await _repository.AddAsync(Rating.Create("sale-3", SellerId, PurchaserId, RatedUserRole.Purchaser, 4, null));
        await _repository.AddAsync(Rating.Create("sale-4", PurchaserId, "other-seller", RatedUserRole.Seller, 1, null));

        List<RatingSummary> summaries = await _service.GetSummariesAsSellerAsync(SellerId);

        Assert.Equal(2, summaries.Count);
        Assert.Equal("rating-1", summaries[0].Id);
        Assert.Equal(SaleId, summaries[0].SaleId);
        Assert.Equal(PurchaserId, summaries[0].RaterId);
        Assert.Equal(5, summaries[0].Score);
        Assert.Equal("Bien", summaries[0].Comment);
        Assert.Equal(2, summaries[1].Score);
    }

    [Fact]
    public async Task GetSummariesAsPurchaser_ShouldReturnOnlyTheRatingsWhereTheUserIsThePurchaser()
    {
        await _repository.AddAsync(Rating.Create(SaleId, SellerId, PurchaserId, RatedUserRole.Purchaser, 4, "Ok"));
        await _repository.AddAsync(Rating.Create("sale-2", SellerId, PurchaserId, RatedUserRole.Seller, 5, null));
        await _repository.AddAsync(Rating.Create("sale-3", SellerId, PurchaserId, RatedUserRole.Purchaser, 2, null));
        await _repository.AddAsync(Rating.Create("sale-4", SellerId, "other-purchaser", RatedUserRole.Purchaser, 1, null));

        List<RatingSummary> summaries = await _service.GetSummariesAsPurchaserAsync(PurchaserId);

        Assert.Equal(2, summaries.Count);
        Assert.Equal("rating-1", summaries[0].Id);
        Assert.Equal(SaleId, summaries[0].SaleId);
        Assert.Equal(SellerId, summaries[0].RaterId);
        Assert.Equal(4, summaries[0].Score);
        Assert.Equal("Ok", summaries[0].Comment);
    }
}
