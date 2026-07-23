package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.PutProductForSaleCommand;
import segundum.application.usecases.PutProductForSaleUseCase;
import segundum.domain.events.ProductPutOnSale;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.product.salestatus.ProductNotDraftException;
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
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductWriteRepository;

import java.util.UUID;

class PutProductForSaleInteractorTest {

	private FakeProductWriteRepository productRepository;
	private FakePublisher publisher;
	private PutProductForSaleUseCase interactor;

	private final CategoryId categoryId = CategoryId.fromString("1");
	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());

	@BeforeEach
	void setUp() {
		productRepository = new FakeProductWriteRepository();
		publisher = new FakePublisher();
		interactor = new PutProductForSaleInteractor(productRepository, publisher);
	}

	@Test
	void shouldPutDraftProductForSale() {
		Product product = createDraftProduct();
		PutProductForSaleCommand command = new PutProductForSaleCommand(product.getProductId());

		interactor.execute(command);

		Product updated = productRepository.findById(product.getProductId()).get();
		assertEquals(SaleStatus.FOR_SALE, updated.getSaleStatus());
	}

	@Test
	void shouldPublishProductPutOnSaleEvent() {
		Product product = createDraftProduct();
		PutProductForSaleCommand command = new PutProductForSaleCommand(product.getProductId());

		interactor.execute(command);

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof ProductPutOnSale);
		ProductPutOnSale event = (ProductPutOnSale) publisher.getPublishedEvents().get(0);
		assertEquals(product.getProductId(), event.getProductId());
	}

	@Test
	void shouldThrowWhenProductNotFound() {
		ProductId nonExistentId = ProductId.generate();
		PutProductForSaleCommand command = new PutProductForSaleCommand(nonExistentId);

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(command));
	}

	@Test
	void shouldThrowWhenProductNotDraft() {
		Product product = createDraftProduct();
		product.putForSale();
		productRepository.update(product);

		PutProductForSaleCommand command = new PutProductForSaleCommand(product.getProductId());
		assertThrows(ProductNotDraftException.class, () -> interactor.execute(command));
	}

	private Product createDraftProduct() {
		Product product = ProductFactory.create(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				sellerId);
		productRepository.create(product);
		return product;
	}
}
