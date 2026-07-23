package segundum.domain.models.product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the condition status of a product.
 */
public enum ConditionStatus {

	/**
	 * The product is new and unused.
	 */
	NEW,

	/**
	 * The product is like new and unused.
	 */
	LIKE_NEW,

	/**
	 * The product is in good condition.
	 */
	GOOD,

	/**
	 * The product is in acceptable condition.
	 */
	ACCEPTABLE,

	/**
	 * The product is sold for parts only.
	 */
	FOR_PARTS;

	/**
	 * Returns a list of condition statuses that are equal to or better than this one.
	 *
	 * @return a list of equal or better condition statuses
	 */
	public List<ConditionStatus> equalOrBetter() {
		return Arrays.stream(values())
				.filter(s -> s.ordinal() <= this.ordinal())
				.collect(Collectors.toList());
	}

}
