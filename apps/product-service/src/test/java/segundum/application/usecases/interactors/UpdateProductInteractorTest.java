package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.UpdateProductCommand;
import segundum.application.usecases.UpdateProductUseCase;
import segundum.domain.events.ProductUpdated;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductFactory;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.product.Title;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductWriteRepository;

import java.util.UUID;

class UpdateProductInteractorTest {

	private FakeProductWriteRepository productRepository;
	private FakePublisher publisher;
	private UpdateProductUseCase interactor;

	private final CategoryId categoryId = CategoryId.fromString("1");
	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());
	private Product existingProduct;

	@BeforeEach
	void setUp() {
		productRepository = new FakeProductWriteRepository();
		publisher = new FakePublisher();
		interactor = new UpdateProductInteractor(productRepository, publisher);

		existingProduct = ProductFactory.create(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				sellerId);
		productRepository.create(existingProduct);
	}

	@Test
	void shouldUpdatePrice() {
		UpdateProductCommand command = new UpdateProductCommand(
				existingProduct.getProductId(),
				new Price(49.99),
				null,
				null);

		interactor.execute(command);

		Product updated = productRepository.findById(existingProduct.getProductId()).get();
		assertEquals(49.99, updated.getPrice().getValue());
	}

	@Test
	void shouldUpdateDescription() {
		UpdateProductCommand command = new UpdateProductCommand(
				existingProduct.getProductId(),
				null,
				new Description("Nueva descripción"),
				null);

		interactor.execute(command);

		Product updated = productRepository.findById(existingProduct.getProductId()).get();
		assertEquals("Nueva descripción", updated.getDescription().getValue());
	}

	@Test
	void shouldUpdateConditionStatus() {
		UpdateProductCommand command = new UpdateProductCommand(
				existingProduct.getProductId(),
				null,
				null,
				ConditionStatus.GOOD);

		interactor.execute(command);

		Product updated = productRepository.findById(existingProduct.getProductId()).get();
		assertEquals(ConditionStatus.GOOD, updated.getConditionStatus());
	}

	@Test
	void shouldPublishProductUpdatedEvent() {
		UpdateProductCommand command = new UpdateProductCommand(
				existingProduct.getProductId(),
				new Price(49.99),
				null,
				null);

		interactor.execute(command);

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof ProductUpdated);
	}

	@Test
	void shouldThrowWhenProductNotFound() {
		ProductId nonExistentId = ProductId.generate();
		UpdateProductCommand command = new UpdateProductCommand(
				nonExistentId,
				new Price(49.99),
				null,
				null);

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(command));
	}
}
