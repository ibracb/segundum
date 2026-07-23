package segundum.domain.models.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class ConditionStatusTest {

	@Test
	void shouldReturnEqualOrBetterForNew() {
		List<ConditionStatus> result = ConditionStatus.NEW.equalOrBetter();
		assertEquals(1, result.size());
		assertTrue(result.contains(ConditionStatus.NEW));
	}

	@Test
	void shouldReturnEqualOrBetterForGood() {
		List<ConditionStatus> result = ConditionStatus.GOOD.equalOrBetter();
		assertEquals(3, result.size());
		assertTrue(result.contains(ConditionStatus.NEW));
		assertTrue(result.contains(ConditionStatus.LIKE_NEW));
		assertTrue(result.contains(ConditionStatus.GOOD));
	}

	@Test
	void shouldReturnEqualOrBetterForForParts() {
		List<ConditionStatus> result = ConditionStatus.FOR_PARTS.equalOrBetter();
		assertEquals(5, result.size());
	}
}
