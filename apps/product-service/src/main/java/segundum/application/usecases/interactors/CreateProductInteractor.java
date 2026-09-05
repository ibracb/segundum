package segundum.application.usecases.interactors;

import segundum.application.commands.CreateProductCommand;
import segundum.application.usecases.CreateProductUseCase;
import segundum.domain.events.ProductCreated;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductFactory;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.product.ProductId;
import segundum.application.outbound.DomainEventPublisher;
import segundum.domain.repositories.CategoryRepository;
import segundum.domain.repositories.ProductRepository;
import segundum.domain.repositories.SellerRepository;

/**
 * Represents the interactor for creating a new product in the system.
 */
public class CreateProductInteractor implements CreateProductUseCase {

	private final CategoryRepository categoryRepository;
	private final SellerRepository sellerRepository;
	private final ProductRepository productRepository;
	private final DomainEventPublisher domainEventPublisher;

	public CreateProductInteractor(CategoryRepository categoryRepository, SellerRepository sellerRepository,
			ProductRepository productRepository, DomainEventPublisher domainEventPublisher) {
		this.categoryRepository = categoryRepository;
		this.sellerRepository = sellerRepository;
		this.productRepository = productRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public ProductId execute(CreateProductCommand command) {
		if (!categoryRepository.existsById(command.getCategoryId())) {
			throw new EntityNotFoundException("Category", command.getCategoryId().toString());
		}
		Seller seller = sellerRepository.findById(command.getSellerId())
				.orElseThrow(() -> new EntityNotFoundException("Seller", command.getSellerId().getValue().toString()));
		if (!seller.isActive()) {
			throw new SellerNotActiveException(command.getSellerId());
		}
		Product product = ProductFactory.create(
				command.getTitle(),
				command.getDescription(),
				command.getPrice(),
				command.getStatus(),
				command.getCategoryId(),
				command.isShippingAvailable(),
				command.getSellerId());

		productRepository.create(product);

		domainEventPublisher.publish(new ProductCreated(
				product.getProductId(),
				product.getTitle(),
				product.getDescription(),
				product.getPrice(),
				product.getPublicationDate(),
				product.getConditionStatus(),
				product.getCategoryId(),
				product.isShippingAvailable(),
				product.getSellerId()));
		return product.getProductId();
	}

}
