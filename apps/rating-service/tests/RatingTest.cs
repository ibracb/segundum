using Xunit;

namespace Segundum.Models;

public class RatingTest
{
    [Fact]
    public void Validate_ShouldThrow_WhenSaleIdIsEmpty()
    {
        var exception = Assert.Throws<ArgumentException>(() => Rating.Validate("  ", "user-1", 3));

        Assert.Equal("saleId", exception.ParamName);
    }

    [Fact]
    public void Validate_ShouldThrow_WhenRaterIdIsEmpty()
    {
        var exception = Assert.Throws<ArgumentException>(() => Rating.Validate("sale-1", "", 3));

        Assert.Equal("raterId", exception.ParamName);
    }

    [Theory]
    [InlineData(0)]
    [InlineData(6)]
    [InlineData(-1)]
    public void Validate_ShouldThrow_WhenScoreIsOutOfRange(int score)
    {
        var exception = Assert.Throws<ArgumentException>(() => Rating.Validate("sale-1", "user-1", score));

        Assert.Equal("score", exception.ParamName);
    }

    [Theory]
    [InlineData(1)]
    [InlineData(5)]
    public void Validate_ShouldAccept_WhenScoreIsAtTheBoundaries(int score)
    {
        Rating.Validate("sale-1", "user-1", score);
    }

    [Fact]
    public void Create_ShouldMapAllFields()
    {
        Rating rating = Rating.Create("sale-1", "purchaser-1", "seller-1",
                RatedUserRole.Seller, 4, "Excelente trato");

        Assert.Null(rating.Id);
        Assert.Equal("sale-1", rating.SaleId);
        Assert.Equal("purchaser-1", rating.RaterId);
        Assert.Equal("seller-1", rating.RatedUserId);
        Assert.Equal(RatedUserRole.Seller, rating.RatedUserRole);
        Assert.Equal(4, rating.Score);
        Assert.Equal("Excelente trato", rating.Comment);
    }

    [Fact]
    public void Create_ShouldThrow_WhenSaleIdIsEmpty()
    {
        Assert.Throws<ArgumentException>(() => Rating.Create("", "purchaser-1", "seller-1",
                RatedUserRole.Seller, 3, null));
    }
}
