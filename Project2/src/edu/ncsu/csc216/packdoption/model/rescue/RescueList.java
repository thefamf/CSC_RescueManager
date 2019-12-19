package edu.ncsu.csc216.packdoption.model.rescue;

import java.util.Scanner;

import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Class to manage a SortedLinkedList of rescues.
 * 
 * @author mlee25
 *
 */
public class RescueList {

	/** sorted linked list of rescues */
	private SortedLinkedList<Rescue> rescues;

	/**
	 * Constuctor.
	 */
	public RescueList() {
		rescues = new SortedLinkedList<Rescue>();
	}

	/**
	 * Adds r to list of rescues.
	 * 
	 * @param r the rescue to add
	 * @throws IllegalArgumentException if r is null or r is already in the list
	 */
	public void addRescue(Rescue r) {
		if (r == null) {
			throw new IllegalArgumentException();
		}
		if (rescues.contains(r)) {
			throw new IllegalArgumentException();
		}
		
		rescues.add(r);
		
	}

	/**
	 * Adds a rescue with name to list of rescues.
	 * 
	 * @param name the name of the rescue
	 * @throws IllegalArgumentException if name is null, whitespace only, contains
	 *                                  \n or is already in the list
	 */
	public void addRescue(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		Scanner scan = new Scanner(name);
		scan.nextLine();
		if (scan.hasNextLine()) {
			scan.close();
			throw new IllegalArgumentException();
		}
		scan.close();
		if (rescues.contains(new Rescue(name))) {
			throw new IllegalArgumentException();
		}
		
		rescues.add(new Rescue(name));
		
		
	}

	/**
	 * Returns the rescue at the given index.
	 * 
	 * @param index the index
	 * @return the rescue
	 * @throws IndexOutOfBoundsException if index is negative or greater than size-1
	 */
	public Rescue getRescue(int index) {
		if (index < 0 || index >= rescues.size()) {
			throw new IndexOutOfBoundsException();
		}
		return rescues.get(index);
	}

	/**
	 * Returns the size of the RescueList.
	 * 
	 * @return the size
	 */
	public int size() {
		return rescues.size();
	}
}
