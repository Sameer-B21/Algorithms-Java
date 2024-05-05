package a4;

import java.util.Arrays;
import java.util.Objects;

/**
 * A class that represents real valued univariate polynomials of degree n where
 * n >= 0.
 * 
 * <p>
 * This class does not provide a mathematically correct representation of the
 * zero polynomial. Mathematically, the zero polynomial is equal to the numeric
 * value zero, and its degree is either undefined or defined to be some negative
 * value depending on usage. This class represents the zero polynomial as a
 * polynomial of degree zero (i.e., there is exactly one coefficient).
 * 
 */
public class Polynomial {

	private double[] a; // length == degree + 1

	/**
	 * Initializes this polynomial so that it has the specified coefficients. The
	 * degree of the polynomial is equal to the number of coefficients minus 1.
	 * 
	 * <p>
	 * The element at index {@code i} of {@code coeffs} is equal to the coefficient
	 * for the term x raised to the power {@code i} in the polynomial. The last
	 * element of {@code coeffs} must not be equal to zero unless there is only one
	 * coefficient.
	 * 
	 * @param coeffs an array of coefficients
	 * @throws IllegalArgumentException if the number of coefficients is equal to
	 *                                  zero
	 * @throws IllegalArgumentException if the last element of {@code coeffs} is
	 *                                  equal to zero and {@code coeffs.length > 1}
	 */
	public Polynomial(double... coeffs) {
		if (coeffs.length == 0 || (coeffs[coeffs.length - 1] == 0 && coeffs.length > 1)) {
			throw new IllegalArgumentException();
		}
		this.a = Arrays.copyOf(coeffs, coeffs.length);
	}

	/**
	 * Initializes this polynomial by copying the coefficients of another
	 * polynomial.
	 * 
	 * <p>
	 * After this constructor completes, the coefficients of both polynomials may be
	 * changed without affecting the other polynomial.
	 * 
	 * @param other the polynomial to copy
	 */
	public Polynomial(Polynomial other) {
		this.a = Arrays.copyOf(other.a, other.a.length);
	}

	/**
	 * Returns the degree of this polynomial.
	 * 
	 * @return the degree of this polynomial
	 */
	public int degree() {
		return a.length - 1;
	}

	/**
	 * Returns the zero polynomial. The returned polynomial has exactly one
	 * coefficient, and the coefficient is equal to zero.
	 * 
	 * @return the zero polynomial
	 */
	public static Polynomial zero() {
		return new Polynomial(0);
	}

	/**
	 * Returns an array containing the coefficients of this polynomial. Modifying
	 * the returned array does not modify the coefficients of this polynomial.
	 * 
	 * @return an array containing the coefficients of this polynomial
	 */
	public double[] getCoeffs() {
		return Arrays.copyOf(a, a.length);
	}

	/**
	 * Returns the coefficient for the term x raised to the power {@code i} in this
	 * polynomial.
	 * 
	 * @param i the coefficient to get
	 * @return the coefficient for the term x raised to the power {@code i} in this
	 *         polynomial
	 * @throws IllegalArgumentException if i is not a valid index
	 */
	public double getCoeffAt(int i) {
		if (i < a.length - 1) {
			throw new IllegalArgumentException();
		}
		return a[i];
	}

	/**
	 * Sets the coefficients of this polynomial to the values in {@code coeffs}. The
	 * degree of this polynomial changes if the length of {@code coeffs} is not
	 * equal to the current number of coefficients of this polynomial when the
	 * method is called.
	 * 
	 * <p>
	 * The degree of the polynomial is equal to the length of {@code coeffs} minus
	 * 1.
	 * 
	 * <p>
	 * The element at index {@code i} of {@code coeffs} is equal to the coefficient
	 * for the term x raised to the power {@code i} in the polynomial. The last
	 * element of {@code coeffs} must not be equal to zero unless there is exactly
	 * one coefficient.
	 * 
	 * <p>
	 * After the method returns, the caller may modify {@code coeffs} without
	 * affecting this polynomial.
	 * 
	 * @param coeffs an array of coefficients
	 * @throws IllegalArgumentException if the degree of the polynomial is less than
	 *                                  zero
	 * @throws IllegalArgumentException if the last element of {@code coeffs} is
	 *                                  equal to zero and {@code coeffs.length > 1}
	 */
	public void setCoeffs(double[] coeffs) {
		if (coeffs.length == 0 || (coeffs[coeffs.length - 1] == 0 && coeffs.length > 1)) {
			throw new IllegalArgumentException();
		}
		this.a = Arrays.copyOf(coeffs, coeffs.length);
	}

