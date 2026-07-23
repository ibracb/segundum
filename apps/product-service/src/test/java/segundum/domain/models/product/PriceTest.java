package segundum.domain.models.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.product.price.PriceNegativeException;

class PriceTest {

	@Test
	void shouldCreateValidPrice() {
		Price price = new Price(29.99);
		assertEquals(29.99, price.getValue());
	}

	@Test
	void shouldAcceptZero() {
		Price price = new Price(0);
		assertEquals(0, price.getValue());
	}

	@Test
	void shouldThrowWhenNegative() {
		assertThrows(PriceNegativeException.class, () -> new Price(-1));
		assertThrows(PriceNegativeException.class, () -> new Price(-0.01));
	}
}
