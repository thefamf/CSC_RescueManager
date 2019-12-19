/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.rescue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Tests Rescue class
 * 
 * @author BrendanRowan
 *
 */
public class RescueTest {
	/** Test rescue */
	Rescue testRescue;
	/** name */
	String name = "name";
	/** house trained */
	boolean houseTrained = true;
	/** Animal is good with kids or not */
	boolean goodWithKids = true;
	/** Animal is adopted or not */
	boolean adopted = true;
	/** Animal's owner */
	String owner = "owner";
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
	/**
	 * Set up for testing.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		testRescue = new Rescue("Test Rescue");
	}

	/**
	 * Test method for Rescue
	 */
	@Test
	public void testRescue() {
		assertEquals("Test Rescue", testRescue.getName());
		assertTrue(testRescue instanceof Rescue);
		try {
			testRescue = new Rescue(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		try {
			testRescue = new Rescue("      ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		try {
			testRescue = new Rescue("Rescue\nName");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
	}

	/**
	 * Test method for getName
	 */
	@Test
	public void testGetName() {
		assertEquals("Test Rescue", testRescue.getName());
		testRescue = new Rescue("New Name");
		assertEquals("New Name", testRescue.getName());
	}

	/**
	 * Test method for addAnimal
	 */
	@Test
	public void testAddAnimal() {
		assertTrue(testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed)));
		assertFalse(testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed)));
		try {
			testRescue.addAnimal(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
	}

	/**
	 * Test method for getAnimal(int)
	 */
	@Test
	public void testGetAnimalInt() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		
		assertEquals(testRescue.getAnimal(0), new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		
		assertEquals(testRescue.getAnimal(1), new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
	}

	/**
	 * Test method for getAnimal(string, Date)
	 */
	@Test
	public void testGetAnimalStringDate() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		
		assertEquals(testRescue.getAnimal(name, birthday), new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		
		assertEquals(testRescue.getAnimal("Buster", new Date(12, 11, 2018)), new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		
	}

	/**
	 * Test method for contains
	 */
	@Test
	public void testContains() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		
		assertTrue(testRescue.contains(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed)));
		
		assertFalse(testRescue.contains(new Dog("Hulk", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed)));
	}

	/**
	 * Test method for addNote
	 */
	@Test
	public void testAddNote() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed));
		
