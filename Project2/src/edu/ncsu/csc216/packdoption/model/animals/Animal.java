package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.*;

/**
 * Abstract class to represent an Animal.
 * 
 * @author bmrowan
 * @author mlee25
 *
 */
public abstract class Animal implements Comparable<Animal> {
	/** Animal name */
	private String name;
	/** Animal is house trained or not */
	private boolean houseTrained;
	/** Animal is good with kids or not */
	private boolean goodWithKids;
	/** Animal is adopted or not */
	private boolean adopted;
	/** Animal's owner */
	private String owner;
	/** Animals birthday */
	private Date birthday;
	/** Date the animal entered the rescue */
	private Date dateEnterRescue;
	/** Date the animal was adopted */
	private Date dateAdopted;
	/** Size of the animal */
	private Size size;
	/** Notes on the animal */
	private SortedLinkedList<Note> notes;

	/**
	 * Enum containing Age Category fields
	 */
	public enum AgeCategory {
		/** Young age classification for the animal */
		YOUNG,
		/** Adult age classification for the animal */
		ADULT,
		/** Senior age classification for the animal */
		SENIOR
	}

	/**
	 * Enum containing the size fields
	 */
	public enum Size {
		/** Small size category for the animal */
		SMALL,
		/** Medium size category for the animal */
		MEDIUM,
		/** Large size category for the animal */
		LARGE
	}

	/**
	 * Constructor with 10 paramaters including adoption information.
	 * 
	 * @param name            Animal name
	 * @param birthday        Animal birthday
	 * @param size            Animal size
	 * @param houseTrained    True if house trained
	 * @param goodWithKids    True if good with kids
	 * @param notes           Notes on the animal
	 * @param dateEnterRescue Date the animal entered the rescue
	 * @param adopted         True if animal was adopted
	 * @param dateAdopted     Date the animal was adopted
	 * @param owner           Name of the animal's owner
	 */
	public Animal(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner) {
		if (name != null) {
			name = name.trim();
		}
		if (owner != null) {
			owner = owner.trim();
		}
		if (name == null || name.equals("") || name.contains("\n") || name.contains(",")) {
			throw new IllegalArgumentException();
		}
		if (birthday == null || notes == null) {
			throw new IllegalArgumentException();
		}
		if (dateEnterRescue == null || dateEnterRescue.compareTo(birthday) < 0) {
			throw new IllegalArgumentException();
		}
		this.name = name.trim();
		this.birthday = birthday;
		this.houseTrained = houseTrained;
		this.goodWithKids = goodWithKids;
		this.notes = notes;
		this.dateEnterRescue = dateEnterRescue;
		this.setSize(size);
		this.setAdoptionInfo(adopted, dateAdopted, owner);
	}

	/**
	 * Constructor with 7 parameters without adoption information.
	 * 
	 * @param name            Animal name
	 * @param birthday        Animal birthday
	 * @param size            Animal size
	 * @param houseTrained    True if house trained
	 * @param goodWithKids    True if good with kids
	 * @param notes           Notes on the animal
	 * @param dateEnterRescue Date the animal entered the rescue
	 */
	public Animal(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue) {
		this(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, false, null, null);
	}

	/**
	 * Assigns adoption information to the animal
	 * 
	 * @param adopted     Whether the animal is adopted or not
	 * @param dateAdopted The date the animal was adopted
	 * @param owner       The owner of the animal
	 * @throws IllegalArgumentException if one or more items 9-13 from constructor
	 *                                  list are true
	 */
	public void setAdoptionInfo(boolean adopted, Date dateAdopted, String owner) {
		if (!adopted && (dateAdopted != null || owner != null)
				|| (adopted && (dateAdopted == null || owner == null))) {
			throw new IllegalArgumentException();
		}
		if (dateAdopted != null && dateAdopted.compareTo(dateEnterRescue) < 0) {
			throw new IllegalArgumentException();
		}
		if (owner != null) {
			owner = owner.trim();
		}
		if (owner != null && (owner.equals("") || owner.contains("\n") || owner.contains(","))) {
			throw new IllegalArgumentException();
		}
		this.adopted = adopted;
		this.dateAdopted = dateAdopted;
		this.owner = owner;
	}

	/**
	 * Sets the size of the animal
	 * 
	 * @param size Size of the animal
	 * @throws IllegalArgumentException if size is null
	 */
	public void setSize(Size size) {
		if (size == null) {
			throw new IllegalArgumentException();
		}
		this.size = size;
	}

