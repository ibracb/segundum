package segundum.application.eventhandlers.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.eventhandlers.SaleEventHandler;
import segundum.application.events.sales.SaleCancelled;
import segundum.application.events.sales.SaleCompleted;
import segundum.application.events.sales.SaleReserved;
import segundum.domain.events.ProductReservationCancelled;
import segundum.domain.events.ProductReserved;
import segundum.domain.events.ProductSold;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductFactory;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.product.SaleStatus;
import segundum.domain.models.product.Title;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.logging.fakes.FakeLogEmitter;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductWriteRepository;

class SaleEventHandlerInteractorTest {

	private FakeProductWriteRepository productRepository;
	private FakePublisher publisher;
	private FakeLogEmitter logEmitter;
	private SaleEventHandler handler;

	private final CategoryId categoryId = CategoryId.fromString("1");
	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());

	@BeforeEach
	void setUp() {
		productRepository = new FakeProductWriteRepository();
		publisher = new FakePublisher();
		logEmitter = new FakeLogEmitter();
		handler = new SaleEventHandlerInteractor(productRepository, publisher, logEmitter);
	}

	@Test
	void shouldReserveProductOnSaleReserved() {
		Product product = createForSaleProduct();
		handler.onSaleReserved(new SaleReserved(product.getProductId().getValue()));

		assertEquals(SaleStatus.RESERVED, productRepository.findById(product.getProductId()).get().getSaleStatus());
		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof ProductReserved);
		ProductReserved event = (ProductReserved) publisher.getPublishedEvents().get(0);
		assertEquals(product.getProductId(), event.getProductId());
	}

	@Test
	void shouldSellProductOnSaleCompleted() {
		Product product = createReservedProduct();
		handler.onSaleCompleted(new SaleCompleted(product.getProductId().getValue()));

		assertEquals(SaleStatus.SOLD, productRepository.findById(product.getProductId()).get().getSaleStatus());
		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof ProductSold);
		ProductSold event = (ProductSold) publisher.getPublishedEvents().get(0);
		assertEquals(product.getProductId(), event.getProductId());
	}

	@Test
	void shouldCancelReservationOnSaleCancelled() {
		Product product = createReservedProduct();
		handler.onSaleCancelled(new SaleCancelled(product.getProductId().getValue()));

		assertEquals(SaleStatus.FOR_SALE, productRepository.findById(product.getProductId()).get().getSaleStatus());
		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof ProductReservationCancelled);
		ProductReservationCancelled event = (ProductReservationCancelled) publisher.getPublishedEvents().get(0);
		assertEquals(product.getProductId(), event.getProductId());
	}

	@Test
	void shouldLogWarningWhenProductNotFound() {
		ProductId nonExistentId = ProductId.generate();
		handler.onSaleReserved(new SaleReserved(nonExistentId.getValue()));

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}

	@Test
	void shouldLogWarningOnInvalidEvent() {
		handler.onSaleReserved(new SaleReserved(null));

		assertEquals(1, logEmitter.getWarnings().size());
		assertTrue(logEmitter.getWarnings().get(0).contains("discarded"));
	}

	private Product createForSaleProduct() {
		Product product = ProductFactory.create(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				sellerId);
		product.putForSale();
		productRepository.create(product);
		return product;
	}

	private Product createReservedProduct() {
		Product product = createForSaleProduct();
		product.reserve();
		productRepository.update(product);
		return product;
	}
}
