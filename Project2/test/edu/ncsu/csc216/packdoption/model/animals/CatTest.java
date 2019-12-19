/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.AgeCategory;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Tests Cat class
 * 
 * @author BrendanRowan
 *
 */
public class CatTest {
	/** test cat */
	Cat cat = null;
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

	/**
	 * Setup for tests.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		Note note1 = new Note(new Date(11, 30, 2018), "a note");
		notes.add(note1);

		cat = new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner);

	}

	/**
	 * Tests Cat()
	 */
	@Test
	public void testCatStringDateSizeBooleanBooleanSortedLinkedListOfNoteDateBooleanDateString() {
		assertEquals("name", cat.getName());
		assertEquals("1/1/2010", cat.getBirthday().toString());
		assertEquals(Size.LARGE, cat.getSize());
		assertEquals(true, cat.isHouseTrained());
		assertEquals(true, cat.isGoodWithKids());
		assertEquals(AgeCategory.SENIOR, cat.getAgeCategory(today));
		// TODO SortedLinkedList.toString()
//		assertEquals("11/30/2018 a note", cat.getNotes().toString());
		assertEquals("12/31/2018", cat.getDateEnterRescue().toString());
		assertEquals(true, cat.adopted());
		assertEquals("6/30/2019", cat.getDateAdopted().toString());
		assertEquals("owner", cat.getOwner());
	}

	/**
	 * Test method for Cat second constructor.
	 */
	@Test
	public void testCatStringDateSizeBooleanBooleanSortedLinkedListOfNoteDate() {
		cat = new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
		assertEquals("name", cat.getName());
		assertEquals("1/1/2010", cat.getBirthday().toString());
		assertEquals(Size.LARGE, cat.getSize());
		assertEquals(true, cat.isHouseTrained());
		assertEquals(true, cat.isGoodWithKids());
		assertEquals(AgeCategory.SENIOR, cat.getAgeCategory(today));
		// TODO SortedLinkedList.toString()
//		assertEquals("11/30/2018 a note", cat.getNotes().toString());
		assertEquals("12/31/2018", cat.getDateEnterRescue().toString());
		assertEquals(false, cat.adopted());
		assertEquals(null, cat.getDateAdopted());
		assertEquals(null, cat.getOwner());
	}

	/**
	 * Tests setAdoptionInfo()
	 */
	@Test
	public void testSetAdoptionIndo() {
		try {
			cat.setAdoptionInfo(false, new Date(1, 1, 2019), null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		try {
			cat.setAdoptionInfo(false, null, "owner");
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}

		cat.setAdoptionInfo(false, null, null);
		array = cat.getAnimalAsArray(today);
		assertEquals("name", array[0]);
		assertEquals("Cat", array[1]);
		assertEquals("1/1/2010", array[2]);
		assertEquals("9", array[3]);
		assertEquals("SENIOR", array[4]);
		assertEquals("No", array[5]);
		assertEquals("365", array[6]);

		try {
			cat.setAdoptionInfo(true, new Date(1, 1, 2019), null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		try {
			cat.setAdoptionInfo(true, null, "owner");
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}

		cat.setAdoptionInfo(true, new Date(1, 1, 2019), "owner");
		array = cat.getAnimalAsArray(today);
		assertEquals("name", array[0]);
		assertEquals("Cat", array[1]);
		assertEquals("1/1/2010", array[2]);
		assertEquals("9", array[3]);
		assertEquals("SENIOR", array[4]);
		assertEquals("Yes", array[5]);
		assertEquals("", array[6]);
	}

	/**
	 * Tests setSize()
	 */
	@Test
	public void testSetSize() {
		assertEquals("LARGE", cat.getSize().toString());
		try {
			cat.setSize(null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		cat.setSize(Size.SMALL);
		assertEquals("SMALL", cat.getSize().toString());
	}

	/**
	 * Tests addNote()
	 */
	@Test
	public void testAddNote() {
		Note note1 = new Note(new Date(11, 30, 2018), "a 2nd note");
		Note note2 = new Note(new Date(1, 1, 2019), "another note");
		assertTrue(cat.addNote(note1));
		assertTrue(cat.addNote(note2));
	}

	/**
	 * Tests compareTo()
	 */
	@Test
	public void testCompareTo() {
		Cat catSame = new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted,
				dateAdopted, owner);
		Cat catOlder = new Cat(name, new Date(2, 1, 2000), size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner);

		try {
			cat.compareTo(null);
			fail();
		} catch (NullPointerException e) {
			assertNull(e.getMessage());
		}
		assertEquals(0, cat.compareTo(cat));
		assertEquals(0, cat.compareTo(catSame));
		assertEquals(-1, catOlder.compareTo(cat));
		assertEquals(1, cat.compareTo(catOlder));
	}

	/**
	 * Tests hashCode()
	 */
	@Test
	public void testHashCode() {
		Cat catSame = new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted,
				dateAdopted, owner);
		Cat catOlder = new Cat(name, new Date(2, 1, 2000), size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner);
		Cat catDifferentName = new Cat("different", birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner);

		assertEquals(cat.hashCode(), cat.hashCode());
		assertEquals(cat.hashCode(), catSame.hashCode());
		assertNotEquals(cat.hashCode(), catOlder.hashCode());
		assertNotEquals(cat.hashCode(), catDifferentName.hashCode());
	}

	/**
	 * Tests equals()
	 */
	@Test
	public void testEquals() {
		Cat catSame = new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted,
				dateAdopted, owner);
		Cat catOlder = new Cat(name, new Date(2, 1, 2000), size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner);
		Cat catDifferentName = new Cat("different", birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner);

		assertFalse(cat == null);
		assertTrue(cat.equals(cat));
		assertTrue(cat.equals(catSame));
		assertFalse(cat.equals(catOlder));
		assertFalse(cat.equals(catDifferentName));

	}

	/**
	 * Tests toString()
	 */
	@Test
	public void testToString() {
		assertEquals("name (1/1/2010)\n-11/30/2018 a note", cat.toString());
	}

	/**
	 * Tests getAge()
	 */
	@Test
	public void testGetAge() {
		try {
			cat.getAge(null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Tests getDaysAvailableForAdoption()
	 */
	@Test
	public void testGetDaysAvailableForAdoption() {
		assertEquals(-1, cat.getDaysAvailableForAdoption(today));
		cat.setAdoptionInfo(false, null, null);
		assertEquals(365, cat.getDaysAvailableForAdoption(today));

		try {
			cat.getDaysAvailableForAdoption(null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}

}
