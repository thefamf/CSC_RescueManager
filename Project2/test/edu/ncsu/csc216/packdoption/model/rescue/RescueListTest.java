/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.rescue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests RescueList
 * 
 * @author BrendanRowan
 *
 */
public class RescueListTest {
	/** Rescue list to test */
	RescueList rescueList;
	/** Test Rescue */
	Rescue testRescue;

	/**
	 * Set up for testing.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		rescueList = new RescueList();
		testRescue = new Rescue("Test Rescue");
	}

	/**
	 * Test method for rescueList
	 */
	@Test
	public void testRescueList() {
		RescueList test = new RescueList();
		assertEquals(0, test.size());
		assertEquals(0, rescueList.size());
	}

	/**
	 * Test method for addRescue
	 */
	@Test
	public void testAddRescueRescue() {
		Rescue r = null;
		rescueList.addRescue(testRescue);
		assertEquals(1, rescueList.size());
		rescueList.addRescue(new Rescue("R"));
		assertEquals(2, rescueList.size());
		
		// rescue is null
		try {
			rescueList.addRescue(r);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
		// rescue already in list
		try {
			rescueList.addRescue(testRescue);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
	}

	/**
	 * Test method for addRescue(String)
	 */
	@Test
	public void testAddRescueString() {
		String n = null;
		rescueList.addRescue("String Rescue");
		assertEquals(1, rescueList.size());
		rescueList.addRescue("Second Rescue");
		assertEquals(2, rescueList.size());
		
		// try will null 
		try {
			rescueList.addRescue(n);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
		// Try when rescue already exists in list
		try {
			rescueList.addRescue("String Rescue");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
		// Try when name is whitespace only
		try {
			rescueList.addRescue("     ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
		//Try when name contains \n
		try {
			rescueList.addRescue("Rescue\nName");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
	}

	/**
	 * Test method for getRescue
	 */
	@Test
	public void testGetRescue() {
		rescueList.addRescue("C");
		rescueList.addRescue("A");
		rescueList.addRescue("D");
		rescueList.addRescue("B");
		assertEquals(4, rescueList.size());
		
		assertEquals("B", rescueList.getRescue(1).getName());
		assertEquals("A", rescueList.getRescue(0).getName());
		assertEquals("D", rescueList.getRescue(3).getName());
		
		try {
			rescueList.getRescue(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		
		try {
			rescueList.getRescue(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		
	}

	/**
	 * Test method for size
	 */
	@Test
	public void testSize() {
		assertEquals(0, rescueList.size());
		rescueList.addRescue("C");
		assertEquals(1, rescueList.size());
		rescueList.addRescue("A");
		assertEquals(2, rescueList.size());
		rescueList.addRescue("D");
		assertEquals(3, rescueList.size());
	}

}
