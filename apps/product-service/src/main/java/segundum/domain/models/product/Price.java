package segundum.domain.models.product;

import segundum.domain.exceptions.product.price.PriceNegativeException;

/**
 * Represents a product's price.
 */
public class Price {

	/**
	 * The value of the price.
	 */
	private final double value;

	/**
	 * Constructs a new Price object with the given value.
	 *
	 * @param value the price value
	 * @throws PriceNegativeException if the value is negative
	 */
	public Price(double value) {
		ensureIsNotNegative(value);
		this.value = value;
	}

	/**
	 * Ensures that the given value is not negative.
	 *
	 * @param value the value to ensure
	 * @throws PriceNegativeException if the value is negative
	 */
	private static void ensureIsNotNegative(double value) {
		if (value < 0) {
			throw new PriceNegativeException();
		}
	}

	/**
	 * Returns the value of the price.
	 *
	 * @return the price value
	 */
	public double getValue() {
		return value;
	}

}
