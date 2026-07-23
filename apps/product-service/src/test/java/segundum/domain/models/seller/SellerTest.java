package segundum.domain.models.seller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.UUID;

class SellerTest {

	private final SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());
	private final Name name = new Name("Juan");
	private final Surname surname = new Surname("Pérez");
	private final Email email = new Email("juan@email.com");

	@Test
	void shouldCreateSellerWithActiveStatus() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		assertEquals(sellerId, seller.getSellerId());
		assertEquals("Juan", seller.getName().getValue());
		assertEquals("Pérez", seller.getSurname().getValue());
		assertEquals("juan@email.com", seller.getEmail().getValue());
		assertEquals(SellerStatus.ACTIVE, seller.getStatus());
		assertFalse(seller.isDeleted());
	}

	@Test
	void shouldChangeName() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.changeName(new Name("Carlos"));
		assertEquals("Carlos", seller.getName().getValue());
	}

	@Test
	void shouldNotChangeNameWhenSameValue() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.changeName(new Name("Juan"));
		assertEquals("Juan", seller.getName().getValue());
	}

	@Test
	void shouldChangeSurname() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.changeSurname(new Surname("García"));
		assertEquals("García", seller.getSurname().getValue());
	}

	@Test
	void shouldNotChangeSurnameWhenSameValue() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.changeSurname(new Surname("Pérez"));
		assertEquals("Pérez", seller.getSurname().getValue());
	}

	@Test
	void shouldDeleteSeller() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.delete();
		assertTrue(seller.isDeleted());
		assertEquals(SellerStatus.DELETED, seller.getStatus());
	}
}
