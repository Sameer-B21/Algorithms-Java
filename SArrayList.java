package a6;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based implementation of a {@code SList}.
 *
 * @param <E> the type of elements in this list
 */
public class SArrayList<E> implements SList<E> {

	private final int DEFAULT_CAPACITY = 16;
	private Object[] arr;
	private int size;

	/**
	 * Initializes an empty list.
	 */
	public SArrayList() {
		this.arr = new Object[DEFAULT_CAPACITY];
		this.size = 0;
	}

	/**
	 * Initializes this list to have the specified elements.
	 * 
	 * @param elems a comma separated list of elements, or an array of elements
	 */
	@SafeVarargs
	public SArrayList(E... elems) {
		this.arr = new Object[elems.length];
		this.size = elems.length;
		for (int i = 0; i < elems.length; i++) {
			this.arr[i] = elems[i];
		}
	}

	/**
	 * Get the number of elements in the list.
	 * 
	 * @return the number of elements in the list.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Adds the given element to the end of the list.
	 * 
	 * @param elem the element to add
	 */
	@Override
	public void add(E elem) {
		// do we need to resize the array?
		if (this.size() == this.arr.length) {
			this.arr = Arrays.copyOf(this.arr, this.arr.length * 2);
		}
		this.arr[this.size] = elem;
		this.size++;
	}

	/**
	 * Throws an {@code IndexOutOfBoundsException} if index is less than 0 or
	 * greater than {@code this.size - 1}.
	 * 
	 * @param index an index to validate
	 * @throws {@code IndexOutOfBoundsException} if index is less than 0 or greater
	 * than {@code this.size - 1}
	 */
	private void checkIndex(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("negative index: " + index);
		} else if (index >= this.size) {
			throw new IndexOutOfBoundsException("index out of bounds: " + index + ", size: " + this.size);
		}
	}

	/**
	 * Returns the element at the specified position in the list.
	 * 
	 * @param index index of the element to return
	 * @return the element at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index >= size())}
	 */
	@Override
	public E get(int index) {
		this.checkIndex(index);
		return (E) this.arr[index];
	}

	/**
	 * Sets the element at the specified position in the list.
	 * 
	 * @param index index of the element to set
	 * @param elem  element to be stored at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index >= size())}
	 */

	@Override
	public E set(int index, E elem) {
		// get element at index, this also checks the index
		E old = this.get(index);
		this.arr[index] = elem;
		return old;
	}

	/**
	 * Compares this list with another list for equality. This list is equal to
	 * another {@code SList} if they have the same size and if each element in this
	 * list is equal to the corresponding element in the other list.
	 *
	 * @param obj the object to compare with this list
	 * @return true if this list is equal to the specified list
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SList<?>)) {
			return false;
		}
		SList<?> other = (SList<?>) obj;
		if (this.size() != other.size()) {
			return false;
		}
		Iterator<?> i1 = this.iterator();
		Iterator<?> i2 = other.iterator();
		while (i1.hasNext()) {
			Object o1 = i1.next();
			Object o2 = i2.next();
			if (!(o1.equals(o2))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a string representation of this list. The returned string has the
	 * same format as the string returned by the Standard Library list types.
	 * 
	 * @return a string representation of this list
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("[");
		if (!this.isEmpty()) {
			b.append(this.get(0));
			for (int i = 1; i < this.size; i++) {
				b.append(", ");
				b.append(this.get(i));
			}
		}
		b.append("]");
		return b.toString();
	}

	/**
	 * Returns an iterator over the elements in this list. The iterator visits the
	 * elements of this list in the order that the elements appear in this list.
	 * 
	 * @return an iterator over the elements in this list
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<E> {
		/**
		 * Index of element to be returned by subsequent call to next.
		 */
		private int next;

		/**
		 * Index of element returned by most recent call to next. Reset to -1 if this
		 * element is deleted by a call to remove.
		 */
		private int prev;

		ArrayIterator() {
			this.next = 0;
			this.prev = -1;
		}

		@Override
		public boolean hasNext() {
			return this.next < SArrayList.this.size;
		}

		@Override
		public E next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			E e = SArrayList.this.get(this.next);
			this.prev = this.next;
			this.next++;
			return e;
		}

	}

	/**
	 * Initializes this list by copying the elements from another list. The other
	 * list is not modified.
	 * 
	 * @param other the list to copy
	 */
	public SArrayList(SList<E> other) {
		new SArrayList();
		for (Iterator<E> iter = other.iterator(); iter.hasNext();) {
			this.add(iter.next());
		}
	}

	/**
	 * Reverses the order of the elements in this list.
	 * 
	 * <p>
	 * Runs in O(n) time taking only a single pass of the array.
	 */
	@Override
	public void reverse() {
		for (int i = 0; i < (this.size) / 2; i++) {
			int j = this.size - 1 - i;
			E tmp = this.get(j);
			this.set(j, this.get(i));
			this.set(i, tmp);
		}
	}

	/**
	 * Splits this list into two separate lists at the specified {@code index}.
	 * After splitting, the returned list contains the elements from the start of
	 * this list up to the element immediately before the specified {@code index}.
	 * The elements from the specified {@code index} to the end of the original list
	 * remain in this list.
	 * 
	 * <p>
	 * Runs in O(n) time taking only a single pass of the array.
	 * 
	 * @param index the index at which to split this list
	 * @return a new list containing the elements from the start of this list up to
	 *         the element immediately before the specified {@code index}
	 * @throws IndexOutOfBoundsException if {@code index} is less than zero or
	 *                                   greater than the size of this list
	 */
	@Override
	public SArrayList<E> splitback(int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException();
		}
		SArrayList<E> first = new SArrayList();
		for (int i = 0; i < index; i++) {
			first.add(this.get(i));
		}
		for (int i = 0; i < this.size - index; i++) {
			this.arr[i] = this.arr[i + index];
		}
		this.size = this.size - index;
		return first;
	}

	/**
	 * Rotates the elements of this list {@code m} positions to the right. This list
	 * is transformed so that the original {@code m} elements at the end of the list
	 * become the first {@code m} elements at the front of the list.
	 * 
	 * @param m the number of positions to rotate the elements of the list
	 * @throws IllegalArgumentException if m is less than zero
	 * @throws IllegalArgumentException if m is greater than the size of this list
	 */
	public void rotate(int m) {
		if (m < 0 || m > this.size) {
			throw new IllegalArgumentException();
		}
		SArrayList<E> tmp = this.splitback(this.size - m);
		for (int i = 0; i < tmp.size; i++) {
			this.set(i + m, tmp.get(i));
		}
	}

	/**
	 * For debugging purposes if desired.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// FOR DEBUGGING IF DESIRED
	}
}