	/**
	 * Sets the coefficient for the term x raised to the power {@code i} in this
	 * polynomial.
	 * 
	 * @param i  the coefficient to set
	 * @param ai the value to set the coefficient to
	 * @throws IllegalArgumentException if i is not a valid index
	 */
	public void setCoeffAt(int i, double ai) {
		if (i > a.length) {
			throw new IllegalArgumentException();
		}
		this.a[i] = ai;
	}

	/**
	 * Evaluates this polynomial at the specified {@code x} value.
	 * 
	 * @param x the value to evaluate this polynomial at
	 * @return the value of {@code f(x)} where {@code f} is the function
	 *         corresponding to this polynomial
	 */
	public double at(double x) {
		double sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += Math.pow(x, i);
		}
		return sum;
	}

	/**
	 * Returns the derivative of this polynomial as a new {@code Polynomial} object.
	 * 
	 * <p>
	 * If the degree of this polynomial is greater than zero, then the degree of the
	 * returned polynomial is one less than the degree of this polynomial.
	 * 
	 * <p>
	 * If the degree of this polynomial is equal to zero (i.e., this polynomial is
	 * equal to a constant), then the zero polynomial is returned.
	 * 
	 * @return the polynomial equal to the derivative of this polynomial
	 */
	public Polynomial derivative() {
		if (this.degree() == 0) {
			return Polynomial.zero();
		}
		double arr[] = new double[degree()];
		for (int i = 0; i < this.degree(); i++) {
			arr[i] = a[i + 1] * (i + 1);
		}
		return new Polynomial(arr);
	}

	/**
	 * Returns the definite integral of this polynomial evaluated from {@code x1} to
	 * {@code x2}.
	 * 
	 * <p>
	 * If {@code x1 > x2} is {@code true}, then the values of {@code x1} and
	 * {@code x2} are swapped before computing the definite integral.
	 * 
	 * @param x1 a limit of the definite integral
	 * @param x2 the other limit of the definite integral
	 * @return the definite integral evaluated at the specified limits
	 */
	public double integrate(double x1, double x2) {
		if (x1 > x2) {
			double temp = x2;
			x2 = x1;
			x1 = temp;
		}
		double integral = 0;
		for (int i = 0; i < this.degree() + 1; i++) {
			integral += (a[i] * Math.pow(x2, i + 1) / (i + 1)) - (a[i] * Math.pow(x1, i + 1) / (i + 1));
		}
		return integral;
	}

	/**
	 * Returns a hash code for this polynomial. The hash code is computed using the
	 * coefficients of this polynomial.
	 * 
	 * @return a hash code for this polynomial
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.a);
	}

	/**
	 * Compares this polynomial to an object for equality. {@code obj} is equal to
	 * this polynomial if and only if {@code obj} is a reference to a
	 * {@code Polynomial} object, {@code obj} has the same degree as this
	 * polynomial, and the coefficients of {@code obj} are equal to the coefficients
	 * of this polynomial.
	 * 
	 * @param obj an object to compare
	 * @return {@code true} if {@code obj} is equal to this polynomial,
	 *         {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Polynomial other = (Polynomial) obj;
		if (!(this.a).equals(other.a)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a string representation of this polynomial. See the assignment
	 * document for the format of the returned string.
	 * 
	 * @return a string representation of this polynomial
	 */
	@Override
	public String toString() {
		String s = "" + a[0];
		for (int i = 1; i < a.length; i++) {
			s += " + " + a[i] + " " + "x**" + i;
		}
		return s;
	}

	/**
	 * A simple main method that uses some of the constructors and methods.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Polynomial p = new Polynomial(new double[] { 1.0, 2.0, 5.0 });

		System.out.println("Polynomial:");
		System.out.println(p);

		System.out.println();
		System.out.println("Coefficients:");
		double[] coeffs = p.getCoeffs();
		System.out.println(Arrays.toString(coeffs));

		System.out.println();
		System.out.println("Coefficient for x**2:");
		double c = p.getCoeffAt(2);
		System.out.println(c);

		System.out.println();
		System.out.println("Change coefficient for x**1 to -3.0:");
		p.setCoeffAt(1, -3.0);
		System.out.println(p);

		System.out.println();
		System.out.println("Derivative:");
		Polynomial dy = p.derivative();
		System.out.println(dy);

		System.out.println();
		System.out.println("Definite integral from -1 to 1:");
		double area = p.integrate(-1.0, 1.0);
		System.out.println(area);
	}
}
