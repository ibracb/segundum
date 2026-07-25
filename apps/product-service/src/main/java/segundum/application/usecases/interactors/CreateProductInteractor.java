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
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.CategoryWriteRepository;
import segundum.domain.repositories.ProductWriteRepository;
import segundum.domain.repositories.SellerRepository;

/**
 * Represents the interactor for creating a new product in the system.
 */
public class CreateProductInteractor implements CreateProductUseCase {

	private final CategoryWriteRepository categoryWriteRepository;
	private final SellerRepository sellerRepository;
	private final ProductWriteRepository productWriteRepository;
	private final DomainEventPublisher domainEventPublisher;

	public CreateProductInteractor(CategoryWriteRepository categoryWriteRepository, SellerRepository sellerRepository,
			ProductWriteRepository productWriteRepository, DomainEventPublisher domainEventPublisher) {
		this.categoryWriteRepository = categoryWriteRepository;
		this.sellerRepository = sellerRepository;
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public ProductId execute(CreateProductCommand command) {
		if (!categoryWriteRepository.existsById(command.getCategoryId())) {
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

		productWriteRepository.create(product);

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
