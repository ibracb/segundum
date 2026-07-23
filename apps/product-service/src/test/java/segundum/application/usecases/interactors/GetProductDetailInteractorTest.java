package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetProductDetailQuery;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.usecases.GetProductDetailUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductReadRepository;

class GetProductDetailInteractorTest {

	private FakeProductReadRepository repository;
	private GetProductDetailUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeProductReadRepository();
		interactor = new GetProductDetailInteractor(repository);
	}

	@Test
	void shouldReturnProductDetail() {
		ProductId productId = ProductId.generate();
		ProductDetail detail = new ProductDetail("1", "iPhone 14", "Desc", 29.99, null, null,
				null, false, null, null, 0);
		repository.setProductDetail(Optional.of(detail));

		Optional<ProductDetail> result = interactor.execute(new GetProductDetailQuery(productId));

		assertTrue(result.isPresent());
		assertEquals("iPhone 14", result.get().getTitle());
	}

	@Test
	void shouldReturnEmptyWhenNotFound() {
		ProductId productId = ProductId.generate();
		repository.setProductDetail(Optional.empty());

		Optional<ProductDetail> result = interactor.execute(new GetProductDetailQuery(productId));

		assertTrue(result.isEmpty());
	}
}
