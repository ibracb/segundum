package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.sellername.SellerNameBlankException;
import segundum.domain.exceptions.sale.sellername.SellerNameInvalidFormatException;
import segundum.domain.exceptions.sale.sellername.SellerNameNullException;

class SellerNameTest {

	@Test
	void shouldCreateValidName() {
		assertEquals("María", new SellerName("María").getValue());
	}

	@Test
	void shouldAcceptNameWithSpaces() {
		assertEquals("María José", new SellerName("María José").getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(SellerNameNullException.class, () -> new SellerName(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(SellerNameBlankException.class, () -> new SellerName(""));
		assertThrows(SellerNameBlankException.class, () -> new SellerName("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(SellerNameInvalidFormatException.class, () -> new SellerName("María123"));
		assertThrows(SellerNameInvalidFormatException.class, () -> new SellerName("María!"));
	}

}
