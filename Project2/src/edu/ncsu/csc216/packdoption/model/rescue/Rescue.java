package edu.ncsu.csc216.packdoption.model.rescue;

import java.util.Scanner;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.util.ArrayListQueue;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SimpleListIterator;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Class to represent a rescue. Each rescue has a name, a list of Animals who
 * are part of the rescue, and a list of Animals that are waiting to see the
 * veterinarian.
 * 
 * @author bmrowan
 * @author mlee25
 *
 */
public class Rescue implements Comparable<Rescue> {
	/** Name of the rescue */
	private String name;
	/** List of vet appointments */
	private ArrayListQueue<Animal> vetAppointments;
	/** List of animals in the rescue */
	private SortedLinkedList<Animal> animals;

	/**
	 * Constructor
	 * 
	 * @param name Name of rescue
	 * @throws IllegalArgumentException if (1) name is null, (2) name is whitespace
	 *                                  only, or (3) name contains \n
	 */
	public Rescue(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		if (name.contains("\n")) {
			throw new IllegalArgumentException();
		}
		Scanner scan = new Scanner(name);
		scan.nextLine();
		if (scan.hasNextLine()) {
			scan.close();
			throw new IllegalArgumentException();
		}
		scan.close();
		this.name = name;
		vetAppointments = new ArrayListQueue<Animal>();
		animals = new SortedLinkedList<Animal>();
		
	}

