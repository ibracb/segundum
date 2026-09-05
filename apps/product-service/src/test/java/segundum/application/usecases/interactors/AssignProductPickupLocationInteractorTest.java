package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.AssignProductPickupLocationCommand;
import segundum.application.usecases.AssignProductPickupLocationUseCase;
import segundum.domain.events.PickupLocationAssigned;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.product.salestatus.ProductNotDraftException;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductFactory;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.product.Title;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductRepository;

import java.util.UUID;

class AssignProductPickupLocationInteractorTest {

	private FakeProductRepository productRepository;
	private FakePublisher publisher;
	private AssignProductPickupLocationUseCase interactor;

	private final CategoryId categoryId = CategoryId.fromString("1");
	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());

	@BeforeEach
	void setUp() {
		productRepository = new FakeProductRepository();
		publisher = new FakePublisher();
		interactor = new AssignProductPickupLocationInteractor(productRepository, publisher);
	}

	@Test
	void shouldAssignPickupLocation() {
		Product product = createDraftProduct();
		PickupLocation location = new PickupLocation("Local de venta", 40.4168, -3.7038);
		AssignProductPickupLocationCommand command = new AssignProductPickupLocationCommand(
				product.getProductId(), location);

		interactor.execute(command);

		Product updated = productRepository.findById(product.getProductId()).get();
		assertNotNull(updated.getPickupLocation());
		assertEquals("Local de venta", updated.getPickupLocation().getDescription());
		assertEquals(40.4168, updated.getPickupLocation().getLatitude());
	}

	@Test
	void shouldPublishPickupLocationAssignedEvent() {
		Product product = createDraftProduct();
		PickupLocation location = new PickupLocation("Local de venta", 40.4168, -3.7038);
		AssignProductPickupLocationCommand command = new AssignProductPickupLocationCommand(
				product.getProductId(), location);

		interactor.execute(command);

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof PickupLocationAssigned);
		PickupLocationAssigned event = (PickupLocationAssigned) publisher.getPublishedEvents().get(0);
		assertEquals(product.getProductId(), event.getProductId());
	}

	@Test
	void shouldThrowWhenProductNotFound() {
		ProductId nonExistentId = ProductId.generate();
		PickupLocation location = new PickupLocation("Local de venta", 40.4168, -3.7038);
		AssignProductPickupLocationCommand command = new AssignProductPickupLocationCommand(
				nonExistentId, location);

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(command));
	}

	@Test
	void shouldThrowWhenProductNotDraft() {
		Product product = createDraftProduct();
		product.putForSale();
		productRepository.update(product);

		PickupLocation location = new PickupLocation("Local de venta", 40.4168, -3.7038);
		AssignProductPickupLocationCommand command = new AssignProductPickupLocationCommand(
				product.getProductId(), location);

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
