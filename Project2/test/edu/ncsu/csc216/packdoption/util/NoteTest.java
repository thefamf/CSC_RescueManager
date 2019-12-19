/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests Note
 * 
 * @author BrendanRowan
 *
 */
public class NoteTest {

	/** test note */
	Note note;
	/** test date */
	Date date;
	/** test note */
	String message;
	
	/**
	 * Set up for testing.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		date = new Date(1, 1, 2000);
		message = "a note";
		note = new Note(date, message);
	}

	/**
	 * Test method for Constructor.
	 */
	@Test
	public void testNote() {
		note = new Note(date, message);
		assertEquals("1/1/2000", note.getDate().toString());
		assertEquals("a note", note.getMessage());
		
		try {
			note = new Note(null, message);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid note", e.getMessage());
		}
		try {
			note = new Note(date, null);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid note", e.getMessage());
		}
		try {
			note = new Note(date, "   ");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid note", e.getMessage());
		}
		try {
			note = new Note(date, "test\n");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid note", e.getMessage());
		}
		try {
			note = new Note(date, "test,");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid note", e.getMessage());
		}
	}

	/**
	 * Test method for compareTo()
	 */
	@Test
	public void testCompareTo() {
		Note dateSame = new Note(new Date("1/1/2000"), message);
		Note dateAfter = new Note(new Date(2, 29, 2048), message);
		assertTrue(note.compareTo(dateSame) == 0);
		assertTrue(note.compareTo(dateAfter) < 0);
		assertTrue(dateAfter.compareTo(note) > 0);
		
		Note messageSame = new Note(date, "a note");
		Note messageAfter = new Note(date, "z");
		assertTrue(note.compareTo(messageSame) == 0);
		assertTrue(note.compareTo(messageAfter) < 0);
		assertTrue(messageAfter.compareTo(note) > 0);
		
		try {
			note.compareTo(null);
			fail();
		} catch (NullPointerException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Test method for equals()
	 */
	@Test
	public void testEquals() {
		Note messageSame = new Note(date, "a note");
		assertTrue(note.equals(messageSame));
		assertFalse(note == null);

	}

	/**
	 * Test method for hashCode
	 */
	@Test
	public void testHashCode() {
		Note noteSame = new Note(date, message);
		Note noteDifferent = new Note(date, "z");
		assertEquals(note.hashCode(), noteSame.hashCode());
		assertNotEquals(note.hashCode(), noteDifferent.hashCode());
	}

	/**
	 * Test method for toString()
	 */
	@Test
	public void testToString() {
		assertEquals("1/1/2000 a note", note.toString());
	}


}
