/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * Tests PackDoptionManager class
 * 
 * @author BrendanRowan
 *
 */
public class PackDoptionManagerTest {
	/** Test PackDoptionManager */
	PackDoptionManager manager;
	/** Test expected file */
	String expectedFile = "test-files/sample-updated.md";
	/** Test valid file */
	String validFile = "test-files/sample-updated.md";
	

	/**
	 * Set up for testing.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = PackDoptionManager.getInstance();
		manager.newList();
	}


	/**
	 * Test method for newList
	 */
	@Test
	public void testNewList() {
		manager.loadFile(validFile);
		assertEquals(2, manager.getRescueList().size());
		manager.newList();
		assertEquals(0, manager.getRescueList().size());
	}

	/**
	 * Test method for isChanged
	 */
	@Test
	public void testIsChanged() {
		assertFalse(manager.isChanged());
		manager.loadFile(validFile);
		assertFalse(manager.isChanged());
		manager.saveFile("test-files/testing_pdm.md");
		
//		manager.getRescueList().addRescue("Random other rescue");
//		
//		assertTrue(manager.isChanged());
	}

	/**
	 * Test method for setChanged()
	 */
	@Test
	public void testSetChanged() {
		assertFalse(manager.isChanged());
		manager.setChanged(true);
		assertTrue(manager.isChanged());
	}

	/**
	 * Test method for getFileName
	 */
	@Test
	public void testGetFilename() {
		manager.setFilename("test-files/testing_pdm.md");
		assertEquals("test-files/testing_pdm.md", manager.getFilename());
	}

	/**
	 * Test method for setFilename
	 */
	@Test
	public void testSetFilename() {
		manager.setFilename("test-files/testing_pdm.md");
		assertEquals("test-files/testing_pdm.md", manager.getFilename());
		manager.setFilename("test-files/other_file.md");
		assertEquals("test-files/other_file.md", manager.getFilename());
		
		try {
			manager.setFilename(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
			
		}
		
		try {
			manager.setFilename("   ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
	}

	/**
	 * Test method for loadFile
	 */
	@Test
	public void testLoadFile() {
		try {
			manager.loadFile(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
		
		try {
			manager.loadFile("file_doesnt_exist.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
		
		try {
			manager.loadFile(validFile);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(2, manager.getRescueList().size());
	}

	/**
	 * Test method for saveFile
	 */
	@Test
	public void testSaveFile() {
		manager.loadFile(validFile);
		
		try {
			manager.saveFile("test-files/testing_pdm.md");
			assertEquals(1, manager.getRescueList().getRescue(0).getAppointments().size());
			assertEquals(3, manager.getRescueList().getRescue(1).getAppointments().size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		
		try {
			manager.saveFile(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
		try {
			manager.saveFile("   ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
	}

	/**
	 * Test method for getRescueList
	 */
	@Test
	public void testGetRescueList() {
		manager.loadFile(validFile);
		
		assertTrue(manager.getRescueList() instanceof RescueList);
		assertEquals(2, manager.getRescueList().size());
	}


}
