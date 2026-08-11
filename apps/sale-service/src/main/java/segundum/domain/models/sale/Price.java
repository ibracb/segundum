package segundum.domain.models.sale;

import segundum.domain.exceptions.sale.price.PriceNegativeException;

/**
 * Represents the price of a sale.
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
	 * @return the value of the price
	 */
    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Double.compare(price.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

}
