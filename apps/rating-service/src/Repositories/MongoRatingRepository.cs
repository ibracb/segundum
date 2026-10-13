using MongoDB.Driver;
using Segundum.Models;

namespace Segundum.Repositories;

/// <summary>
/// MongoDB implementation of the rating repository, following the repository
/// example of the course reference repository (BookleMinApi).
/// </summary>
public class MongoRatingRepository : IRatingRepository
{
    private readonly IMongoCollection<Rating> _ratings;

    public MongoRatingRepository(IMongoDatabase database, IConfiguration config)
    {
        _ratings = database.GetCollection<Rating>(config["MongoDB:Collection"] ?? "ratings");
    }

    public async Task<string> AddAsync(Rating entity)
    {
        await _ratings.InsertOneAsync(entity);
        return entity.Id!;
    }

    public async Task UpdateAsync(Rating entity)
    {
        await _ratings.ReplaceOneAsync(r => r.Id == entity.Id, entity);
    }

    public async Task DeleteAsync(Rating entity)
    {
        await _ratings.DeleteOneAsync(r => r.Id == entity.Id);
    }

    public async Task<List<Rating>> GetAllAsync() =>
        await _ratings.Find(_ => true).ToListAsync();

    public async Task<Rating?> GetByIdAsync(string id) =>
        await _ratings.Find(r => r.Id == id).FirstOrDefaultAsync();

    public async Task<List<string>> GetIdsAsync()
    {
        // IMongoCollection has no Select(), so we use Find to get the cursor
        var todas = await _ratings.Find(_ => true).ToListAsync();
        return todas.Select(r => r.Id!).ToList();
    }

    public async Task<bool> ExistsForSaleAndRaterAsync(string saleId, string raterId) =>
        await _ratings.Find(r => r.SaleId == saleId && r.RaterId == raterId)
                .FirstOrDefaultAsync() != null;

    public async Task<List<Rating>> GetByRatedUserAsync(string ratedUserId, RatedUserRole role) =>
        await _ratings.Find(r => r.RatedUserId == ratedUserId && r.RatedUserRole == role)
                .ToListAsync();

    /// <summary>
    /// Creates the indexes needed by the repository. It is invoked once on startup.
    /// </summary>
    /// <param name="client">the MongoDB client</param>
    /// <param name="databaseName">the name of the database</param>
    /// <param name="collectionName">the name of the collection</param>
    public static async Task EnsureIndexesAsync(IMongoClient client, string databaseName, string collectionName)
    {
        var ratings = client.GetDatabase(databaseName).GetCollection<Rating>(collectionName);

        // A user can only rate a sale once
        var uniqueSaleAndRater = new CreateIndexModel<Rating>(
                Builders<Rating>.IndexKeys.Ascending(r => r.SaleId).Ascending(r => r.RaterId),
                new CreateIndexOptions { Unique = true, Name = "sale_rater_unique" });

        // Ratings received by a user, grouped by role
        var byRatedUser = new CreateIndexModel<Rating>(
                Builders<Rating>.IndexKeys.Ascending(r => r.RatedUserId).Ascending(r => r.RatedUserRole));

        await ratings.Indexes.CreateManyAsync(new[] { uniqueSaleAndRater, byRatedUser });
    }
}
