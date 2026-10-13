namespace Segundum.Dtos;

/// <summary>
/// Request body of the registration of a rating given to a purchaser: the user
/// who rates is the seller of the sale.
/// </summary>
/// <param name="SaleId">the identifier of the sale</param>
/// <param name="SellerId">the identifier of the seller who gives the rating</param>
/// <param name="Score">the score given to the purchaser, between 1 and 5</param>
/// <param name="Comment">an optional comment</param>
public record RatePurchaserRequest(string SaleId, string SellerId, int Score, string? Comment);
