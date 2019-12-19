package edu.ncsu.csc216.packdoption.model.io;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SimpleListIterator;

/**
 * IO class to write a RescueList to a file.
 * 
 * @author bmrowan
 * @author mlee25
 *
 */
public class PackDoptionWriter {

	/**
	 * Writes the given RescueList to the given file.
	 * 
	 * @param fileName the file to write
	 * @param list     the RescueList
	 * @throws IllegalArgumentException on any error
	 */
	public static void writeRescueFile(String fileName, RescueList list) {
		PrintStream fileWriter = null;
		try {
			fileWriter = new PrintStream(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file");
		}

		// loop through rescues in list
		for (int i = 0; i < list.size(); i++) {
			// Label rescue
			fileWriter.print("# " + list.getRescue(i).getName().trim() + "\n");
			// loop through animals
			for (int j = 0; j < list.getRescue(i).numAnimals(); j++) {
				Animal animal = list.getRescue(i).getAnimal(j);
				fileWriter.print("* ");
				if (animal instanceof Dog) {
					fileWriter.print("Dog,");
				} else {
					fileWriter.print("Cat,");
				}

				fileWriter.print(animal.getName() + ",");

				fileWriter.print(animal.getBirthday() + ",");

				fileWriter.print(animal.getSize().toString() + ",");

				if (animal.isHouseTrained()) {
					fileWriter.print("true,");
				} else {
					fileWriter.print("false,");
				}

				if (animal.isGoodWithKids()) {
					fileWriter.print("true,");
				} else {
					fileWriter.print("false,");
				}

				fileWriter.print(animal.getDateEnterRescue().toString() + ",");

				if (animal.adopted()) {
					fileWriter.print("true,");
					fileWriter.print(animal.getDateAdopted().toString() + ",");
					fileWriter.print(animal.getOwner() + ",");
				}

				if (animal instanceof Dog) {
					fileWriter.print(((Dog) animal).getBreed().toString() + ",");
				}

				fileWriter.print("NOTES");
				if (animal.getNotes().size() != 0) {
					fileWriter.print(",");
				}
				SimpleListIterator<Note> iter = animal.getNotes().iterator();
				while (iter.hasNext()) {
					// handle empty after NOTES
					String token = iter.next().toString();
					fileWriter.print(token);
					if (iter.hasNext()) {
						fileWriter.print(",");
					}
				}
				// go to new line for vet appointments
				fileWriter.println();

			}

			//ArrayListQueue<Animal> appointmentList = list.getRescue(i).getAppointments();

			// set the upper bound to the list size
			int bound = list.getRescue(i).getAppointments().size();
			for (int k = 0; k < bound; k++) {
//				fileWriter.print("- ");
//				Animal app = list.getRescue(i).getAppointments().remove();
//				fileWriter.print(app.getName() + ",");
//				fileWriter.print(app.getBirthday().toString());

				fileWriter.print("- ");
				System.out.print("- ");
				Animal app = list.getRescue(i).getAppointments().remove();
				fileWriter.print(app.getName() + ",");
				System.out.print(app.getName() + ",");
				fileWriter.println(app.getBirthday().toString());
				System.out.println(app.getBirthday().toString());

				// add appointment back
				list.getRescue(i).addAppointment(app);

//				if (k + 1 != bound) {
//					fileWriter.println();
//				}

			}
//			int appBound = appointmentList.size();
//			for (int k = 0; k < appBound; k++) {
//				list.getRescue(i).addAppointment(appointmentList.remove());
//			}
			
			fileWriter.println();
//			fileWriter.println();

		}

		fileWriter.close();
	}
}
