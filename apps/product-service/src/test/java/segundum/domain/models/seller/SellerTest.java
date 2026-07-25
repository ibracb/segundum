package segundum.domain.models.seller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import segundum.domain.exceptions.SameValueException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;

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
		assertTrue(seller.isActive());
	}

	@Test
	void shouldChangeName() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.changeName(new Name("Carlos"));
		assertEquals("Carlos", seller.getName().getValue());
	}

	@Test
	void shouldThrowWhenChangingNameToSameValue() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		assertThrows(SameValueException.class, () -> seller.changeName(new Name("Juan")));
	}

	@Test
	void shouldChangeSurname() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.changeSurname(new Surname("García"));
		assertEquals("García", seller.getSurname().getValue());
	}

	@Test
	void shouldThrowWhenChangingSurnameToSameValue() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		assertThrows(SameValueException.class, () -> seller.changeSurname(new Surname("Pérez")));
	}

	@Test
	void shouldDeactivateSeller() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.deactivate();
		assertFalse(seller.isActive());
		assertEquals(SellerStatus.INACTIVE, seller.getStatus());
	}

	@Test
	void shouldThrowWhenDeactivatingInactiveSeller() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.deactivate();
		assertThrows(SellerNotActiveException.class, () -> seller.deactivate());
	}

	@Test
	void shouldThrowWhenChangingNameIfInactive() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.deactivate();
		assertThrows(SellerNotActiveException.class, () -> seller.changeName(new Name("Carlos")));
	}

	@Test
	void shouldThrowWhenChangingSurnameIfInactive() {
		Seller seller = SellerFactory.create(sellerId, name, surname, email);
		seller.deactivate();
		assertThrows(SellerNotActiveException.class, () -> seller.changeSurname(new Surname("García")));
	}
}
