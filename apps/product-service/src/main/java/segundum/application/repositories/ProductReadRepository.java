package segundum.application.repositories;

import java.util.Optional;

import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.readmodels.product.SellerProduct;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.seller.SellerId;

public interface ProductReadRepository {

	Optional<ProductDetail> findById(ProductId id);

	Page<ProductSummary> findByMonthAndYear(int month, int year, int pageNumber, int pageSize);

	Page<ProductSearchResult> search(CategoryId categoryId, String descriptionText,
			ConditionStatus status, Price maxPrice, int pageNumber, int pageSize);

	Page<SellerProduct> findDraftsBySeller(SellerId sellerId, int pageNumber, int pageSize);

	Page<SellerProduct> findForSaleBySeller(SellerId sellerId, int pageNumber, int pageSize);

}
