using Segundum.Dtos;

namespace Segundum.Ports;

/// <summary>
/// Outbound port used to retrieve the parties of a sale from the sales
/// microservice.
/// </summary>
public interface ISalePartiesReader
{
    /// <summary>
    /// Retrieves the parties and the status of a sale, or null when the sale
    /// does not exist.
    /// </summary>
    Task<SalePartiesResponse?> GetSalePartiesAsync(string saleId);
}
