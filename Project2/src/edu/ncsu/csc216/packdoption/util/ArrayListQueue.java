package edu.ncsu.csc216.packdoption.util;


/**
 * Implements the Queue interface with an array data structure.
 * 
 * @author bmrowan
 * @author mlee25
 *
 * @param <E> generic type parameter
 */
public class ArrayListQueue<E> implements Queue<E> {

	/** the array list queue */
	private E[] list;
	/** the size */
	private int size;

	/**
	 * Constructor.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ArrayListQueue() {
	    list = (E[]) new Object[100];
	    size = 0; 
	}

	/**
	 * Inserts the specified element into this queue if it is possible to do so
	 * immediately without violating capacity restrictions, returning true upon
	 * success.
	 * 
	 * @param e element to add to queue
	 * @return true if add is successful
	 * @throws NullPointerException if the specified element is null and this queue
	 *                              does not permit null elements
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		try {
			list[size] = e;
			size++;
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Retrieves and removes the head of this queue. This method differs from poll
	 * only in that it throws an exception if this queue is empty.
	 * 
	 * @return the head of this queue
	 * @throws NoSuchListElementException if this queue is empty
	 */
	@Override
	public E remove() {
		if (size == 0) {
			throw new NoSuchListElementException();
		}
		E result = list[0];
		for (int i = 0; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		size--;
		return result;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue. Throws an exception
	 * if this queue is empty.
	 * 
	 * @return the head of this queue
	 * @throws NoSuchListElementException if this queue is empty
	 */
	@Override
	public E element() {
		if (size == 0) {
			throw new NoSuchListElementException();
		}
		return list[0];
	}

	/**
	 * Returns the number of elements in this collection.
	 * 
	 * @return the number of elements in this collection
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * 
	 * @return true if this collection contains no elements
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

}
