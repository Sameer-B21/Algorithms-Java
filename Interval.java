package a2;

/**
 * An interval of floating-point values. An interval has a non-NaN minimum value
 * and a non-NaN maximum where the minimum value is greater than or equal to the
 * maximum value.
 * 
 * <p>
 * For intervals that do no include {@code Double.NEGATIVE_INFINITY} or
 * {@code Double.NEGATIVE_INFINITY}, the width of the interval is equal to the
 * maximum value minus the minimum value, but this value is not always
 * representable as a {@code double} value. Zero-width intervals are possible
 * when the minimum and maximum values are finite and equal.
 * 
 * <p>
 * A value is defined to be inside the interval if the value is greater than or
 * equal the minimum value of the interval and less than or equal to the maximum
 * value of the interval.
 */
public class Interval {


	private double a; //min value
	private double b; //max value
 
	/**
	 * Initializes this interval to have the specified minimum and maximum values.
	 */
	public Interval(double min, double max) {
		if (Double.isNaN(min) || Double.isNaN(max) || min > max) {
			throw new IllegalArgumentException(
					"Parameters must be 'double' values!!! or the minimum greater than the maximum value of teh interval.");
		}
		else {
			a = min;
			b = max;
		}
	}

	/**
	 * Initializes this interval by copying the minimum and maximum values of
	 * another interval.
	 */
	public Interval(Interval other) {
		a = other.min();
		b = other.max();
	}

	/**
	 * Returns the minimum value of this interval.
	 */
	public double min() {
		return a;
	}

	/**
	 * Returns the maximum value of this interval.
	 */
	public double max() {
		return b;
	}

	/**
	 * Returns the width of this interval (defined to be equal to
	 * {@code this.max() - this.min()}). {@code Double.POSITIVE_INIFINITY} is
	 * returned if the the width cannot be computed as a {@code double} value.
	 */
	public double width() {
		if (b-a>Double.POSITIVE_INFINITY)
			return (Double.POSITIVE_INFINITY);
		return (b - a);
	}

	/**
	 * Sets the minimum value of this interval to the specified value.
	 */
	public void min(double min) {
		if (Double.isNaN(min) || min > b) {
			throw new IllegalArgumentException();
		} else {
			a = min;
		}
	}

	/**
	 * Sets the maximum value of this interval to the specified value.
	 */
	public void max(double max) {
		if (Double.isNaN(max) || max < a) {
			throw new IllegalArgumentException();
		} else {
			b = max;
		}
	}

	/**
	 * Moves the minimum and maximum value of this interval by the specified amount
	 * (positive values of {@code delta} increase the bounds of the interval by
	 * {@code delta}, and negative values of {@code delta} decrease the bounds of
	 * the interval by {@code delta}).
	 */
	public void moveBy(double delta) {
		if (Double.isNaN(delta)) {
			throw new IllegalArgumentException();
		} else {
			a = a + delta;
			b = b + delta;
		}
	}

	/**
	 * Returns {@code true} if the specified value is inside of this interval. The
	 * minimum and maximum values of the interval are considered to be inside of the
	 * interval. The value NaN is never inside of an interval.
	 */
	public boolean contains(double val) {
		return ((a <= val) && (val <= b));
	}

	@Override
	public String toString() {
		return ("[" + Double.toString(a) + ", " + Double.toString(b) + "]");
	}
}
