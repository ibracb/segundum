using Refit;
using Segundum.Dtos;
using Segundum.Ports;

namespace Segundum.Clients;

/// <summary>
/// Refit client of the internal operation GET /api/sales/{id}/parties of the
/// sales microservice. It is the transport detail behind the outbound port
/// <see cref="ISalePartiesReader"/>.
/// </summary>
public interface ISaleApiClient
{
    [Get("/{id}/parties")]
    Task<SalePartiesResponse> GetSalePartiesAsync(string id);
}
