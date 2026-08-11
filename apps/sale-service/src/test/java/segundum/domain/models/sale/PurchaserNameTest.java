package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.purchasername.PurchaserNameBlankException;
import segundum.domain.exceptions.sale.purchasername.PurchaserNameInvalidFormatException;
import segundum.domain.exceptions.sale.purchasername.PurchaserNameNullException;

class PurchaserNameTest {

	@Test
	void shouldCreateValidName() {
		assertEquals("Ana", new PurchaserName("Ana").getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(PurchaserNameNullException.class, () -> new PurchaserName(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(PurchaserNameBlankException.class, () -> new PurchaserName(""));
		assertThrows(PurchaserNameBlankException.class, () -> new PurchaserName("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(PurchaserNameInvalidFormatException.class, () -> new PurchaserName("Ana1"));
	}

}
