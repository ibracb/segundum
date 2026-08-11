package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.pickup.description.DescriptionBlankException;
import segundum.domain.exceptions.sale.pickup.description.DescriptionNullException;
import segundum.domain.exceptions.sale.pickup.description.DescriptionTooLongException;
import segundum.domain.exceptions.sale.pickup.latitude.LatitudeTooHighException;
import segundum.domain.exceptions.sale.pickup.latitude.LatitudeTooLowException;
import segundum.domain.exceptions.sale.pickup.longitude.LongitudeTooHighException;
import segundum.domain.exceptions.sale.pickup.longitude.LongitudeTooLowException;

class PickupLocationTest {

	@Test
	void shouldCreateValidLocation() {
		PickupLocation loc = new PickupLocation("Plaza Mayor", 40.4168, -3.7038);
		assertEquals("Plaza Mayor", loc.getDescription());
		assertEquals(40.4168, loc.getLatitude());
		assertEquals(-3.7038, loc.getLongitude());
	}

	@Test
	void shouldThrowWhenDescriptionNull() {
		assertThrows(DescriptionNullException.class, () -> new PickupLocation(null, 0, 0));
	}

	@Test
	void shouldThrowWhenDescriptionBlank() {
		assertThrows(DescriptionBlankException.class, () -> new PickupLocation("", 0, 0));
		assertThrows(DescriptionBlankException.class, () -> new PickupLocation("   ", 0, 0));
	}

	@Test
	void shouldThrowWhenDescriptionTooLong() {
		assertThrows(DescriptionTooLongException.class, () -> new PickupLocation("a".repeat(501), 0, 0));
	}

	@Test
	void shouldThrowWhenLatitudeOutOfRange() {
		assertThrows(LatitudeTooLowException.class, () -> new PickupLocation("A", -90.1, 0));
		assertThrows(LatitudeTooHighException.class, () -> new PickupLocation("A", 90.1, 0));
	}

	@Test
	void shouldThrowWhenLongitudeOutOfRange() {
		assertThrows(LongitudeTooLowException.class, () -> new PickupLocation("A", 0, -180.1));
		assertThrows(LongitudeTooHighException.class, () -> new PickupLocation("A", 0, 180.1));
	}

	@Test
	void shouldAcceptBoundaryValues() {
		assertEquals(-90.0, new PickupLocation("A", -90.0, -180.0).getLatitude());
		assertEquals(-180.0, new PickupLocation("A", -90.0, -180.0).getLongitude());
	}

}