		Note note1 = new Note(new Date(11, 13, 2018), "Testing1");
		assertEquals(0, testRescue.getAnimal(0).getNotes().size());
		// animal == null
		try {
			testRescue.addNote(null, new Note(new Date(11, 13, 2018), "Testing"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		assertEquals(0, testRescue.getAnimal(0).getNotes().size());
		// note == null
		try {
			testRescue.addNote(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
					owner, breed), null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		assertEquals(0, testRescue.getAnimal(0).getNotes().size());
		// animal && note == null
		try {
			testRescue.addNote(null, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		assertEquals(0, testRescue.getAnimal(0).getNotes().size());
		// add notes
		assertTrue(testRescue.addNote(testRescue.getAnimal(1), note1));
		assertEquals(1, testRescue.getAnimal(1).getNotes().size());
		// add same note to same animal
		try {
			assertFalse(testRescue.addNote(testRescue.getAnimal(1), note1));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		assertEquals(1, testRescue.getAnimal(0).getNotes().size());
		// add note to non existent animal
		assertFalse(testRescue.addNote(new Dog("Skipper", new Date(11, 19, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted,
				owner, breed), new Note(new Date(11, 13, 2019), "Testing no animal")));
		assertEquals(1, testRescue.getAnimal(1).getNotes().size());
		
		assertEquals("11/13/2018 Testing1", testRescue.getAnimal(0).getNotes().get(0).toString());
		assertEquals(1, testRescue.getAnimal(0).getNotes().size());
		assertEquals(2, testRescue.numAnimals());
		
	}

	/**
	 * Test method for setAdoptionInfo
	 */
	@Test
	public void testSetAdoptionInfo() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertEquals(testRescue.getAnimal(0).getDateAdopted(), null);
		assertEquals(testRescue.getAnimal(1).getDateAdopted(), null);
		assertEquals(testRescue.getAnimal(0).getOwner(), null);
		assertEquals(testRescue.getAnimal(1).getOwner(), null);
		
		// adopt animals
		testRescue.setAdoptionInfo(testRescue.getAnimal(0), adopted, dateAdopted, owner);
		testRescue.setAdoptionInfo(testRescue.getAnimal(1), adopted, dateAdopted, owner);
		
		assertEquals(testRescue.getAnimal(0).getDateAdopted(), dateAdopted);
		assertEquals(testRescue.getAnimal(1).getDateAdopted(), dateAdopted);
		assertEquals(testRescue.getAnimal(0).getOwner(), owner);
		assertEquals(testRescue.getAnimal(1).getOwner(), owner);
		
		try {
			testRescue.setAdoptionInfo(null, adopted, dateAdopted, owner);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
	}

	/**
	 * Test method for numAnimals
	 */
	@Test
	public void testNumAnimals() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertEquals(2, testRescue.numAnimals());
		
		// adopt first animal
		testRescue.setAdoptionInfo(testRescue.getAnimal(0), adopted, dateAdopted, owner);
		
		assertEquals(2, testRescue.numAnimals());
		
		// adopt second animal
		testRescue.setAdoptionInfo(testRescue.getAnimal(1), adopted, dateAdopted, owner);
		
		assertEquals(2, testRescue.numAnimals());
		
	}

	/**
	 * Test method for numAnimalsAvailable
	 */
	@Test
	public void testNumAnimalsAvailable() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertEquals(2, testRescue.numAnimalsAvailable());
		
		// adopt first animal
		testRescue.setAdoptionInfo(testRescue.getAnimal(0), adopted, dateAdopted, owner);
		
		assertEquals(1, testRescue.numAnimalsAvailable());
		
		// adopt second animal
		testRescue.setAdoptionInfo(testRescue.getAnimal(1), adopted, dateAdopted, owner);
		
		assertEquals(0, testRescue.numAnimalsAvailable());
		
		
		
		
	}

	/**
	 * Test method for numAnimalsAdopted
	 */
	@Test
	public void testNumAnimalsAdopted() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertEquals(0, testRescue.numAnimalsAdopted());
		
		// adopt first animal
		testRescue.setAdoptionInfo(testRescue.getAnimal(0), adopted, dateAdopted, owner);
		
		assertEquals(1, testRescue.numAnimalsAdopted());
		
		// adopt second animal
		testRescue.setAdoptionInfo(testRescue.getAnimal(1), adopted, dateAdopted, owner);
		
		assertEquals(2, testRescue.numAnimalsAdopted());
	}

	/**
	 * Test method for animalsAvailable
	 */
	@Test
	public void testAnimalsAvailable() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertEquals(2, testRescue.animalsAvailable().size());
			// adopt animal 
		testRescue.setAdoptionInfo(testRescue.getAnimal(0), adopted, dateAdopted, owner);
		
		assertEquals(1, testRescue.animalsAvailable().size());
	}

	/**
	 * Test method for availableCats
	 */
	@Test
	public void testAvailableCats() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Cat("Antonio", birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue));
		testRescue.addAnimal(new Cat("Tigger", new Date(12, 12, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue));
		
		assertEquals(2, testRescue.availableCats().size());
		
		testRescue.setAdoptionInfo(testRescue.getAnimal(0), adopted, dateAdopted, owner);
		assertEquals(1, testRescue.availableCats().size());
		testRescue.setAdoptionInfo(testRescue.getAnimal(3), adopted, dateAdopted, owner);
		assertEquals(0, testRescue.availableCats().size());
	}

	/**
	 * Test method for availableDogs
	 */
	@Test
	public void testAvailableDogs() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue));
		testRescue.addAnimal(new Cat("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue));
		
		assertEquals(2, testRescue.availableDogs().size());
		
		testRescue.setAdoptionInfo(testRescue.getAnimal(0), adopted, dateAdopted, owner);
		assertEquals(1, testRescue.availableDogs().size());
		testRescue.setAdoptionInfo(testRescue.getAnimal(1), adopted, dateAdopted, owner);
		assertEquals(0, testRescue.availableDogs().size());
	}

	/**
	 * Test method for animalsAdopted
	 */
	@Test
	public void testAnimalsAdopted() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Cat("Tygo", birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue));
		testRescue.addAnimal(new Cat("Buster", new Date(12, 10, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue));
		
		assertEquals(0, testRescue.animalsAdopted().size());
		testRescue.setAdoptionInfo(testRescue.getAnimal(0), adopted, dateAdopted, owner);
		testRescue.setAdoptionInfo(testRescue.getAnimal(1), adopted, dateAdopted, owner);
		testRescue.setAdoptionInfo(testRescue.getAnimal(2), adopted, dateAdopted, owner);
		assertEquals(3, testRescue.animalsAdopted().size());
		
		
		
		
	}

