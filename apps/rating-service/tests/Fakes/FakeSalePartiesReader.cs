using Segundum.Dtos;
using Segundum.Ports;

namespace Segundum.Fakes;

public class FakeSalePartiesReader : ISalePartiesReader
{
    public SalePartiesResponse? Sale { get; set; }

    public Task<SalePartiesResponse?> GetSalePartiesAsync(string saleId) =>
        Task.FromResult(Sale);
}
