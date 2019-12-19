package edu.ncsu.csc216.packdoption.util;

import java.util.Collection;

/**
 * Implementation of the SortedList interface with a data structure of linked
 * Nodes
 * 
 * @author bmrowan
 * @author mlee25
 *
 * @param <E> generic type parameter
 */
public class SortedLinkedList<E extends Comparable<E>> implements SortedList<E> {

	/** the head node */
	private Node<E> head;

	/**
	 * Returns the number of elements in this list. If this list contains more than
	 * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		int size = 0;
		Node<E> current = head;
		while (current != null) {
			current = current.next;
			size++;
		}
		if (size > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;

		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element a such
	 * that (o==null ? a==null : o.equals(a)).
	 *
	 * @param e element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	@Override
	public boolean contains(E e) {
		Node<E> current = head;
		while (current != null) {
			if (current.value.equals(e)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Adds the specified element to list in sorted order
	 *
	 * @param e element to be appended to this list
	 * @return true (as specified by {@link Collection#add})
	 * @throws NullPointerException     if e is null
	 * @throws IllegalArgumentException if list already contains e
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (contains(e)) {
			throw new IllegalArgumentException();
		}
		Node<E> current = head;
		Node<E> previous = null;
		while (current != null && current.value.compareTo(e) < 0) {
			previous = current;
			current = current.next;
		}
		if (current == head) {
			head = new Node<E>(e, head);
		} else {
			previous.next = new Node<E>(e, current);
		}

		return true;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range (index less
	 *                                   than zero or index greater than or equal to
	 *                                   size)
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		int i = 0;
		Node<E> current = head;
		while (index != i) {
			current = current.next;
			i++;
		}
		return current.value;
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices). Returns
	 * the element that was removed from the list.
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range (index less
	 *                                   than zero or greater than or equal to size)
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = head;
		Node<E> previous = null;
		while (current != null && index > 0) {
			previous = current;
			current = current.next;
			index--;
		}
		if (current != null) {
			if (current == head) {
				head = head.next;
			} else {
				previous.next = current.next;
			}
			return current.value;
		}

		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
	 * -1 if there is no such index.
	 *
	 * @param e element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(E e) {
		int index = 0;
		SimpleListIterator<E> iter = this.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(e)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Returns an instance of Cursor.
	 * 
	 * @return the cursor
	 */
	public SimpleListIterator<E> iterator() {
		return new Cursor();
	}

//	/**
//	 * Returns a hash code based on head.
//	 * 
//	 * @return the hash code
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((head == null) ? 0 : head.hashCode());
//		return result;
//	}
//
//	/**
//	 * Compares if this is equal to another object and returns true if equal.
//	 * 
//	 * @return true if equal
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		SortedLinkedList<E> other = (SortedLinkedList<E>) obj;
//		if (head == null) {
//			if (other.head != null)
//				return false;
//		}
//		if (other.head == null) {
//			if (head != null) {
//				return false;
//			}
//		}
//		Node<E> current = head;
//		Node<E> otherCurrent = other.head;
//
//		while (current != null) {
//			if (!current.value.equals(otherCurrent.value)) {
//				return false;
//			}
//			current = current.next;
//			otherCurrent = otherCurrent.next;
//		}
////		if (head != null && !head.equals(other.head))
////			return false;
//		return true;
//	}

	/**
	 * Returns a string representation of the list in the format �-A\n-B\n�-X�.
	 * 
	 * @return the string
	 */
	public String toString() {
		String string = "";
		SimpleListIterator<E> iter = this.iterator();
		while (iter.hasNext()) {
			string += "-" + iter.next();
			if (iter.hasNext()) {
				string += "\n";
			}
		}
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof SortedLinkedList))
			return false;
		SortedLinkedList other = (SortedLinkedList) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}

	/**
	 * Private, static inner class of SortedLinkedList that contains an E and a
	 * reference to the next Node in the SortedLinkedList.
	 * 
	 * @author bmrowan
	 * @author mlee25
	 * 
	 * @param <E> generic type parameter
	 *
	 */
	private static class Node<E> {

		/** the node element */
		protected E value;
		/** the next node */
		private Node<E> next;

		/**
		 * Constructor.
		 * 
		 * @param value the node element
		 * @param next  the next node
		 */
		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Node))
				return false;
			Node<E> other = (Node<E>) obj;
			if (next == null) {
				if (other.next != null)
					return false;
			} else if (!next.equals(other.next))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

//		/**
//		 * Generates the hash code for the Node.
//		 * 
//		 * @return the hash code
//		 */
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
////			result = prime * result + getEnclosingInstance().hashCode();
//			result = prime * result + ((next == null) ? 0 : next.hashCode());
//			result = prime * result + ((value == null) ? 0 : value.hashCode());
//			return result;
//		}
//
//		/**
//		 * Compares if this Node is equal to another object.
//		 * 
//		 * @return true if equal
//		 */
//		@SuppressWarnings("unchecked")
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
////			if (obj == null)
////				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			Node<E> other = (Node<E>) obj;
////			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
////				return false;
//
////			if (next == null && other.next == null) {
////				return true;
////			}
////			if (next == null && other.next != null) {
////				return false;
////			}
//			if (!next.equals(other.next)) {
//				return false;
//			}
//			if (!value.equals(other.value)) {
//				return false;
//			}
//			if (value == null && other.value != null) {
//					return false;
//			}
//			if (value != null && !value.equals(other.value))
//				return false;
//			return true;
//		}
//

	}

	/**
	 * Private inner class of SortedLinkedList that provides a cursor for iterating
	 * forward through the list without changing the list.
	 * 
	 * @author bmrowan
	 * @author mlee25
	 *
	 */
	private class Cursor implements SimpleListIterator<E> {

		/** the current node */
		private Node<E> current;

		/**
		 * Constructor.
		 */
		public Cursor() {
			current = new Node<E>(null, head);
		}

		/**
		 * Returns true if there is a next node in the list.
		 * 
		 * @return true if there is a next node
		 */
		public boolean hasNext() {
			return current.next != null;
		}

		/**
		 * Returns the next node and travels to the following node.
		 * 
		 * @return the next node's data
		 */
		public E next() {
			E value = null;
			if (!hasNext()) {
				throw new NoSuchListElementException("No element available with call to next.");
			}
			if (current.next.value != null) {
				value = current.next.value;
			}
			current.next = current.next.next;
			return value;
		}

	}

}
