package a2;

/**
 * A floating-point value that is guaranteed to lie within a fixed interval. The
 * interval of values that the value can take cannot be modified after a
 * {@code RangedValue} object is created, but the value can be modified.
 *
 */
public class RangedValue {


	private Interval inter;
	private double val;

	/**
	 * Initializes this ranged value to the specified value in the specified
	 * interval.
	 */
	public RangedValue(double min, double max, double value) {
		if (Double.isNaN(min) || Double.isNaN(max) || min > max || value < min || value > max) {
			throw new IllegalArgumentException();
		}
		else {
			inter = new Interval (min, max);
			val = value;
		}
	}

	/**
	 * Initializes this ranged value by copying another ranged value so that both
	 * ranged values have equal values and intervals.
	 */
	public RangedValue(RangedValue other) {
		val = other.value();
		inter = new Interval(other.interval());
	}

	/**
	 * Returns the minimum value that this value can have.
	 */
	public double min() {
		return inter.min();
	}

	/**
	 * Returns the maximum value that this value can have.
	 */
	public double max() {
		return inter.max();
	}

	/**
	 * Returns an {@code Interval} object representing the interval of values that
	 * this object can have. Changes in the returned {@code Interval} object are not
	 * reflected in the interval of this object.
	 */
	public Interval interval() {
		return inter;
	}

	/**
	 * Returns the value of this object.
	 */
	public double value() {
		return val;
	}

	/**
	 * Sets the value of this object to the specified value. The value must lie
	 * within the interval of this object.
	 */
	public void value(double value) {
		if (inter.contains(value)) {
			val = value;
		} else {
			throw new IllegalArgumentException("Value out of interval bounds.");
		}
	}

	@Override
	public String toString() {
		return ("[" + inter.min() + " : " + val + " : " + inter.max() + "]");
	}

}
