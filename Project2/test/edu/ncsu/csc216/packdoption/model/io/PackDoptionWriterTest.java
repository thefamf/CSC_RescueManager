/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * Tests PackDoptionWriter class
 * 
 * @author BrendanRowan
 *
 */
public class PackDoptionWriterTest {
	/** Test rescue list */
	RescueList rescueList;

	/** valid input file */
	private final String validInputFile = "test-files/sample-updated.md";
	/** Expected output file */
	private final String expectedOutputFile = "test-files/expected_rescue_list.md";
	/** Actual output file */
	private final String actualOutputFile = "test-files/actual_rescue_list.md";

	

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.io.PackDoptionWriter#writeRescueFile(java.lang.String, edu.ncsu.csc216.packdoption.model.rescue.RescueList)}.
	 */
	@Test
	public void testWriteRescueFile() {		
		rescueList = PackDoptionReader.readRescueListFile(validInputFile);
		// test with loading and writing test file
		try {
			PackDoptionWriter.writeRescueFile("test-files/actual_rescue_list.md", rescueList);
			// write again to 2nd file to confirm rescueList is the same
			PackDoptionWriter.writeRescueFile("test-files/actual_rescue_list2.md", rescueList);
		} catch (IllegalArgumentException e) {
			fail("Cannot write to rescue list file");
		}
		
		checkFiles(expectedOutputFile, actualOutputFile);
		checkFiles(expectedOutputFile, "test-files/actual_rescue_list2.md");
		
		
//		// Test with creating a rescue list
//		RescueList list = new RescueList();
//		
//		list.addRescue("Rescue 1");
//		list.addRescue(new Rescue("Animal Rescue"));
//		
//		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
//		notes.add(new Note(new Date(11, 20, 2019), "Date adopted note"));
//		
//		SortedLinkedList<Note> notes2 = new SortedLinkedList<Note>();
//		notes2.add(new Note(new Date(9, 2, 2019), "adopted note"));
//		notes2.add(new Note(new Date(9, 5, 2019), "Owner is Brendan"));
//		//empty notes
//		SortedLinkedList<Note> notes3 = new SortedLinkedList<Note>();
//		
//		// add date to Animal Rescue
//		list.getRescue(0).addAnimal(new Dog("Buster", new Date(12, 11, 2017), Size.LARGE, true, true,
//				notes, new Date(7, 5, 2018), true, new Date(11, 20, 2019), "Jones Family",
//				Breed.ROTTWEILER));
//		list.getRescue(0).addAnimal(new Cat("Tiger", new Date(12, 15, 2017), Size.SMALL, false, true,
//				notes3, new Date(2, 25, 2018), false, null, null));
//		list.getRescue(0).addAppointment(list.getRescue(0).getAnimal(0));
//		list.getRescue(0).addAppointment(list.getRescue(0).getAnimal(1));
//		assertEquals(2, list.getRescue(0).getAppointments().size());
//		//add data to Rescue 1
//		list.getRescue(1).addAnimal(new Dog("Zane", new Date(12, 11, 2017), Size.MEDIUM, true, true,
//				notes3, new Date(10, 5, 2018), Breed.GERMAN_SHEPHERD));
//		list.getRescue(1).addAnimal(new Dog("Buddy", new Date(12, 11, 2017), Size.SMALL, true, false,
//				notes2, new Date(8, 22, 2018), true, new Date(9, 2, 2018), "Brendan", Breed.BEAGLE));
//		
//		try {
//			PackDoptionWriter.writeRescueFile("test-files/actual_rescue_list_coded.md", list);
//		} catch (IllegalArgumentException e) {
//			fail("Cannot write to rescue list file");
//		}
//		
//		checkFiles("test-files/expected_rescue_list_coded.md", "test-files/actual_rescue_list_coded.md");
		
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
//			int line = 1;
			while (expScanner.hasNextLine()) {
//				System.out.println("line: " + line++);
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
