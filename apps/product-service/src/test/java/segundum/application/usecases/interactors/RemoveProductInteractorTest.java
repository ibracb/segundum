package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RemoveProductCommand;
import segundum.application.usecases.RemoveProductUseCase;
import segundum.domain.events.ProductRemoved;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.product.salestatus.ProductNotForSaleException;
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
import segundum.infrastructure.persistence.fakes.repositories.FakeProductRepository;

import java.util.UUID;

class RemoveProductInteractorTest {

	private FakeProductRepository productRepository;
	private FakePublisher publisher;
	private RemoveProductUseCase interactor;

	private final CategoryId categoryId = CategoryId.fromString("1");
	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());

	@BeforeEach
	void setUp() {
		productRepository = new FakeProductRepository();
		publisher = new FakePublisher();
		interactor = new RemoveProductInteractor(productRepository, publisher);
	}

	@Test
	void shouldRemoveForSaleProduct() {
		Product product = createForSaleProduct();
		RemoveProductCommand command = new RemoveProductCommand(product.getProductId());

		interactor.execute(command);

		Product updated = productRepository.findById(product.getProductId()).get();
		assertEquals(SaleStatus.DELETED, updated.getSaleStatus());
	}

	@Test
	void shouldPublishProductRemovedEvent() {
		Product product = createForSaleProduct();
		RemoveProductCommand command = new RemoveProductCommand(product.getProductId());

		interactor.execute(command);

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof ProductRemoved);
		ProductRemoved event = (ProductRemoved) publisher.getPublishedEvents().get(0);
		assertEquals(product.getProductId(), event.getProductId());
	}

	@Test
	void shouldThrowWhenProductNotFound() {
		ProductId nonExistentId = ProductId.generate();
		RemoveProductCommand command = new RemoveProductCommand(nonExistentId);

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(command));
	}

	@Test
	void shouldThrowWhenProductNotForSale() {
		Product product = createDraftProduct();
		RemoveProductCommand command = new RemoveProductCommand(product.getProductId());

		assertThrows(ProductNotForSaleException.class, () -> interactor.execute(command));
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

	private Product createForSaleProduct() {
		Product product = createDraftProduct();
		product.putForSale();
		productRepository.update(product);
		return product;
	}
}
