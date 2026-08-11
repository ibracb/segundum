package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.purchasersurname.PurchaserSurnameBlankException;
import segundum.domain.exceptions.sale.purchasersurname.PurchaserSurnameInvalidFormatException;
import segundum.domain.exceptions.sale.purchasersurname.PurchaserSurnameNullException;

class PurchaserSurnameTest {

	@Test
	void shouldCreateValidSurname() {
		assertEquals("López", new PurchaserSurname("López").getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(PurchaserSurnameNullException.class, () -> new PurchaserSurname(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(PurchaserSurnameBlankException.class, () -> new PurchaserSurname(""));
		assertThrows(PurchaserSurnameBlankException.class, () -> new PurchaserSurname("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(PurchaserSurnameInvalidFormatException.class, () -> new PurchaserSurname("López1"));
	}

}
