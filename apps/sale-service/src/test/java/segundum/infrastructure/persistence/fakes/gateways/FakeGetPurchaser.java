package segundum.infrastructure.persistence.fakes.gateways;

import segundum.application.gateways.GetPurchaser;
import segundum.application.dtos.purchaser.PurchaserData;
import segundum.domain.models.sale.PurchaserId;

public class FakeGetPurchaser implements GetPurchaser {

	private PurchaserData purchaser;

	@Override
	public PurchaserData fetch(PurchaserId purchaserId) {
		return purchaser;
	}

	public void setPurchaser(PurchaserData purchaser) {
		this.purchaser = purchaser;
	}

}
