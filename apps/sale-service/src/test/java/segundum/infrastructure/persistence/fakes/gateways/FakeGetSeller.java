package segundum.infrastructure.persistence.fakes.gateways;

import segundum.application.gateways.GetSeller;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.domain.models.sale.SellerId;

public class FakeGetSeller implements GetSeller {

	private SellerReadModel seller;

	@Override
	public SellerReadModel fetch(SellerId sellerId) {
		return seller;
	}

	public void setSeller(SellerReadModel seller) {
		this.seller = seller;
	}

}
