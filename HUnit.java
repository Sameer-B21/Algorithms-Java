package a2;

/**
 * A class that represents a Hounsfield unit. Hounsfield units are the units of
 * measurement used in computed tomography (CT or CAT) scanning.
 * 
 * <p>
 * The Hounsfield scale is defined by specifying the radiodensity of air as
 * {@code -1000} Hounsfield units and the radiodensity of distilled water as
 * {@code 0} Hounsfield units. Adjacent tissues in the human body can be
 * distinguished from one another if their radiodensities differ; see
 * <a href="https://en.wikipedia.org/wiki/Hounsfield_scale">the Wikipedia
 * page</a> for a table of typical Hounsfield values for tissues of the human
 * body.
 * 
 * <p>
 * CT scanners for medical purposes typically restrict the value of reported
 * Hounsfield units to integers in the interval {@code -1024} to {@code 3071} so
 * that a Hounsfield unit can be encoded as a 12-bit value. This class uses the
 * values {@code -1024} and {@code 3071} to represent the minimum and maximum,
 * respectively, allowable Hounsfield unit values.
 *
 */
public class HUnit {

	public int MIN_VALUE = -1024;
	public int MAX_VALUE = 3071;
	private RangedValue rng = new RangedValue(MIN_VALUE, MAX_VALUE, 0);

	public HUnit() {
		rng.value(0);
	}

	/**
	 * Initializes this Hounsfield unit to have the specified value.
	 */
	public HUnit(int value) {
		if (value < MIN_VALUE || value > MAX_VALUE) {
			throw new IllegalArgumentException("Value out of bounds of the Interval");
		} else {
			rng.value(value);
		}

	}

	/**
	 * Initializes this Hounsfield unit by copying the value from the specified
	 * other Hounsfield unit.
	 */
	public HUnit(HUnit other) {
		rng.value(other.value());
	}

	/**
	 * Returns the value of this Hounsfield unit.
	 */
	public int value() {
		return ((int) rng.value());
	}

	/**
	 * Sets the value of this Hounsfield unit to the specified value returning the
	 * value that was overwritten.
	 */
	public int value(int value) {
		if (value < MIN_VALUE || value > MAX_VALUE) {
			throw new IllegalArgumentException("Value out of bounds of the Interval");
		} else {
			int ov = (int)rng.value(); 
			rng.value(value);
			return (ov);
		}
	}

	/**
	 * Returns a string representation of this Hounsfield unit. The returned string
	 * is the numeric value of this Hounsfield unit (formatted as an integer) inside
	 * of a matched pair of braces {}.
	 */
	@Override
	public String toString() {
		return ("{" + rng + "}");
	}
}