	/**
	 * Adds a note to the notes list
	 * 
	 * @param note Note to be added
	 * @return True if the note was added
	 * @throws IllegalArgumentException if note is null or if notes already contains
	 *                                  note
	 */
	public boolean addNote(Note note) {
		if (note == null || this.notes.contains(note)) {
			throw new IllegalArgumentException();
		}
		try {
			notes.add(note);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the name of the animal
	 * 
	 * @return Name of the animal
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the birthday of the animal
	 * 
	 * @return Animal's birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Gets the size of the animal
	 * 
	 * @return Size of the animal
	 * @throws IllegalArgumentException if size is null
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * Returns a boolean indicating if the animal is house trained
	 * 
	 * @return True if the animal is house trained
	 */
	public boolean isHouseTrained() {
		return houseTrained;
	}

	/**
	 * Returns a boolean indicating if the animal is good with kids
	 * 
	 * @return True if the animal is good with kids
	 */
	public boolean isGoodWithKids() {
		return goodWithKids;
	}

	/**
	 * Returns a list of notes on the animal
	 * 
	 * @return List of notes
	 */
	public SortedLinkedList<Note> getNotes() {
		return notes;
	}

	/**
	 * Returns the date the animal entered the rescue
	 * 
	 * @return Date animal entered rescue
	 */
	public Date getDateEnterRescue() {
		return dateEnterRescue;
	}

	/**
	 * Returns the date the animal was adopted
	 * 
	 * @return Date animal was adopted
	 */
	public Date getDateAdopted() {
		return dateAdopted;
	}

	/**
	 * Returns the Owner's name
	 * 
	 * @return Owner of the animal
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Returns a boolean indicating if the animal has been adopted
	 * 
	 * @return True if the animal has been adopted
	 */
	public boolean adopted() {
		return adopted;
	}

	/**
	 * Gets the animal's age
	 * 
	 * @param today today's date
	 * @return Age in years
	 * @throws IllegalArgumentException if today is null, or today is before
	 *                                  birthday
	 */
	public int getAge(Date today) {
		if (today == null || today.compareTo(birthday) < 0) {
			throw new IllegalArgumentException();
		}
		return birthday.yearsTo(today);
	}

	/**
	 * Returns days in rescue (from dateEnterRescue to today). Returns -1 if animal
	 * has been adopted.
	 * 
	 * @param today Today's date
	 * @return Days in the rescue
	 * @throws IllegalArgumentException if today is null or today is before
	 *                                  dateEnterRescue
	 */
	public int getDaysAvailableForAdoption(Date today) {
		if (today == null || today.compareTo(dateEnterRescue) < 0) {
			throw new IllegalArgumentException();
		}
		if (adopted) {
			return -1;
		}
		return dateEnterRescue.daysTo(today);
	}

	/**
	 * Gets the animal's age category
	 * 
	 * @param today Today's date
	 * @return AgeCategory of animal
	 * @throws IllegalArgumentException if today is null or today is before birthday
	 */
	public abstract AgeCategory getAgeCategory(Date today);

	/**
	 * Abstract method that returns a String array with seven elements based on
	 * today: Name, Type (Dog or Cat), Birthday, Age, Age Category, Adopted (Yes or
	 * No), Days in Rescue (if adopted then empty string)
	 * 
	 * @param today Today's date
	 * @return Array of information
	 * @throws IllegalArgumentException if today is null or today is before birthday
	 */
	public abstract String[] getAnimalAsArray(Date today);

	/**
	 * Returns negative number the the implicit parameter is less than the actual,
	 * positive number if greater than and 0 if equal. Compared based on birthday
	 * and then name.
	 * 
	 * @param o Animal being compared
	 * @return -1 if this is less than o, 0 if equal, and 1 if this is greater than
	 *         o
	 * @throws NullPointerException if o is null
	 * @throws ClassCastException   if o is not an instance of type Animal
	 */
	public int compareTo(Animal o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (!(o instanceof Animal)) {
			throw new ClassCastException();
		}
		if (o.equals(this)) {
			return 0;
		}
		if (this.birthday.compareTo(o.getBirthday()) != 0) {
			return this.birthday.compareTo(o.getBirthday());
		} else {
			return this.name.compareTo(o.getName());
		}
	}

	/**
	 * Checks for equality based on name and birthday.
	 * 
	 * @param obj the object to compare
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Animal))
			return false;
		Animal other = (Animal) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Generates a hash code based on name and birthday.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Converts the animal information into a String with the format name
	 * (birthday)\nnotes.
	 * 
	 * @return String format of animal info
	 */
	@Override
	public String toString() {
		return name + " (" + birthday + ")\n" + notes;
	}

}
