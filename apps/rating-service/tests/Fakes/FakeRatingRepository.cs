using Segundum.Models;
using Segundum.Repositories;

namespace Segundum.Fakes;

public class FakeRatingRepository : IRatingRepository
{
    private readonly List<Rating> _ratings = new();
    private int _nextId = 1;

    public Task<string> AddAsync(Rating entity)
    {
        entity.Id ??= $"rating-{_nextId++}";
        _ratings.Add(entity);
        return Task.FromResult(entity.Id);
    }

    public Task UpdateAsync(Rating entity) => Task.CompletedTask;

    public Task DeleteAsync(Rating entity)
    {
        _ratings.Remove(entity);
        return Task.CompletedTask;
    }

    public Task<Rating?> GetByIdAsync(string id) =>
        Task.FromResult(_ratings.FirstOrDefault(r => r.Id == id));

    public Task<List<Rating>> GetAllAsync() => Task.FromResult(_ratings.ToList());

    public Task<List<string>> GetIdsAsync() =>
        Task.FromResult(_ratings.Select(r => r.Id!).ToList());

    public Task<bool> ExistsForSaleAndRaterAsync(string saleId, string raterId) =>
        Task.FromResult(_ratings.Any(r => r.SaleId == saleId && r.RaterId == raterId));

    public Task<List<Rating>> GetByRatedUserAsync(string ratedUserId, RatedUserRole role) =>
        Task.FromResult(_ratings
                .Where(r => r.RatedUserId == ratedUserId && r.RatedUserRole == role)
                .ToList());
}
