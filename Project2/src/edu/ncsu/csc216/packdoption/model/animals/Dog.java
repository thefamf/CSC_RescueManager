package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Concrete class of Animal representing a dog.
 * 
 * @author BrendanRowan
 *
 */
public class Dog extends Animal {
	/** Breed type of the dog */
	private Breed breed;

	/**
	 * Constructor 1
	 * 
	 * @param name            Dog name
	 * @param birthday        Dog birthday
	 * @param size            Dog size
	 * @param houseTrained    True if house trained
	 * @param goodWithKids    True if good with kids
	 * @param notes           Notes on the dog
	 * @param dateEnterRescue Date the dog entered the rescue
	 * @param adopted         True if dog was adopted
	 * @param dateAdopted     Date the dog was adopted
	 * @param owner           Name of the dog's owner
	 * @param breed           the dog's breed
	 */
	public Dog(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner,
			Breed breed) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted, owner);
		if (breed == null) {
			throw new IllegalArgumentException();
		}
		this.breed = breed;
	}

	/**
	 * Constructor 2
	 * 
	 * @param name            Dog name
	 * @param birthday        Dog birthday
	 * @param size            Dog size
	 * @param houseTrained    True if house trained
	 * @param goodWithKids    True if good with kids
	 * @param notes           Notes on the dog
	 * @param dateEnterRescue Date the dog entered the rescue
	 * @param breed           the dog's breed
	 */
	public Dog(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, Breed breed) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
		if (breed == null) {
			throw new IllegalArgumentException();
		}
		this.breed = breed;
		
	}

	/**
	 * Returns a String array with seven elements based on
	 * today parameter: Name, Type (Dog or Cat), Birthday, Age, Age Category, Adopted (Yes or
	 * No), Days in Rescue (if adopted then empty string)
	 * 
	 * @param today Today's date
	 * @return Array of information
	 * @throws IllegalArgumentException if today is null or today is before birthday
	 */
	public String[] getAnimalAsArray(Date today) {
		if (today == null || today.compareTo(this.getBirthday()) < 0) {
			throw new IllegalArgumentException();
		}
		String[] result = new String[7];
		result[0] = this.getName();
		result[1] = "Dog";
		result[2] = this.getBirthday().toString();
		result[3] = "" + this.getAge(today);
		result[4] = this.getAgeCategory(today).toString();
		if (this.adopted()) {
			result[5] = "Yes";
			result[6] = "";
		} else {
			result[5] = "No";
			result[6] = "" + this.getDaysAvailableForAdoption(today);
		}
		return result;
	}

	/**
	 * Gets the dog's age category
	 * 
	 * @param today Today's date
	 * @return AgeCategory of dog
	 * @throws IllegalArgumentException if today is null or today is before birthday
	 */
	public AgeCategory getAgeCategory(Date today) {
		if (today == null || today.compareTo(this.getBirthday()) < 0) {
			throw new IllegalArgumentException();
		}
		if (this.getSize() == Size.SMALL) {
			if (this.getAge(today) < 4) {
				return AgeCategory.YOUNG;
			}
			if (this.getAge(today) < 9) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		}
		if (this.getSize() == Size.MEDIUM) {
			if (this.getAge(today) < 3) {
				return AgeCategory.YOUNG;
			}
			if (this.getAge(today) < 9) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		} else {
//		if (this.getSize() == Size.LARGE) {
			if (this.getAge(today) < 3) {
				return AgeCategory.YOUNG;
			}
			if (this.getAge(today) < 6) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		}
	}


	/**
	 * Returns the breed
	 * 
	 * @return the breed
	 */
	public Breed getBreed() {
		return breed;
	}

	/**
	 * Enum for the breed types
	 */
	public enum Breed {
		/** Beagle */
		BEAGLE,
		/** Bulldog */
		BULLDOG,
		/** French bulldog */
		FRENCH_BULLDOG,
		/** German Shepherd */
		GERMAN_SHEPHERD,
		/** Pointer German Shorthaired */
		POINTER_GERMAN_SHORTHAIRED,
		/** Poodle */
		POODLE,
		/** Golden retriever */
		RETRIEVER_GOLDEN,
		/** Labrador retriever */
		RETRIEVER_LABRADOR,
		/** Rottweiler */
		ROTTWEILER,
		/** Yorkshire terrier */
		YORKSHIRE_TERRIER,
		/** Mixed breed */
		MIXED,
		/** Other breed */
		OTHER
	}

}
