package segundum.domain.models.pickup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.pickup.description.DescriptionBlankException;
import segundum.domain.exceptions.pickup.description.DescriptionNullException;
import segundum.domain.exceptions.pickup.description.DescriptionTooLongException;
import segundum.domain.exceptions.pickup.latitude.LatitudeTooHighException;
import segundum.domain.exceptions.pickup.latitude.LatitudeTooLowException;
import segundum.domain.exceptions.pickup.longitude.LongitudeTooHighException;
import segundum.domain.exceptions.pickup.longitude.LongitudeTooLowException;

class PickupLocationTest {

	@Test
	void shouldCreateValidPickupLocation() {
		PickupLocation location = new PickupLocation("Local de venta", 40.4168, -3.7038);
		assertEquals("Local de venta", location.getDescription());
		assertEquals(40.4168, location.getLatitude());
		assertEquals(-3.7038, location.getLongitude());
	}

	@Test
	void shouldAcceptBoundaryLatLon() {
		PickupLocation min = new PickupLocation("Min", -90, -180);
		PickupLocation max = new PickupLocation("Max", 90, 180);
		assertEquals(-90, min.getLatitude());
		assertEquals(90, max.getLatitude());
		assertEquals(-180, min.getLongitude());
		assertEquals(180, max.getLongitude());
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
		String longDesc = "A".repeat(501);
		assertThrows(DescriptionTooLongException.class, () -> new PickupLocation(longDesc, 0, 0));
	}

	@Test
	void shouldThrowWhenLatitudeTooLow() {
		assertThrows(LatitudeTooLowException.class, () -> new PickupLocation("Desc", -91, 0));
	}

	@Test
	void shouldThrowWhenLatitudeTooHigh() {
		assertThrows(LatitudeTooHighException.class, () -> new PickupLocation("Desc", 91, 0));
	}

	@Test
	void shouldThrowWhenLongitudeTooLow() {
		assertThrows(LongitudeTooLowException.class, () -> new PickupLocation("Desc", 0, -181));
	}

	@Test
	void shouldThrowWhenLongitudeTooHigh() {
		assertThrows(LongitudeTooHighException.class, () -> new PickupLocation("Desc", 0, 181));
	}
}
