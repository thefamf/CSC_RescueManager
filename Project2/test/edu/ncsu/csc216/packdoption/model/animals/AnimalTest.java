/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test the animal class
 * 
 * @author BrendanRowan
 *
 */
public class AnimalTest {
	/** name */
	String name = "name";
	/** house trained */
	boolean houseTrained = true;
	/** Animal is good with kids or not */
	boolean goodWithKids = true;
	/** Animal is adopted or not */
	boolean adopted = true;
	/** Animal's owner */
	String owner = " owner ";
	/** Animals birthday */
	Date birthday = new Date(1, 1, 2010);
	/** Date the animal entered the rescue */
	Date dateEnterRescue = new Date(12, 31, 2018);
	/** Date the animal was adopted */
	Date dateAdopted = new Date(6, 30, 2019);
	/** Size of the animal */
	Size size = Size.LARGE;
	/** List of notes */
	SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
	/** today */
	Date today = new Date(12, 31, 2019);
	/** String array */
	String[] array = new String[7];
	/** note */
	Note note = new Note(new Date(11, 30, 2018), "a note");

	/**
	 * Set up for testing.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		notes.add(note);
	}

	/**
	 * Tests addNote()
	 */
	@Test
	public void testAddNote() {
		Animal cat = new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted,
				dateAdopted, owner);
		assertEquals(1, cat.getNotes().size());

		Note newNote = new Note(new Date(1, 1, 2019), "another note");
		cat.addNote(newNote);
		assertEquals(2, cat.getNotes().size());
		// TODO SortedLinkedList.toString() 
//		assertEquals("11/30/2018 a note\1/1/2019 another note", cat.getNotes().toString());
	}

}
