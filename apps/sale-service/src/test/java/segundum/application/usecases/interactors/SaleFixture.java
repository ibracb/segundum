package segundum.application.usecases.interactors;

import java.util.UUID;

import segundum.domain.events.SaleProposed;
import segundum.domain.events.SaleReserved;
import segundum.domain.models.sale.DateTime;
import segundum.domain.models.sale.Price;
import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.PurchaserName;
import segundum.domain.models.sale.PurchaserSurname;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.domain.models.sale.SellerName;
import segundum.domain.models.sale.SellerSurname;
import segundum.domain.models.sale.Title;
import segundum.infrastructure.persistence.fakes.FakeEventStore;

final class SaleFixture {

	static final UUID SELLER = UUID.fromString("11111111-1111-1111-1111-111111111111");
	static final UUID PURCHASER = UUID.fromString("22222222-2222-2222-2222-222222222222");
	static final UUID PRODUCT = UUID.fromString("44444444-4444-4444-4444-444444444444");

	private SaleFixture() {
	}

	static ProductId productId() {
		return ProductId.fromUUID(PRODUCT);
	}

	static PurchaserId purchaserId() {
		return PurchaserId.fromUUID(PURCHASER);
	}

	static SaleId saleId() {
		return SaleId.generate();
	}

	static SaleProposed proposal(SaleId saleId) {
		return new SaleProposed(
				saleId,
				productId(),
				SellerId.fromUUID(SELLER),
				new SellerName("Juan"), new SellerSurname("Pérez"),
				PurchaserId.fromUUID(PURCHASER),
				new PurchaserName("Ana"), new PurchaserSurname("López"),
				new Price(100), new Title("iPhone 13"), null, DateTime.now());
	}

	static void seedProposed(FakeEventStore store, SaleId saleId) {
		store.append(proposal(saleId));
	}

	static void seedReserved(FakeEventStore store, SaleId saleId) {
		store.append(proposal(saleId));
		store.append(new SaleReserved(saleId, productId()));
	}

}
