package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

class DateTimeTest {

	@Test
	void shouldCreateNow() {
		assertNotNull(DateTime.now().getValue());
	}

	@Test
	void shouldCreateFromInstant() {
		Instant instant = Instant.parse("2026-08-11T10:00:00Z");
		assertEquals(instant, DateTime.fromInstant(instant).getValue());
	}

	@Test
	void shouldBeEqualOnSameValue() {
		Instant instant = Instant.parse("2026-08-11T10:00:00Z");
		DateTime a = DateTime.fromInstant(instant);
		DateTime b = DateTime.fromInstant(instant);
		assertEquals(a, b);
	}

}
