using System.Text.Json.Serialization;

namespace Segundum.Dtos;

/// <summary>
/// Parties of a sale, as returned by the internal operation of the
/// sales microservice: GET /api/sales/{id}/parties.
/// </summary>
/// <param name="PurchaserId">the identifier of the purchaser of the sale</param>
/// <param name="SellerId">the identifier of the seller of the sale</param>
/// <param name="Status">the current status of the sale</param>
public record SalePartiesResponse(
    [property: JsonPropertyName("purchaserId")] string PurchaserId,
    [property: JsonPropertyName("sellerId")] string SellerId,
    [property: JsonPropertyName("status")] string Status);
