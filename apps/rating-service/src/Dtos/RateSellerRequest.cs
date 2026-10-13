namespace Segundum.Dtos;

/// <summary>
/// Request body of the registration of a rating given to a seller: the user who
/// rates is the purchaser of the sale.
/// </summary>
/// <param name="SaleId">the identifier of the sale</param>
/// <param name="PurchaserId">the identifier of the purchaser who gives the rating</param>
/// <param name="Score">the score given to the seller, between 1 and 5</param>
/// <param name="Comment">an optional comment</param>
public record RateSellerRequest(string SaleId, string PurchaserId, int Score, string? Comment);
