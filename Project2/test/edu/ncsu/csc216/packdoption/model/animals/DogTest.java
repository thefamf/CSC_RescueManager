/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.AgeCategory;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Tests for Dog class
 * 
 * @author BrendanRowan
 *
 */
public class DogTest {

	/** test dog */
	Dog dog = null;
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
	/** Animal's owner */
	Breed breed = Breed.MIXED;
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

		dog = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed);

//		Note note1 = new Note(new Date(11, 30, 2018), "a note");
//		Note note2 = new Note(new Date(1, 1, 2019), "another note");
//		notes.add(note1);
//		notes.add(note2);
	}

	/**
	 * Tests Dog()
	 */
	@Test
	public void testDogStringDateSizeBooleanBooleanSortedLinkedListOfNoteDateBooleanDateString() {
		assertEquals("name", dog.getName());
		assertEquals("1/1/2010", dog.getBirthday().toString());
		assertEquals(Size.LARGE, dog.getSize());
		assertEquals(true, dog.isHouseTrained());
		assertEquals(true, dog.isGoodWithKids());
		assertEquals(AgeCategory.SENIOR, dog.getAgeCategory(today));
		// TODO SortedLinkedList.toString()
//		assertEquals("11/30/2018 a note", dog.getNotes().toString());
		assertEquals("12/31/2018", dog.getDateEnterRescue().toString());
		assertEquals(true, dog.adopted());
		assertEquals("6/30/2019", dog.getDateAdopted().toString());
		assertEquals("owner", dog.getOwner());

		try {
			dog = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted,
					dateAdopted, owner, null);
			fail();
		} catch (IllegalArgumentException e) {
			// invalid
		}
	}

	/**
	 * Test method for Dog second constructor.
	 */
	@Test
	public void testDogStringDateSizeBooleanBooleanSortedLinkedListOfNoteDate() {
		try {
			dog = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, null);
			fail();
		} catch (IllegalArgumentException e) {
			// invalid
		}
		dog = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed);
		assertEquals("name", dog.getName());
		assertEquals("1/1/2010", dog.getBirthday().toString());
		assertEquals(Size.LARGE, dog.getSize());
		assertEquals(true, dog.isHouseTrained());
		assertEquals(true, dog.isGoodWithKids());
		assertEquals(AgeCategory.SENIOR, dog.getAgeCategory(today));
		// TODO SortedLinkedList.toString()
