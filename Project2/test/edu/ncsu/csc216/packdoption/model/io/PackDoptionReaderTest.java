/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.io;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Date;

/**
 * Tests PackDoptionReader class
 * 
 * @author mlee25
 *
 */
public class PackDoptionReaderTest {

	/** valid test filename */
	String validFilename;
	/** invalid test filename */
	String invalidFilename;
	/** test RescueList */
	RescueList rescueList;
	
	/**
	 * Set up for testing.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		validFilename = "test-files/sample-updated.md";
		invalidFilename = "test-files/nofile.md";
	}

//	/**
//	 * Test method for PackDoptionReader
//	 */
//	@Test
//	public void testPackDoptionReader() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for readRescueListFile
	 */
	@Test
	public void testReadRescueListFile() {
		Date today = new Date(12, 31, 2019);
		rescueList = new RescueList();
		try {
			rescueList = PackDoptionReader.readRescueListFile(invalidFilename);
			fail();
		} catch (IllegalArgumentException e) {
			// invalid
		}
		try {
			// test.md is for modification
			rescueList = PackDoptionReader.readRescueListFile("test-files/test.md");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
					
//			fail();
		}
		try {
			rescueList = PackDoptionReader.readRescueListFile(validFilename);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(2, rescueList.size());
		
//		assertEquals("NCSU Rescue", rescueList.getRescue(0).getName());
//		assertEquals(4, rescueList.getRescue(0).getAnimalsAsArray(today).length);
//		assertEquals(3, rescueList.getRescue(0).getAppointmentsAsArray(today).length);
//
//		assertEquals("Ms. Wuf's Rescue", rescueList.getRescue(1).getName());
//		assertEquals(3, rescueList.getRescue(1).getAnimalsAsArray(today).length);
//		assertEquals(1, rescueList.getRescue(1).getAppointmentsAsArray(today).length);
		
		assertEquals("Ms. Wuf's Rescue", rescueList.getRescue(0).getName());
		assertEquals(3, rescueList.getRescue(0).getAnimalsAsArray(today).length);
		assertEquals(1, rescueList.getRescue(0).getAppointmentsAsArray(today).length);
		
		assertEquals("NCSU Rescue", rescueList.getRescue(1).getName());
		assertEquals(4, rescueList.getRescue(1).getAnimalsAsArray(today).length);
		assertEquals(3, rescueList.getRescue(1).getAppointmentsAsArray(today).length);

		
	}

}
