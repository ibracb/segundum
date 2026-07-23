package segundum.infrastructure.persistence.fakes.repositories;

import java.util.Optional;

import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.repositories.ProductReadRepository;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.seller.SellerId;

public class FakeProductReadRepository implements ProductReadRepository {

	private Optional<ProductDetail> productDetail = Optional.empty();
	private Page<ProductSummary> monthlyHistoryResult = new Page<>(java.util.Collections.emptyList(), 0, 0, 20);
	private Page<ProductSearchResult> searchResult = new Page<>(java.util.Collections.emptyList(), 0, 0, 20);
	private Page<SellerProduct> sellerDraftsResult = new Page<>(java.util.Collections.emptyList(), 0, 0, 20);
	private Page<SellerProduct> sellerForSaleResult = new Page<>(java.util.Collections.emptyList(), 0, 0, 20);

	@Override
	public Optional<ProductDetail> findById(ProductId id) {
		return productDetail;
	}

	@Override
	public Page<ProductSummary> findByMonthAndYear(int month, int year, int pageNumber, int pageSize) {
		return monthlyHistoryResult;
	}

	@Override
	public Page<ProductSearchResult> search(CategoryId categoryId, String descriptionText,
			ConditionStatus status, Price maxPrice, int pageNumber, int pageSize) {
		return searchResult;
	}

	@Override
	public Page<SellerProduct> findDraftsBySeller(SellerId sellerId, int pageNumber, int pageSize) {
		return sellerDraftsResult;
	}

	@Override
	public Page<SellerProduct> findForSaleBySeller(SellerId sellerId, int pageNumber, int pageSize) {
		return sellerForSaleResult;
	}

	public void setProductDetail(Optional<ProductDetail> productDetail) {
		this.productDetail = productDetail;
	}

	public void setMonthlyHistoryResult(Page<ProductSummary> monthlyHistoryResult) {
		this.monthlyHistoryResult = monthlyHistoryResult;
	}

	public void setSearchResult(Page<ProductSearchResult> searchResult) {
		this.searchResult = searchResult;
	}

	public void setSellerDraftsResult(Page<SellerProduct> sellerDraftsResult) {
		this.sellerDraftsResult = sellerDraftsResult;
	}

	public void setSellerForSaleResult(Page<SellerProduct> sellerForSaleResult) {
		this.sellerForSaleResult = sellerForSaleResult;
	}
}
