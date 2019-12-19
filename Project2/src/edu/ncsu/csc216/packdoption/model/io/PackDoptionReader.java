package edu.ncsu.csc216.packdoption.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.rescue.Rescue;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * IO class to read files and create a RescueList.
 * 
 * @author mlee25
 *
 */
public class PackDoptionReader {

	/**
	 * Constructor
	 * 
	 * @throws FileNotFoundException if file not found
	 */
	public PackDoptionReader() throws FileNotFoundException {
		// null constructor
	}

	/**
	 * Reads and returns a RescueList from the given file.
	 * 
	 * @param filename the file to read
	 * @return the RescueList
	 * @throws IllegalArgumentException if unable to load file or error with
	 *                                  formatting
	 */
	@SuppressWarnings("resource")
	public static RescueList readRescueListFile(String filename) {
		RescueList rescues = null;
		// try {
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found");
		}
		int dividerCount = 0;
		try {
			String line = null;
			rescues = new RescueList();
			Rescue rescue = null;
//			Date today = new Date(12, 31, 2019);

			Animal animal = null;
			String type = null;
			String name = null;
			Date birthday = null;
			Size size = null;
			boolean houseTrained = false;
			boolean goodWithKids = false;
			Date dateEnterRescue = null;
			boolean adopted = false;
			Date dateAdopted = null;
			String owner = null;
			Breed breed = null;
			Note note = null;
			Date noteDate = null;
			String noteMessage = null;
			SortedLinkedList<Note> notes;
			String startCheck = null;
			boolean started = false;

			// try { // line reader try block
			while (fileReader.hasNextLine()) {
				line = fileReader.nextLine();

				if (!started) {
					startCheck = line;
					if (startCheck.charAt(0) != '#') {
						throw new IllegalArgumentException("Unable to load file.");
					}
					started = true;
				}

//				if (line.charAt(0) != '#') {
//					throw new IllegalArgumentException("Unable to load file.");
//				}

//					line = fileReader.next();
//					System.out.println("line: " + line);
//				if (line.charAt(0) == '\n' || line.charAt(0) == '\r' ) {
//				if (rescueCount > 1 && !line.equals("")) {
//					throw new IllegalArgumentException("Invalid file");
//				}

				if (line.equals("")) {
					dividerCount++;
					if (!fileReader.hasNextLine()) {
						break;
					}
					line = fileReader.nextLine();
				}
//						if (rescue != null) {
//							// add the rescue to rescueList and reset to start reading a new rescue
//							System.out.println("rescue ended reached");
//							rescues.addRescue(rescue);
//							// testing
//							for (int i = 0; i < rescue.numAnimals(); i++) {
//								Animal a = rescue.getAnimal(i);
//								System.out.println(a.toString());
//							}
//							// reset rescue
//							rescue = null;
//						}
//						if (rescue == null) {
				// start a new rescue
				if (line.charAt(0) == '#') {
					if (dividerCount != rescues.size()) {
						throw new IllegalArgumentException("Unable to load file.");
					}
					rescue = null;
//						System.out.println("new rescue");
					String rescueNameLine = line;
					Scanner rescueNameScanner = new Scanner(rescueNameLine);
					rescueNameScanner.useDelimiter("#");
					name = rescueNameScanner.next().trim();
					rescueNameScanner.close();
					try {
						rescue = new Rescue(name);
					} catch (IllegalArgumentException e) {
						throw new IllegalArgumentException("Unable to load file.");
					}
//						System.out.println("rescue name: " + rescue.getName());
					line = fileReader.nextLine();
				}

				// read animal
				Scanner s = new Scanner(line);
				if (line.charAt(0) == '*') {
//					System.out.println("char is *");
//					System.out.println(line);
//					try { 
					if (rescue == null) {
						throw new IllegalArgumentException("Unable to load file.");
					}
					s.useDelimiter(",");
					type = s.next().substring(2);
					if (type.equals("Dog")) {
//							System.out.println("Dog reached");
						name = s.next();
						birthday = new Date(s.next());
						size = Size.valueOf(s.next());
						houseTrained = Boolean.valueOf(s.next());
						String kids = s.next();
						if (kids.equals("true")) {
							goodWithKids = true;
						} else if (kids.equals("false")) {
							goodWithKids = false;
						} else {
							throw new IllegalArgumentException("Unable to load file.");
						}

//							goodWithKids = Boolean.valueOf(s.next());
						dateEnterRescue = new Date(s.next());
						String nextToken = s.next();
						if (nextToken.equalsIgnoreCase("true")) {
							adopted = true;
							dateAdopted = new Date(s.next());
							owner = s.next();
							breed = Breed.valueOf(s.next());
						} else {
							adopted = false;
							breed = Breed.valueOf(nextToken);
						}
						notes = new SortedLinkedList<Note>();
						if (notes.size() == 0) {
							String noteCheck = s.next();
							if (!noteCheck.equals("NOTES")) {
								s.close();
								throw new IllegalArgumentException("Unable to load file.");
							}
						}
						if (s.hasNext()) {
							s.useDelimiter(" ");
							noteDate = new Date(s.next().substring(1));
							s.useDelimiter(",");
							noteMessage = s.next().substring(1);
//								String nextcheck = s.next().substring(1);
//								System.out.println(nextcheck);
							note = new Note(noteDate, noteMessage);
//								System.out.println("note: " + note.toString());
							notes.add(note);
//								s.useDelimiter(",");
						}
						if (adopted) {
							animal = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue,
									adopted, dateAdopted, owner, breed);
						} else {
							animal = new Dog(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue,
									breed);
						}
					}
					if (type.equals("Cat")) {
//						System.out.println("reached cat");
						name = s.next();
						birthday = new Date(s.next());
						size = Size.valueOf(s.next());
//							houseTrained = Boolean.valueOf(s.next());
//							goodWithKids = Boolean.valueOf(s.next());
						String house = s.next();
						if (house.equals("true")) {
							houseTrained = true;
						} else if (house.equals("false")) {
							houseTrained = false;
						} else {
							throw new IllegalArgumentException("Unable to load file.");
						}
						String kids = s.next();
						if (kids.equals("true")) {
							goodWithKids = true;
						} else if (kids.equals("false")) {
							goodWithKids = false;
						} else {
							throw new IllegalArgumentException("Unable to load file.");
						}
						dateEnterRescue = new Date(s.next());
						String nextToken = s.next();
						notes = new SortedLinkedList<Note>();
						if (nextToken.equalsIgnoreCase("true")) {
							adopted = true;
							dateAdopted = new Date(s.next());
							owner = s.next();
							nextToken = s.next();
						}

						if (notes.size() == 0 && !nextToken.equals("NOTES")) {
							// String noteCheck = s.next();
//									System.out.println(noteCheck);
//								if (!nextToken.equals("NOTES")) {
							s.close();
							throw new IllegalArgumentException("Unable to load file.");
//								}
						}

						if (s.hasNext()) {
							String noteLine = s.nextLine();
							Scanner n = new Scanner(noteLine);
							while (n.hasNext()) {
								n.useDelimiter(" ");
//									System.out.println("after notecheck");
								noteDate = new Date(n.next().substring(1));
//									System.out.println(noteDate.toString());
								n.useDelimiter(",");
								noteMessage = n.next().substring(1);
//									System.out.println(noteMessage);
//									String nextcheck = s.next().substring(1);
//									System.out.println(nextcheck);
								note = new Note(noteDate, noteMessage);
//									System.out.println("note: " + note.toString());
								notes.add(note);
//									s.useDelimiter(",");
							}
						}
						if (adopted) {
							animal = new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue,
									adopted, dateAdopted, owner);
						} else {
							animal = new Cat(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
						}
					}

//						System.out.println("animal: " + animal.toString());
					rescue.addAnimal(animal);
					// reset variables
					animal = null;
					type = null;
					name = null;
					birthday = null;
					size = null;
					houseTrained = false;
					goodWithKids = false;
					dateEnterRescue = null;
					adopted = false;
					dateAdopted = null;
					owner = null;
					breed = null;
					note = null;
					noteDate = null;
					noteMessage = null;
					notes = null;
				}

				// read appointments
				if (line.charAt(0) == '-') {
//					System.out.println("char is -");
					try {
						s.useDelimiter(",");
						name = s.next().substring(2);
//							System.out.println("appt name: " + name);
						birthday = new Date(s.next());
//							System.out.println(birthday.toString());
						rescue.addAppointment(rescue.getAnimal(name, birthday));
					} catch (Exception e) {
						s.close();
						throw new IllegalArgumentException("Unable to load file.");
					}
				}

				s.close();
				try {
					rescues.addRescue(rescue);
				} catch (Exception e) {
					if (!started) {
						throw new IllegalArgumentException("Unable to load file.");
					} else
					continue;
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
//		} catch (FileNotFoundExceptiuon e) {
//			throw new IllegalArgumentException("Unable to load file.");
//		}
//		if (rescues == null) {
//			throw new IllegalArgumentException("Unable to load file.");
//		}
		
		return rescues;
	}

}
