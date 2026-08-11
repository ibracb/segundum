package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.ProposeSaleCommand;
import segundum.application.readmodels.product.PickupLocationReadModel;
import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.application.readmodels.purchaser.PurchaserReadModel;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.application.usecases.ProposeSaleUseCase;
import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleProposed;
import segundum.domain.exceptions.sale.creation.ProductNotForSaleException;
import segundum.domain.exceptions.sale.creation.SaleAlreadyProposedException;
import segundum.domain.models.sale.SaleId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakeEventStore;
import segundum.infrastructure.persistence.fakes.gateways.FakeGetProductBasicInfo;
import segundum.infrastructure.persistence.fakes.gateways.FakeGetPurchaser;
import segundum.infrastructure.persistence.fakes.gateways.FakeGetSeller;

class ProposeSaleInteractorTest {

	private FakeEventStore eventStore;
	private FakePublisher publisher;
	private FakeGetProductBasicInfo productGateway;
	private FakeGetSeller sellerGateway;
	private FakeGetPurchaser purchaserGateway;
	private ProposeSaleUseCase interactor;

	@BeforeEach
	void setUp() {
		eventStore = new FakeEventStore();
		publisher = new FakePublisher();
		productGateway = new FakeGetProductBasicInfo();
		sellerGateway = new FakeGetSeller();
		purchaserGateway = new FakeGetPurchaser();
		interactor = new ProposeSaleInteractor(productGateway, sellerGateway,
				purchaserGateway, eventStore, publisher);
	}

	@Test
	void shouldProposeSaleAndPublishEvent() {
		productGateway.setProduct(new ProductBasicInfoReadModel(
				SaleFixture.PRODUCT.toString(), "iPhone 13", 100.0, null,
				SaleFixture.SELLER.toString(), "FOR_SALE"));
		sellerGateway.setSeller(new SellerReadModel(SaleFixture.SELLER.toString(), "Juan", "Pérez"));
		purchaserGateway.setPurchaser(new PurchaserReadModel(SaleFixture.PURCHASER.toString(), "Ana", "López"));

		SaleId saleId = interactor.execute(new ProposeSaleCommand(
				SaleFixture.productId(), SaleFixture.purchaserId()));

		assertNotNull(saleId);
		assertEquals(1, eventStore.getAppendedEvents().size());
		assertEquals(1, publisher.getPublishedEvents().size());
		SaleProposed event = (SaleProposed) publisher.getPublishedEvents().get(0);
		assertEquals(saleId, event.getSaleId());
		assertEquals(SaleFixture.SELLER.toString(), event.getSellerId().getValue().toString());
		assertEquals("Juan", event.getSellerName().getValue());
		assertEquals("Ana", event.getPurchaserName().getValue());
		assertEquals(100.0, event.getPrice().getValue());
		assertEquals("iPhone 13", event.getTitle().getValue());
	}

	@Test
	void shouldThrowWhenProductNotForSale() {
		productGateway.setProduct(new ProductBasicInfoReadModel(
				SaleFixture.PRODUCT.toString(), "iPhone 13", 100.0, null,
				SaleFixture.SELLER.toString(), "SOLD"));

		assertThrows(ProductNotForSaleException.class, () -> interactor.execute(
				new ProposeSaleCommand(SaleFixture.productId(), SaleFixture.purchaserId())));
		assertTrue(eventStore.getAppendedEvents().isEmpty());
		assertTrue(publisher.getPublishedEvents().isEmpty());
	}

	@Test
	void shouldThrowWhenProposalAlreadyExists() {
		productGateway.setProduct(new ProductBasicInfoReadModel(
				SaleFixture.PRODUCT.toString(), "iPhone 13", 100.0, null,
				SaleFixture.SELLER.toString(), "FOR_SALE"));
		sellerGateway.setSeller(new SellerReadModel(SaleFixture.SELLER.toString(), "Juan", "Pérez"));
		purchaserGateway.setPurchaser(new PurchaserReadModel(SaleFixture.PURCHASER.toString(), "Ana", "López"));
		SaleFixture.seedProposed(eventStore, SaleFixture.saleId());

		assertThrows(SaleAlreadyProposedException.class, () -> interactor.execute(
				new ProposeSaleCommand(SaleFixture.productId(), SaleFixture.purchaserId())));
	}

	@Test
	void shouldCreateSaleWithPickupLocation() {
		PickupLocationReadModel pickup = new PickupLocationReadModel("Plaza Mayor", 40.4168, -3.7038);
		productGateway.setProduct(new ProductBasicInfoReadModel(
				SaleFixture.PRODUCT.toString(), "iPhone 13", 100.0, pickup,
				SaleFixture.SELLER.toString(), "FOR_SALE"));
		sellerGateway.setSeller(new SellerReadModel(SaleFixture.SELLER.toString(), "Juan", "Pérez"));
		purchaserGateway.setPurchaser(new PurchaserReadModel(SaleFixture.PURCHASER.toString(), "Ana", "López"));

		interactor.execute(new ProposeSaleCommand(SaleFixture.productId(), SaleFixture.purchaserId()));

		DomainEvent event = publisher.getPublishedEvents().get(0);
		assertNotNull(((SaleProposed) event).getPickupLocation());
		assertEquals("Plaza Mayor", ((SaleProposed) event).getPickupLocation().getDescription());
		assertEquals(40.4168, ((SaleProposed) event).getPickupLocation().getLatitude());
	}

}
