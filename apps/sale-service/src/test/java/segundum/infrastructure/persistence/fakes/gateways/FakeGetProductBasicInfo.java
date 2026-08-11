package segundum.infrastructure.persistence.fakes.gateways;

import segundum.application.gateways.GetProductBasicInfo;
import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.domain.models.sale.ProductId;

public class FakeGetProductBasicInfo implements GetProductBasicInfo {

	private ProductBasicInfoReadModel product;

	@Override
	public ProductBasicInfoReadModel fetch(ProductId productId) {
		return product;
	}

	public void setProduct(ProductBasicInfoReadModel product) {
		this.product = product;
	}

}
