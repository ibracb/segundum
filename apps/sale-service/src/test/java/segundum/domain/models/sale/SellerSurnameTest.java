package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.sellersurname.SellerSurnameBlankException;
import segundum.domain.exceptions.sale.sellersurname.SellerSurnameInvalidFormatException;
import segundum.domain.exceptions.sale.sellersurname.SellerSurnameNullException;

class SellerSurnameTest {

	@Test
	void shouldCreateValidSurname() {
		assertEquals("García", new SellerSurname("García").getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(SellerSurnameNullException.class, () -> new SellerSurname(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(SellerSurnameBlankException.class, () -> new SellerSurname(""));
		assertThrows(SellerSurnameBlankException.class, () -> new SellerSurname("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(SellerSurnameInvalidFormatException.class, () -> new SellerSurname("García2"));
	}

}
