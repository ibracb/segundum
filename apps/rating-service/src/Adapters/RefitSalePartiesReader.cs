using System.Net;
using Refit;
using Segundum.Clients;
using Segundum.Dtos;
using Segundum.Ports;

namespace Segundum.Adapters;

/// <summary>
/// Outbound adapter that implements the sale parties port with the Refit
/// client of the sales microservice. A 404 answer of the sales service is
/// translated into a null result, so the technology of the transport does not
/// leak into the application services.
/// </summary>
public class RefitSalePartiesReader : ISalePartiesReader
{
    private readonly ISaleApiClient _api;

    public RefitSalePartiesReader(ISaleApiClient api)
    {
        _api = api;
    }

    public async Task<SalePartiesResponse?> GetSalePartiesAsync(string saleId)
    {
        try
        {
            return await _api.GetSalePartiesAsync(saleId);
        }
        catch (ApiException ex) when (ex.StatusCode == HttpStatusCode.NotFound)
        {
            return null;
        }
    }
}
