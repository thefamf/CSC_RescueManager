package edu.ncsu.csc216.packdoption.util;

/**
 * A class that represents a note as a date and a message.
 * 
 * @author bmrowan
 * @author mlee25
 *
 */
public class Note implements Comparable<Note> {

	/** the note date */
	private Date date;
	/** the note message */
	private String message;

	/**
	 * Constructor taking 2 parameters.
	 * 
	 * @param date    the date
	 * @param message the message
	 * @throws IllegalArgumentException if date or message is null, message is
	 *                                  whitespace only, or if message contains \n
	 *                                  or ,
	 */
	public Note(Date date, String message) {
		if (date == null || message == null || message.trim().equals("") || message.contains("\n") || message.contains(",")) {
			throw new IllegalArgumentException("Invalid note");
		}
		this.date = date;
		this.message = message.trim();
	}

	/**
	 * Returns the message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the date
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Compares this Note to a given note based on date and then message.
	 * 
	 * @return a negative integer if this is less than other, 0 if equal, and a
	 *         positive integer if this is greater than other
	 */
	@Override
	public int compareTo(Note o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (!(o instanceof Note)) {
			throw new ClassCastException();
		}
		if (o.equals(this)) {
			return 0;
		}
		if (this.date.compareTo(o.getDate()) < 0) {
			return -1;
		}
		if (this.date.compareTo(o.getDate()) > 0) {
			return 1;
		}
		if (this.date.compareTo(o.getDate()) == 0) {
			if (this.message.compareTo(o.getMessage()) < 0) {
				return -1;
			}
			if (this.message.compareTo(o.getMessage()) > 0) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Returns the string representation of this Note in the format - date message
	 * 
	 * @return the string formatted note
	 */
	@Override
	public String toString() {
		return date.toString() + " " + message.toString();
	}

	/**
	 * Compares this Note to another object and returns true if equal.
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
		Note other = (Note) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	/**
	 * Returns a hash code for this Note.
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}
}

