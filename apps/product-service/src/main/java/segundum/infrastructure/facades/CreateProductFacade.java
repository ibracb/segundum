package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.CreateProductCommand;
import segundum.application.usecases.CreateProductUseCase;
import segundum.domain.models.product.ProductId;

/**
 * Represents the transaction boundary for creating a product.
 */
@Component
public class CreateProductFacade {

	/**
	 * The use case for creating a product.
	 */
	private final CreateProductUseCase useCase;

	/**
	 * Constructs a new CreateProductFacade with the given use case.
	 *
	 * @param useCase the use case for creating a product
	 */
	public CreateProductFacade(CreateProductUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Creates a product within a single transaction.
	 *
	 * @param command the create product command
	 * @return the identifier of the created product
	 */
	@Transactional
	public ProductId run(CreateProductCommand command) {
		return useCase.execute(command);
	}

}
