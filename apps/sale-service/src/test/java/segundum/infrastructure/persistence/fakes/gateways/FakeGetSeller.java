package segundum.infrastructure.persistence.fakes.gateways;

import segundum.application.gateways.GetSeller;
import segundum.application.dtos.seller.SellerData;
import segundum.domain.models.sale.SellerId;

public class FakeGetSeller implements GetSeller {

	private SellerData seller;

	@Override
	public SellerData fetch(SellerId sellerId) {
		return seller;
	}

	public void setSeller(SellerData seller) {
		this.seller = seller;
	}

}