	/**
	 * Test method for availableAnimalsDayRange
	 */
	@Test
	public void testAvailableAnimalsDayRange() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, new Date(11, 1, 2019), breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, new Date(10, 14, 2019), breed));
		testRescue.addAnimal(new Cat("Tygo", birthday, size, houseTrained, goodWithKids, notes, new Date(11, 8, 2019)));
		testRescue.addAnimal(new Cat("Tigger", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue));
		
		assertEquals(4, testRescue.animalsAvailable().size());
		assertEquals(3, testRescue.availableAnimalsDayRange(new Date(11, 13, 2019), 4, 31).size());
		assertEquals(1, testRescue.availableAnimalsDayRange(new Date(11, 13, 2019), 5, 30).size());
		assertEquals(2, testRescue.availableAnimalsDayRange(new Date(11, 13, 2019), 5, 31).size());
		assertEquals(2, testRescue.availableAnimalsDayRange(new Date(11, 13, 2019), 4, 29).size());
		assertEquals(4, testRescue.availableAnimalsDayRange(new Date(11, 13, 2019), 1, 365).size());
		assertEquals(0, testRescue.availableAnimalsDayRange(new Date(11, 13, 2019), 1, 2).size());
		assertEquals(0, testRescue.availableAnimalsDayRange(new Date(11, 13, 2019), 365, 366).size());
	}

	/**
	 * Test method for availableAnimalsAge
	 */
	@Test
	public void testAvailableAnimalsAge() {
		testRescue.addAnimal(new Dog(name, new Date(9, 13, 2019), size, houseTrained, goodWithKids, notes, new Date(11, 1, 2019), breed));
		testRescue.addAnimal(new Dog("Buster", new Date(9, 13, 2017), size, houseTrained, goodWithKids, notes, new Date(10, 14, 2019), breed));
		testRescue.addAnimal(new Cat(name, birthday, size, houseTrained, goodWithKids, notes, new Date(11, 8, 2019)));
		testRescue.addAnimal(new Cat("Buster", new Date(11, 13, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue));
		
		assertEquals(4, testRescue.animalsAvailable().size());
		assertEquals(2, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 1, 8).size());
		assertEquals(3, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 0, 8).size());
		assertEquals(4, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 0, 10).size());
		assertEquals(3, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 0, 2).size());
		assertEquals(2, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 0, 1).size());
		assertEquals(2, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 1, 2).size());
		assertEquals(2, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 0, 1).size());
		assertEquals(1, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 9, 10).size());
		assertEquals(0, testRescue.availableAnimalsAge(new Date(11, 13, 2019), 3, 8).size());
		
		
	}

	/**
	 * Test method for getAnimalsAsArray
	 */
	@Test
	public void testGetAnimalsAsArray() {
		assertEquals(0, testRescue.getAnimalsAsArray(new Date(11, 13, 2019)).length);
		
		
		testRescue.addAnimal(new Dog(name, new Date(9, 13, 2019), size, houseTrained, goodWithKids, notes, new Date(11, 1, 2019), breed));
		testRescue.addAnimal(new Dog("Buster", new Date(9, 13, 2018), size, houseTrained, goodWithKids, notes, new Date(10, 14, 2019), breed));
		testRescue.addAnimal(new Cat("Sam", birthday, size, houseTrained, goodWithKids, notes, new Date(11, 8, 2019)));
		testRescue.addAnimal(new Cat("Chuck", new Date(11, 13, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue));
		
		String[][] array = testRescue.getAnimalsAsArray(new Date(11, 13, 2019));
		assertEquals(4, array.length);
		for (int i = 0; i < array.length; i++) {
			assertEquals(testRescue.getAnimal(i).getName(), array[i][0]);
			if (testRescue.getAnimal(i) instanceof Cat) {
				assertEquals("Cat", array[i][1]);
			} else {
				assertEquals("Dog", array[i][1]);
			}
			assertEquals(testRescue.getAnimal(i).getBirthday().toString(), array[i][2]);
			assertEquals(testRescue.getAnimal(i).getAge(new Date(11, 13, 2019)) + "", array[i][3]);
			assertEquals(testRescue.getAnimal(i).getAgeCategory(new Date(11, 13, 2019)).toString(), array[i][4]);
			if (testRescue.getAnimal(i).adopted()) {
				assertEquals("Yes", array[i][5]);
			} else {
				assertEquals("No", array[i][5]);
			}
			if (testRescue.getAnimal(i).adopted()) {
				assertEquals("", array[i][6]);
			} else {
				assertEquals(testRescue.getAnimal(i).getDaysAvailableForAdoption(new Date(11, 13, 2019)) + "", array[i][6]);
			}
		}
		
	}

	/**
	 * Test method for addAppointment
	 */
	@Test
	public void testAddAppointment() {
		// not in rescue
		assertFalse(testRescue.addAppointment(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed)));
		
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertTrue(testRescue.addAppointment(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed)));
		assertTrue(testRescue.addAppointment(new Dog("Buster", new Date(12, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed)));
		
		try {
			testRescue.addAppointment(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(null, e.getMessage());
		}
		
	}

	/**
	 * Test method for getAppointmentsAsArray
	 */
	@Test
	public void testGetAppointmentsAsArray() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(11, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertTrue(testRescue.addAppointment(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed)));
		assertTrue(testRescue.addAppointment(new Dog("Buster", new Date(11, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed)));
		String[][] array = testRescue.getAppointmentsAsArray(new Date(11, 13, 2019));
		assertEquals(2, array.length);
		assertEquals(2, testRescue.getAppointments().size());
		for (int i = 0; i < array.length; i++) {
			assertEquals(testRescue.getAnimal(i).getName(), array[i][0]);
			if (testRescue.getAnimal(i) instanceof Cat) {
				assertEquals("Cat", array[i][1]);
			} else {
				assertEquals("Dog", array[i][1]);
			}
			assertEquals(testRescue.getAnimal(i).getBirthday().toString(), array[i][2]);
			assertEquals(testRescue.getAnimal(i).getAge(new Date(11, 13, 2019)) + "", array[i][3]);
			assertEquals(testRescue.getAnimal(i).getAgeCategory(new Date(11, 13, 2019)).toString(), array[i][4]);
			if (testRescue.getAnimal(i).adopted()) {
				assertEquals("Yes", array[i][5]);
			} else {
				assertEquals("No", array[i][5]);
			}
			if (testRescue.getAnimal(i).adopted()) {
				assertEquals("", array[i][6]);
			} else {
				assertEquals(testRescue.getAnimal(i).getDaysAvailableForAdoption(new Date(11, 13, 2019)) + "", array[i][6]);
			}
		}
	}

	/**
	 * Test method for compareTo
	 */
	@Test
	public void testCompareTo() {
		Rescue testRescue2 = new Rescue("AA Rescue");
		Rescue testRescue3 = new Rescue("zz");
		
		assertTrue(testRescue.compareTo(testRescue3) < 0);
		assertTrue(testRescue2.compareTo(testRescue) < 0);
		assertTrue(testRescue3.compareTo(testRescue2) > 0);
		
		try {
			testRescue.compareTo(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(null, e.getMessage());
		}
		
		
		
		
		
	}

	/**
	 * Test method for getAppointments
	 */
	@Test
	public void testGetAppointments() {
		testRescue.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		testRescue.addAnimal(new Dog("Buster", new Date(11, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertTrue(testRescue.addAppointment(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed)));
		assertTrue(testRescue.addAppointment(new Dog("Buster", new Date(11, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed)));
		
		assertEquals(2, testRescue.getAppointments().size());
	}
	
	/**
	 * Test hashCode()
	 */
	@Test
	public void testHashCode() {
		Rescue rescue1 = new Rescue("Rescue 1");
		Rescue rescue2 = new Rescue("Rescue 2");
		Rescue rescueTwo = new Rescue("Rescue 2");
		Rescue rescue3 = new Rescue ("Rescue 3");
		
		assertEquals(rescue2.hashCode(), rescueTwo.hashCode());
		assertEquals(rescueTwo.hashCode(), rescue2.hashCode());
		
		assertNotEquals(rescue1.hashCode(), rescue2.hashCode());
		assertNotEquals(rescue2.hashCode(), rescue1.hashCode());
		
		assertNotEquals(rescue1.hashCode(), rescue3.hashCode());
		assertNotEquals(rescue3.hashCode(), rescue1.hashCode());
		
		rescue2.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		rescueTwo.addAnimal(new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertEquals(rescue2.hashCode(), rescueTwo.hashCode());
		assertEquals(rescueTwo.hashCode(), rescue2.hashCode());
		
		rescue2.addAnimal(new Dog("Buster", new Date(11, 11, 2018), size, houseTrained, goodWithKids, notes, dateEnterRescue, breed));
		
		assertEquals(rescue2.hashCode(), rescueTwo.hashCode());
		assertEquals(rescueTwo.hashCode(), rescue2.hashCode());
		
		
		
	}
	
	/**
	 * Test equals()
	 */
	@Test
	public void testEquals() {
		Rescue rescue1 = new Rescue("Rescue 1");
		Rescue rescue2 = new Rescue("Rescue 2");
		Rescue rescueTwo = new Rescue("Rescue 2");
		
		assertFalse(rescue1.equals(rescueTwo));
		assertFalse(rescueTwo.equals(rescue1));
		
		assertTrue(rescue2.equals(rescueTwo));
		assertTrue(rescueTwo.equals(rescue2));
		
		
		assertFalse(rescue1.equals(rescue2));
		assertFalse(rescue2.equals(rescue1));
		
	}
	
	/**
	 * Test toString()
	 */
	@Test
	public void testToString() {
		Rescue rescue1 = new Rescue("Rescue 1");
		
		assertEquals("Rescue 1", rescue1.toString());
	}

}
