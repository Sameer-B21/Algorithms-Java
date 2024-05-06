package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * A linked node-based implementation of a {@code SList}.
 *
 * @param <E> the type of elements in this list
 */
public class SLinkedList<E> implements SList<E> {

	static class Node<E> {
		E elem;
		Node<E> next;

		/**
		 * Initializes a node to refer to the specified element and node.
		 * 
		 * @param c a character
		 */
		public Node(E elem, Node<E> node) {
			this.elem = elem;
			this.next = node;
		}
	}

	/**
	 * The number of elements in the linked list.
	 */
	private int size;

	/**
	 * The first node of the linked list; will be <code>null</code> for an empty
	 * list.
	 */
	private Node<E> head;

	/**
	 * The last node of the linked list; will be <code>null</code> for an empty
	 * list.
	 */
	private Node<E> tail;

	/**
	 * Returns the head node of this list.
	 * 
	 * @return the head node of this list
	 */
	Node<E> head() {
		return this.head;
	}

	/**
	 * Returns the tail node of this list.
	 * 
	 * @return the tail node of this list
	 */
	Node<E> tail() {
		return this.tail;
	}

	/**
	 * Initialize an empty list.
	 */
	public SLinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	/**
	 * Initializes this list to have the specified elements.
	 * 
	 * @param elems a comma separated list of elements, or an array of elements
	 */
	@SafeVarargs
	public SLinkedList(E... elems) {
		this.size = 0;
		this.head = null;
		this.tail = null;
		for (int i = 0; i < elems.length; i++) {
			this.add(elems[i]);
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
		if (this.size == 0) {
			this.head = new Node<>(elem, null);
			this.tail = this.head;
		} else {
			Node<E> n = new Node<>(elem, null);
			this.tail.next = n;
			this.tail = n;
		}
		this.size++;
	}

	/**
	 * Validates the specified index.
	 * 
	 * @param index an index
	 * @throws IndexOutOfBoundsException if
	 *                                   {@code index < 0 || index >= this.size()}
	 */
	void validate(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("index out of bounds: " + index);
		}
	}

	/**
	 * Returns the node at the specified index. Assumes that the index is valid for
	 * this list to avoid re-validating the index.
	 * 
	 * @param index a valid index for this list
	 * @return the node at the specified index
	 */
	Node<E> moveTo(int index) {
		Node<E> n = this.head;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		return n;
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
		this.validate(index);
		Node<E> n = this.moveTo(index);
		return n.elem;
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
		this.validate(index);
		Node<E> n = this.moveTo(index);
		E old = n.elem;
		n.elem = elem;
		return old;
	}

	/**
	 * Adds an element to the front of this list.
	 * 
	 * @param elem the element to add
	 */
	public void addFront(E elem) {
		Node<E> toAdd = new Node<>(elem, null);
		toAdd.next = this.head;
		this.head = toAdd;
		this.size++;
	}

	/**
	 * Removes the first element of this list and returns the element.
	 * 
	 * @return the removed element
	 * @throws NoSuchElementException if the list is empty
	 */
	public E removeFront() {
		if (this.size == 0) {
			throw new NoSuchElementException("list is empty");
		}
		Node<E> toRemove = this.head;
		this.head = toRemove.next;
		// special case of removing from a list of size 1
		if (this.size == 0) {
			this.tail = null;
		}
		this.size--;
		return toRemove.elem;
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

		StringJoiner j = new StringJoiner(", ", "[", "]");
		Node<E> n = this.head;
		for (int i = 0; i < this.size; i++) {
			j.add(n.elem.toString());
			n = n.next;
		}
		return j.toString();
	}

	/**
	 * Returns an iterator over the elements in this list. The iterator visits the
	 * elements of this list in the order that the elements appear in this list.
	 * 
	 * @return an iterator over the elements in this list
	 */
	@Override
	public Iterator<E> iterator() {
		return new LLIterator();
	}

	private class LLIterator implements Iterator<E> {
		/**
		 * Node holding element immediately before the iterator
		 */
		private Node<E> prev;

		/**
		 * Node immediately before prev
		 */
		private Node<E> prevPrev;

		LLIterator() {
			this.prev = new Node<>(null, SLinkedList.this.head);
			this.prevPrev = null;
		}

		@Override
		public boolean hasNext() {
			return this.prev.next != null;
		}

		@Override
		public E next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			E e = this.prev.next.elem;
			this.prevPrev = this.prev;
			this.prev = this.prev.next;
			return e;
		}

	}

	/**
	 * Initializes this list by copying the elements from another list. The other
	 * list is not modified.
	 * 
	 * @param other the list to copy
	 */
	public SLinkedList(SList<E> other) {
		new SLinkedList();
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
	public void reverse() {
		Node<E> m = null;
		Node<E> n = this.head;
		Node<E> o = this.head.next;
		while (o != null) {
			n.next = m;
			m = n;
			n = o;
			o = o.next;
		}
		n.next = m;
		this.tail = this.head;
		this.head = n;
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
	public SLinkedList<E> splitback(int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException();
		}
		SLinkedList<E> beg = new SLinkedList();
		if (index == this.size) {
			beg.head = this.head;
			beg.tail = this.tail;
			beg.size = this.size;
			this.head = null;
			this.tail = null;
			this.size = 0;
			return beg;
		}
		if (index == 0) {
			beg.size = 0;
			beg.head = null;
			beg.tail = null;
			return beg;
		}
		beg.head = this.head;
		this.head = this.moveTo(index);
		beg.tail = this.moveTo(index - 1);
		beg.tail.next = null;
		this.size = this.size - index;
		beg.size = index;
		return beg;
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
		Node<E> n = this.moveTo(this.size - 1 - m);
		this.tail.next = this.head;
		this.head = n.next;
		n.next = null;
		this.tail = n;
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