	/**
	 * Returns the name of the rescue
	 * 
	 * @return Name of the rescue
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds an animal to the rescue
	 * 
	 * @param animal Animal to be added
	 * @return True if the animal was added, false if animal is a duplicate
	 * @throws IllegalArgumentException if animal is null.
	 */
	public boolean addAnimal(Animal animal) {
		if (animal == null) {
			throw new IllegalArgumentException();
		}
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(animal)) {
				return false;
			}
		}
		animals.add(animal);
		return true;
	}

	/**
	 * Gets an animal from the animal list
	 * 
	 * @param index Index in the list where the animal is located
	 * @return Animal from the list at index
	 * @throws IndexOutOfBoundsException if index is negative or greater than size -
	 *                                   1
	 */
	public Animal getAnimal(int index) {
		if (index < 0 || index >= animals.size()) {
			throw new IndexOutOfBoundsException();
		}
		SimpleListIterator<Animal> iter = animals.iterator();
		while (index > 0) {
			iter.next();
			index--;
		}
		return iter.next();
	}

	/**
	 * Gets an animal from the animal list
	 * 
	 * @param name     Name of animal
	 * @param birthday Animal's birthday
	 * @return Animal from the list with specified name and birthday
	 * @throws IllegalArgumentException if (1) name is null or (2) birthday is null
	 */
	public Animal getAnimal(String name, Date birthday) {
		if (name == null || birthday == null) {
			throw new IllegalArgumentException();
		}
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (a.getName().equals(name) && a.getBirthday().equals(birthday)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Returns whether animals contains a.
	 * 
	 * @param animal Animal being searched for
	 * @return True if the animal is in the rescue, false otherwise
	 */
	public boolean contains(Animal animal) {
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(animal)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a note to animal
	 * 
	 * @param animal Animal where the note is being added to
	 * @param note   Note being added
	 * @return True if animal is in the rescue, false and does not add note if
	 *         animal is not in the rescue
	 * @throws IllegalArgumentException Throws an IllegalArgumentException if animal
	 *                                  is null, note is null, or the animal’s notes
	 *                                  already contains note
	 */
	public boolean addNote(Animal animal, Note note) {
		if (animal == null || note == null) {
			throw new IllegalArgumentException();
		}
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (a.equals(animal)) {
				if (a.getNotes().contains(note)) {
					throw new IllegalArgumentException();
				}
				a.addNote(note);
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the adoption information for an animal in the rescue
	 * 
	 * @param animal      Animal where the information is being added to
	 * @param adopted     True if the animal has been adopted
	 * @param dateAdopted Date the animal was adopted
	 * @param owner       Owner of the animal
	 * @throws IllegalArgumentException if animal is null
	 */
	public void setAdoptionInfo(Animal animal, boolean adopted, Date dateAdopted, String owner) {
		if (animal == null) {
			throw new IllegalArgumentException();
		}
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (a.equals(animal)) {
				a.setAdoptionInfo(adopted, dateAdopted, owner);
			}
		}
	}

	/**
	 * Returns the number of animals in the rescue
	 * 
	 * @return Number of animals in the rescue
	 */
	public int numAnimals() {
		return animals.size();
	}

	/**
	 * Returns the number of animals available for adoption in the rescue
	 * 
	 * @return Number animals available
	 */
	public int numAnimalsAvailable() {
		int count = 0;
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (!a.adopted()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns number animals in rescue that have been adopted.
	 * 
	 * @return Number of animals adopted
	 */
	public int numAnimalsAdopted() {
		int count = 0;
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (a.adopted()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns SortedLinkedList of all animals in rescue that are available for
	 * adoption (have not been adopted)
	 * 
	 * @return List of animals available
	 */
	public SortedLinkedList<Animal> animalsAvailable() {
		SortedLinkedList<Animal> list = new SortedLinkedList<Animal>();
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (!a.adopted()) {
				list.add(a);
			}
		}
		
		return list;
	}

	/**
	 * Returns SortedLinkedList of all cats in rescue that are available for
	 * adoption (have not been adopted)
	 * 
	 * @return List of cats available
	 */
	public SortedLinkedList<Animal> availableCats() {
		SortedLinkedList<Animal> catList = new SortedLinkedList<Animal>();
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (!a.adopted() && a instanceof Cat) {
				catList.add(a);
			}
		}
		
		return catList;
	}

	/**
	 * Returns SortedLinkedList of all dogs in rescue that are available for
	 * adoption (have not been adopted)
	 * 
	 * @return List of dogs available
	 */
	public SortedLinkedList<Animal> availableDogs() {
		SortedLinkedList<Animal> dogList = new SortedLinkedList<Animal>();
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (!a.adopted() && a instanceof Dog) {
				dogList.add(a);
			}
		}
		
		return dogList;
	}

	/**
	 * Returns SortedLinkedList of animals in rescue that have been adopted
	 * 
	 * @return List of adopted animals
	 */
	public SortedLinkedList<Animal> animalsAdopted() {
		SortedLinkedList<Animal> list = new SortedLinkedList<Animal>();
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (a.adopted()) {
				list.add(a);
			}
		}
		
		return list;
	}

	/**
	 * Returns SortedLinkedList of all animals in rescue that are available for
	 * adoption (have not been adopted) and have been available between min and max
	 * days (inclusive)
	 * 
	 * @param today Today's date
	 * @param min   Minimum days the animal has been available
	 * @param max   Maximum days the animal has been available
	 * @return List of animals available based on parameters
	 * @throws IllegalArgumentException if (1) today is null, (2) today is before
	 *                                  one of the animal’s dateEnterRescue, (3) max
	 *                                  is less than min, or (4) min is less than
	 *                                  zero
	 */
	public SortedLinkedList<Animal> availableAnimalsDayRange(Date today, int min, int max) {
		if (today == null || max < min || min < 0) {
			throw new IllegalArgumentException();
		}
		SortedLinkedList<Animal> list = new SortedLinkedList<Animal>();
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (a.getDateEnterRescue().compareTo(today) > 0) {
				throw new IllegalArgumentException();
			}
			if (!a.adopted() && a.getDaysAvailableForAdoption(today) > min && a.getDaysAvailableForAdoption(today) < max) {
				list.add(a);
			}
		}
		
		return list;
	}

	/**
	 * Returns SortedLinkedList of all animals in rescue that are available for
	 * adoption (have not been adopted) and are between min and max years old
	 * (inclusive)
	 * 
	 * @param today Today's date
	 * @param min   Minimum age of the animal
	 * @param max   Maximum age of the animal
	 * @return List of animals based on parameters
	 * @throws IllegalArgumentException if (1) today is null, (2) today is before
	 *                                  one of the animal’s birthday, (3) max is
	 *                                  less than min, or (4) min is less than zero
	 */
	public SortedLinkedList<Animal> availableAnimalsAge(Date today, int min, int max) {
		if (today == null || max < min || min < 0) {
			throw new IllegalArgumentException();
		}
		SortedLinkedList<Animal> list = new SortedLinkedList<Animal>();
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			if (a.getBirthday().compareTo(today) > 0) {
				throw new IllegalArgumentException();
			}
			if (!a.adopted() && a.getAge(today) >= min && a.getAge(today) <= max) {
				list.add(a);
			}
		}
		
		return list;
	}

	/**
	 * Returns a 2D array of string representing all animals in rescue
	 * 
	 * @param today Today's date
	 * @return 2D array of rescue/animal information
	 */
	public String[][] getAnimalsAsArray(Date today) {
		String[][] arr = new String[animals.size()][7];
		int index = 0;
		SimpleListIterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			Animal a = iter.next();
			arr[index][0] = a.getName();
			if (a instanceof Cat) {
				arr[index][1] = "Cat";
			} else {
				arr[index][1] = "Dog";
			}
			arr[index][2] = a.getBirthday().toString();
			arr[index][3] = a.getAge(today) + "";
			arr[index][4] = a.getAgeCategory(today).toString();
			if (a.adopted()) {
				arr[index][5] = "Yes";
			} else {
				arr[index][5] = "No";
			}
			if (a.getDaysAvailableForAdoption(today) == -1) {
				arr[index][6] = "";
			} else {
				arr[index][6] = a.getDaysAvailableForAdoption(today) + "";
			}
			index++;
		}
		return arr;
	}

	/**
	 * Adds animal to vetAppointments
	 * 
	 * @param animal Animal that is getting the appointment
	 * @return True if animal is in rescue. Otherwise, returns false
	 * @throws NullPointerException if animal is null
	 */
	public boolean addAppointment(Animal animal) {
		if (animal == null) {
			throw new NullPointerException();
		}
		if (contains(animal)) {
			vetAppointments.add(animal);
			return true;
		}
		return false;
	}

	/**
	 * Returns a 2D array of string representing animals in veterinary queue
	 * 
	 * @param today Today's date
	 * @return 2D array of animals in vet queue
	 */
	public String[][] getAppointmentsAsArray(Date today) {
		if (today == null) {
			throw new IllegalArgumentException();
		}
		ArrayListQueue<Animal> app = vetAppointments;
		String[][] arr = new String[app.size()][7];
		int index = 0;
//		while (app.size() > 0) {
		while (index < arr.length) {
			Animal a = app.remove();
			arr[index][0] = a.getName();
			if (a instanceof Cat) {
				arr[index][1] = "Cat";
			} else {
				arr[index][1] = "Dog";
			}
			arr[index][2] = a.getBirthday().toString();
			arr[index][3] = a.getAge(today) + "";
			arr[index][4] = a.getAgeCategory(today).toString();
			if (a.adopted()) {
				arr[index][5] = "Yes";
				arr[index][6] = "";
			} else {
				arr[index][5] = "No";
				arr[index][6] = a.getDaysAvailableForAdoption(today) + "";
			}
			index++;
			app.add(a);
		}
		return arr;
	}

	/**
	 * Returns the vetAppointments.
	 * 
	 * @return the vetAppointments
	 */
	public ArrayListQueue<Animal> getAppointments() {
		return vetAppointments;
	}

	/**
	 * Compares rescues based on name
	 * 
	 * @param rescue Object or rescue being compared
	 * @return Negative number if implicit less than actual, positive if greater
	 *         than, 0 if equal
	 * @throws NullPointerException if the object if null
	 * @throws ClassCastException   if the object's type is not a Rescue
	 */
	@Override
	public int compareTo(Rescue rescue) {
		if (rescue == null) {
			throw new NullPointerException();
		}
		if (!(rescue instanceof Rescue)) {
			throw new ClassCastException();
		}
		return name.compareTo(rescue.getName());
	}

	/**
	 * Returns a hash code for this Rescue.
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Compares this to other object for equality based on name.
	 * 
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rescue other = (Rescue) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Returns the rescue name.
	 * 
	 * @return the rescue name
	 */
	@Override
	public String toString() {
		return getName();
	}

}