package segundum.infrastructure.persistence.fakes.gateways;

import segundum.application.gateways.GetProductBasicInfo;
import segundum.application.dtos.product.ProductBasicInfo;
import segundum.domain.models.sale.ProductId;

public class FakeGetProductBasicInfo implements GetProductBasicInfo {

	private ProductBasicInfo product;

	@Override
	public ProductBasicInfo fetch(ProductId productId) {
		return product;
	}

	public void setProduct(ProductBasicInfo product) {
		this.product = product;
	}

}
