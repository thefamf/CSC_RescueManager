package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Concrete class of Animal representing a cat
 * 
 * @author BrendanRowan
 *
 */
public class Cat extends Animal {

	/**
	 * Constructor with 10 paramaters including adoption information.
	 * 
	 * @param name            Cat name
	 * @param birthday        Cat birthday
	 * @param size            Cat size
	 * @param houseTrained    True if house trained
	 * @param goodWithKids    True if good with kids
	 * @param notes           Notes on the cat
	 * @param dateEnterRescue Date the cat entered the rescue
	 * @param adopted         True if cat was adopted
	 * @param dateAdopted     Date the cat was adopted
	 * @param owner           Name of the cat's owner
	 */
	public Cat(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted, owner);
	}

	/**
	 * Constructor with 7 parameters without adoption information.
	 * 
	 * @param name            Cat name
	 * @param birthday        Cat birthday
	 * @param size            Cat size
	 * @param houseTrained    True if house trained
	 * @param goodWithKids    True if good with kids
	 * @param notes           Notes on the cat
	 * @param dateEnterRescue Date the cat entered the rescue
	 */
	public Cat(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
	}

	/**
	 * Gets the cat's age category
	 * 
	 * @param today Today's date
	 * @return AgeCategory of cat
	 * @throws IllegalArgumentException if today is null or today is before birthday
	 */
	public AgeCategory getAgeCategory(Date today) {
		if (today == null || today.compareTo(this.getBirthday()) < 0) {
			throw new IllegalArgumentException();
		}
		if (this.getAge(today) < 4) {
			return AgeCategory.YOUNG;
		}
		if (this.getAge(today) < 9) {
			return AgeCategory.ADULT;
		} else {
			return AgeCategory.SENIOR;
		}
	}

	/**
	 * Returns a String array with seven elements based on today: Name, Type (Cat),
	 * Birthday, Age, Age Category, Adopted (Yes or No), Days in Rescue (if adopted
	 * then empty string)
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
		result[1] = "Cat";
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

}
