package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.price.PriceNegativeException;

class PriceTest {

	@Test
	void shouldCreatePositivePrice() {
		assertEquals(150.5, new Price(150.5).getValue());
	}

	@Test
	void shouldAllowZero() {
		assertEquals(0.0, new Price(0).getValue());
	}

	@Test
	void shouldThrowWhenNegative() {
		assertThrows(PriceNegativeException.class, () -> new Price(-0.01));
	}

}
