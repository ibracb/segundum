package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.CreateProductCommand;
import segundum.application.usecases.CreateProductUseCase;
import segundum.domain.events.ProductCreated;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.product.Title;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.repositories.FakeCategoryRepository;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductRepository;
import segundum.infrastructure.persistence.fakes.repositories.FakeSellerRepository;

import java.util.UUID;

import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.Surname;
import segundum.domain.models.seller.Email;

class CreateProductInteractorTest {

	private FakeCategoryRepository categoryRepository;
	private FakeSellerRepository sellerRepository;
	private FakeProductRepository productRepository;
	private FakePublisher publisher;
	private CreateProductUseCase interactor;

	private final CategoryId categoryId = CategoryId.fromString("1");
	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());

	@BeforeEach
	void setUp() {
		categoryRepository = new FakeCategoryRepository();
		sellerRepository = new FakeSellerRepository();
		productRepository = new FakeProductRepository();
		publisher = new FakePublisher();
		interactor = new CreateProductInteractor(categoryRepository, sellerRepository, productRepository, publisher);

		categoryRepository.addExistingId(categoryId);

		Seller seller = SellerFactory.create(sellerId,
				new segundum.domain.models.seller.Name("Juan"),
				new Surname("Pérez"),
				new Email("juan@email.com"));
		sellerRepository.create(seller);
	}

	@Test
	void shouldCreateProduct() {
		CreateProductCommand command = new CreateProductCommand(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				sellerId);

		ProductId productId = interactor.execute(command);

		assertNotNull(productId);
		assertTrue(productRepository.findById(productId).isPresent());
	}

	@Test
	void shouldPublishProductCreatedEvent() {
		CreateProductCommand command = new CreateProductCommand(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				sellerId);

		ProductId productId = interactor.execute(command);

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof ProductCreated);
		ProductCreated event = (ProductCreated) publisher.getPublishedEvents().get(0);
		assertEquals(productId, event.getProductId());
		assertEquals("iPhone 14", event.getTitle().getValue());
	}

	@Test
	void shouldThrowWhenCategoryNotFound() {
		CategoryId nonExistentCategoryId = CategoryId.fromString("999");
		CreateProductCommand command = new CreateProductCommand(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				nonExistentCategoryId,
				false,
				sellerId);

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(command));
	}

	@Test
	void shouldThrowWhenSellerNotFound() {
		SellerId nonExistentSellerId = SellerId.fromUUID(UUID.randomUUID());
		CreateProductCommand command = new CreateProductCommand(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				nonExistentSellerId);

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(command));
	}

	@Test
	void shouldThrowWhenSellerIsInactive() {
		Seller inactiveSeller = sellerRepository.findById(sellerId).get();
		inactiveSeller.deactivate();
		sellerRepository.create(inactiveSeller);

		CreateProductCommand command = new CreateProductCommand(
				new Title("iPhone 14"),
				new Description("Un iPhone en perfecto estado"),
				new Price(29.99),
				ConditionStatus.NEW,
				categoryId,
				false,
				sellerId);

		assertThrows(SellerNotActiveException.class, () -> interactor.execute(command));
	}
}
