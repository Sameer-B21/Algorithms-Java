package a6;

import java.util.Iterator;

/**
 * A simplified list interface. A list represents a finite collection of
 * elements held in a linear sequence.
 * 
 * @param <E> the type of elements in this list
 */
public interface SList<E> extends Iterable<E> {

	/**
	 * Get the number of elements in the list.
	 * 
	 * @return the number of elements in the list.
	 */
	public int size();

	/**
	 * Returns {@code true} if this list contains no elements, {@code false}
	 * otherwise.
	 * 
	 * @return {@code true} if this list contains no elements, {@code false}
	 *         otherwise
	 */
	default public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Adds the given element to the end of the list.
	 * 
	 * @param elem the element to add
	 */
	public void add(E elem);

	/**
	 * Returns the element at the specified position in the list.
	 * 
	 * @param index index of the element to return
	 * @return the element at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index >= size())}
	 */
	public E get(int index);

	/**
	 * Sets the element at the specified position in the list.
	 * 
	 * @param index index of the element to set
	 * @param elem  element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index >= size())}
	 */
	public E set(int index, E elem);

	/**
	 * Inserts an element at the specified index of this list. Shifts the element
	 * currently at that position (if any) and any subsequent elements to the right.
	 * 
	 * <p>
	 * For the purposes of the current assignment, this method has been removed from
	 * the list implementations.
	 * 
	 * @param index the index at which to insert the element
	 * @param elem  the element to be inserted
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index > size())}
	 * @throws UnsupportedOperationException for this assignment
	 */
	default public void add(int index, E elem) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes the element at the specified index of this list, shifts any
	 * subsequent elements to the left (subtracts one to their indices), and returns
	 * a reference to the removed element.
	 * 
	 * <p>
	 * For the purposes of the current assignment, this method has been removed from
	 * the list implementations.
	 * 
	 * @param index the index of the element to remove
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is out of the range
	 *                                   {@code (index < 0 || index >= size())}
	 * @throws UnsupportedOperationException for this assignment
	 */
	default public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	
	/**
	 * Reverses the order of the elements in this list.
	 */
	public void reverse();
	

	/**
	 * Splits this list into two separate lists at the specified {@code index}.
	 * After splitting, the returned list contains the elements from the start of this list
	 * up to the element immediately before the specified {@code index}. The
	 * elements from the specified {@code index} to the end of the original list remain
	 * in this list.
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
	public SList<E> splitback(int index);
	
	
	
	/**
	 * Rotates the elements of this list {@code m} positions to the right.
	 * This list is transformed so that the original {@code m} elements at
	 * the end of the list become the first {@code m} elements at the front of the list.
	 * 
	 * @param m the number of positions to rotate the elements of the list
	 * @throws IllegalArgumentException if m is less than zero
	 * @throws IllegalArgumentException if m is greater than the size of this list
	 */
	public void rotate(int m);
	
	

	/**
	 * Returns an iterator over the elements in this list. The iterator visits the
	 * elements of this list in the order that the elements appear in this list.
	 * 
	 * @return an iterator over the elements in this list
	 */
	public Iterator<E> iterator();
	
	
}
