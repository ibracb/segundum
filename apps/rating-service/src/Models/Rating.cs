using System.Text.Json.Serialization;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace Segundum.Models;

/// <summary>
/// The role of the user that has been rated: the purchaser or the seller of a sale.
/// </summary>
[JsonConverter(typeof(JsonStringEnumConverter))]
public enum RatedUserRole
{
    Purchaser,
    Seller
}

/// <summary>
/// Represents a rating given by a user to another user in the context of a sale.
/// </summary>
public class Rating
{
    /// <summary>
    /// The identifier of the rating, automatically assigned on persistence.
    /// </summary>
    [BsonId]
    [BsonRepresentation(BsonType.ObjectId)]
    public string? Id { get; set; }

    /// <summary>
    /// The identifier of the sale the rating belongs to.
    /// </summary>
    public string SaleId { get; set; } = string.Empty;

    /// <summary>
    /// The identifier of the user who gives the rating.
    /// </summary>
    public string RaterId { get; set; } = string.Empty;

    /// <summary>
    /// The identifier of the user being rated.
    /// </summary>
    public string RatedUserId { get; set; } = string.Empty;

    /// <summary>
    /// The role (purchaser or seller) of the user being rated.
    /// </summary>
    public RatedUserRole RatedUserRole { get; set; }

    /// <summary>
    /// The score given to the rated user, an integer between 1 and 5.
    /// </summary>
    public int Score { get; set; }

    /// <summary>
    /// An optional comment written by the user who gives the rating.
    /// </summary>
    public string? Comment { get; set; }

    /// <summary>
    /// Validates the data shared by both registration operations.
    /// </summary>
    /// <param name="saleId">the identifier of the sale</param>
    /// <param name="raterId">the identifier of the user who rates</param>
    /// <param name="score">the score given to the rated user</param>
    /// <exception cref="ArgumentException">if any of the values is not valid</exception>
    public static void Validate(string saleId, string raterId, int score)
    {
        if (string.IsNullOrWhiteSpace(saleId))
            throw new ArgumentException("The sale identifier must not be empty.", nameof(saleId));

        if (string.IsNullOrWhiteSpace(raterId))
            throw new ArgumentException("The identifier of the user who rates must not be empty.", nameof(raterId));

        if (score < 1 || score > 5)
            throw new ArgumentException($"The score must be between 1 and 5, but it was {score}.", nameof(score));
    }

    /// <summary>
    /// Creates a new rating once all the business checks have been passed.
    /// </summary>
    /// <param name="saleId">the identifier of the sale</param>
    /// <param name="raterId">the identifier of the user who rates</param>
    /// <param name="ratedUserId">the identifier of the user being rated</param>
    /// <param name="role">the role of the user being rated</param>
    /// <param name="score">the score given to the rated user</param>
    /// <param name="comment">an optional comment</param>
    /// <returns>the rating that has to be persisted</returns>
    public static Rating Create(string saleId, string raterId, string ratedUserId,
            RatedUserRole role, int score, string? comment)
    {
        Validate(saleId, raterId, score);

        return new Rating
        {
            SaleId = saleId,
            RaterId = raterId,
            RatedUserId = ratedUserId,
            RatedUserRole = role,
            Score = score,
            Comment = comment
        };
    }
}
