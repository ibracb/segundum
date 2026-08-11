package segundum.infrastructure.persistence.fakes.gateways;

import segundum.application.gateways.GetPurchaser;
import segundum.application.readmodels.purchaser.PurchaserReadModel;
import segundum.domain.models.sale.PurchaserId;

public class FakeGetPurchaser implements GetPurchaser {

	private PurchaserReadModel purchaser;

	@Override
	public PurchaserReadModel fetch(PurchaserId purchaserId) {
		return purchaser;
	}

	public void setPurchaser(PurchaserReadModel purchaser) {
		this.purchaser = purchaser;
	}

}