//		assertEquals("11/30/2018 a note", dog.getNotes().toString());
		assertEquals("12/31/2018", dog.getDateEnterRescue().toString());
		assertEquals(false, dog.adopted());
		assertEquals(null, dog.getDateAdopted());
		assertEquals(null, dog.getOwner());
	}

	/**
	 * Tests setAdoptionInfo()
	 */
	@Test
	public void testSetAdoptionIndo() {
		try {
			dog.setAdoptionInfo(false, new Date(1, 1, 2019), null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		try {
			dog.setAdoptionInfo(false, null, "owner");
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}

		dog.setAdoptionInfo(false, null, null);
		array = dog.getAnimalAsArray(today);
		assertEquals("name", array[0]);
		assertEquals("Dog", array[1]);
		assertEquals("1/1/2010", array[2]);
		assertEquals("9", array[3]);
		assertEquals("SENIOR", array[4]);
		assertEquals("No", array[5]);
		assertEquals("365", array[6]);

		try {
			dog.setAdoptionInfo(true, new Date(1, 1, 2019), null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		try {
			dog.setAdoptionInfo(true, null, "owner");
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}

		dog.setAdoptionInfo(true, new Date(1, 1, 2019), "owner");
		array = dog.getAnimalAsArray(today);
		assertEquals("name", array[0]);
		assertEquals("Dog", array[1]);
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
		assertEquals("LARGE", dog.getSize().toString());
		try {
			dog.setSize(null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		dog.setSize(Size.SMALL);
		assertEquals("SMALL", dog.getSize().toString());
	}

	/**
	 * Tests compareTo()
	 */
	@Test
	public void testCompareTo() {
		Dog dogSame = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted,
				dateAdopted, owner, breed);
		Dog dogOlder = new Dog(name, new Date(2, 1, 2000), size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner, breed);
//		Dog dogDifferentName = new Dog("different", birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
//				owner, breed);

		try {
			dog.compareTo(null);
			fail();
		} catch (NullPointerException e) {
			assertNull(e.getMessage());
		}
		assertEquals(0, dog.compareTo(dog));
		assertEquals(0, dog.compareTo(dogSame));
		assertEquals(-1, dogOlder.compareTo(dog));
		assertEquals(1, dog.compareTo(dogOlder));
	}

	/**
	 * Tests hashCode()
	 */
	@Test
	public void testHashCode() {
		Dog dogSame = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted,
				dateAdopted, owner, breed);
		Dog dogOlder = new Dog(name, new Date(2, 1, 2000), size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner, breed);
		Dog dogDifferentName = new Dog("different", birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner, breed);

		assertEquals(dog.hashCode(), dog.hashCode());
		assertEquals(dog.hashCode(), dogSame.hashCode());
		assertNotEquals(dog.hashCode(), dogOlder.hashCode());
		assertNotEquals(dog.hashCode(), dogDifferentName.hashCode());
	}

	/**
	 * Tests equals()
	 */
	@Test
	public void testEquals() {
		Dog dogSame = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted,
				dateAdopted, owner, breed);
		Dog dogOlder = new Dog(name, new Date(2, 1, 2000), size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner, breed);
		Dog dogDifferentName = new Dog("different", birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue,
				adopted, dateAdopted, owner, breed);

		assertFalse(dog == null);
		assertTrue(dog.equals(dog));
		assertTrue(dog.equals(dogSame));
		assertFalse(dog.equals(dogOlder));
		assertFalse(dog.equals(dogDifferentName));

	}

	/**
	 * Tests toString()
	 */
	@Test
	public void testToString() {
		assertEquals("name (1/1/2010)\n-11/30/2018 a note", dog.toString());
	}

	/**
	 * Tests getAge()
	 */
	@Test
	public void testGetAge() {
		try {
			dog.getAge(null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Tests getAgeCategory()
	 */
	@Test
	public void testGetAgeCategory() {
		Date ageOne = new Date(10, 10, 2018);
//		Date ageFour = new Date(10, 15, 2015);
		Date ageThree = new Date(10, 15, 2016);
		Date ageSix = new Date(10, 20, 2013);
		try {
			dog.getAgeCategory(null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		try {
			dog.getAgeCategory(new Date(1, 1, 2009));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}

		assertEquals("SENIOR", dog.getAgeCategory(today).toString());
		dog.setSize(Size.MEDIUM);
		assertEquals("ADULT", dog.getAgeCategory(new Date(12, 31, 2018)).toString());
		dog = new Dog(name, ageSix, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed);
		assertEquals("SENIOR", dog.getAgeCategory(today).toString());
		dog = new Dog(name, ageThree, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed);
		assertEquals("ADULT", dog.getAgeCategory(new Date(12, 31, 2019)).toString());
		dog = new Dog(name, ageOne, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed);
		assertEquals("YOUNG", dog.getAgeCategory(new Date(12, 31, 2019)).toString());

	}

	/**
	 * Tests getDaysAvailableForAdoption()
	 */
	@Test
	public void testGetDaysAvailableForAdoption() {
		assertEquals(-1, dog.getDaysAvailableForAdoption(today));
		dog.setAdoptionInfo(false, null, null);
		assertEquals(365, dog.getDaysAvailableForAdoption(today));

		try {
			dog.getDaysAvailableForAdoption(null);
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Tests getBreed()
	 */
	@Test
	public void testGetBreed() {
		dog = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed);
		assertEquals(Breed.MIXED, dog.getBreed());
	}

}
