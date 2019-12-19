package edu.ncsu.csc216.packdoption.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class that represents a date as a month, a day, and a year (M/D/YYYY). A
 * valid date must have a year between 2000 and 2050 (inclusive). Implements the
 * Comparable interface.
 * 
 * @author bmrowan
 * @author mlee25
 * 
 */
public class Date implements Comparable<Date> {

	/** the month */
	private int month;
	/** the day */
	private int day;
	/** the year */
	private int year;

	/**
	 * Constructor taking three parameters for month, day and year.
	 * 
	 * @param month the month
	 * @param day   the day
	 * @param year  the year
	 * @throws IllegalArgumentException if month, day and year do not form a valid
	 *                                  date, from isValidDate() methods
	 */
	public Date(int month, int day, int year) {
		if (isValidDate(month, day, year)) {
			this.month = month;
			this.day = day;
			this.year = year;
		} else {
			throw new IllegalArgumentException("Invalid date");
		}
		
	}

	/**
	 * Constructor taking a date as a String.
	 * 
	 * @param date the date String
	 * @throws IllegalArgumentException if the date String is not in the correct
	 *                                  format or if month, day and year do not form
	 *                                  a valid date, from isValidDate() methods
	 */
	public Date(String date) {
		if (date == null) {
			throw new IllegalArgumentException("Invalid date");
		}
		if (isValidDate(date)) {
			Scanner scanner = new Scanner(date);
			scanner.useDelimiter("/");
			String monthString = "";
			String dayString = "";
			String yearString = "";
//			int monthInt;
//			int dayInt;
//			int yearInt;
			if (scanner.hasNextInt()) {
				monthString = scanner.next();
			}
			if (scanner.hasNextInt()) {
				dayString = scanner.next();
			}
			if (scanner.hasNextInt()) {
				yearString = scanner.next();
			}
			scanner.close();
			this.month = Integer.valueOf(monthString);
			this.day = Integer.valueOf(dayString);
			this.year = Integer.valueOf(yearString);
		} else {
			throw new IllegalArgumentException("Invalid date");
		}
	}

	/**
	 * Returns the month.
	 * 
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Returns the day.
	 * 
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Returns the year.
	 * 
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Static method to test whether the given parameters form a valid date.
	 * 
	 * @param month the month
	 * @param day   the day
	 * @param year  the year
	 * @return true if valid date
	 */
	public static boolean isValidDate(int month, int day, int year) {
		boolean leapYear = false;
		int[] leapYears = new int[] { 2000, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, 2048 };
		for (int x : leapYears) {
			if (x == year) {
				leapYear = true;
			}
		}

		if (year < 2000 || year > 2050) {
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		if (month < 1 || month > 12) {
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		if (day < 1) {
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				&& day > 31) {
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		if (leapYear && month == 2 && day > 29) {
			return false;
//			throw new IllegalArgumentException("Invalid date");
		} else if (!leapYear && month == 2 && day > 28) {
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		return true;
	}

	/**
	 * Static method to test whether the given String forms a valid date.
	 * 
	 * @param date the date String
	 * @return true if valid date
	 * @throws IllegalArgumentException if parameter is invalid
	 */
	public static boolean isValidDate(String date) {
		if (date == null || date.length() < 8) {
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		Scanner scanner = new Scanner(date);
		scanner.useDelimiter("/");
		String monthString = "";
		String dayString = "";
		String yearString = "";
		int monthInt;
		int dayInt;
		int yearInt;

//		try {

		if (!Character.isDigit(date.charAt(0)) || !Character.isDigit(date.charAt(date.length() - 1))) {
			scanner.close();
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
//		Scanner duplicate = new Scanner(date);
//		char c = duplicate.next().charAt(0);
//		if (!Character.isDigit(c)) {
//			scanner.close();
//			duplicate.close();
//			throw new IllegalArgumentException("Invalid date");
//		}
//		duplicate.close();

		// check for int and read as string
		if (scanner.hasNextInt()) {
			monthString = scanner.next();
		}

		if (scanner.hasNextInt()) {
			dayString = scanner.next();
		}

		if (scanner.hasNextInt()) {
			yearString = scanner.next();
		}

		// check for any additional characters
		try {

			if (scanner.hasNext()) {
				scanner.close();
				return false;
//				throw new IllegalArgumentException("Invalid date");
			}
		} catch (NoSuchElementException e) {
			// do nothing
		}

		if (monthString.length() != 1 && monthString.length() != 2) {
			scanner.close();
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		if (dayString.length() != 1 && dayString.length() != 2) {
			scanner.close();
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
		if (yearString.length() != 4) {
			scanner.close();
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}
//		} catch (Exception e) {
//			throw new IllegalArgumentException("Invalid date");
//		}

		try {
			monthInt = Integer.valueOf(monthString);
			dayInt = Integer.valueOf(dayString);
			yearInt = Integer.valueOf(yearString);
		} catch (NumberFormatException e) {
			scanner.close();
			return false;
//			throw new IllegalArgumentException("Invalid date");
		}

		scanner.close();
		return isValidDate(monthInt, dayInt, yearInt);
	}

	/**
	 * Returns a Date as a String in the format M/D/YYYY. If month and days are less
	 * than 10, the String format is a single digit without a leading zero.
	 * 
	 * @return the String formatted Date
	 */
	public String toString() {
		String result = "";
		String monthString = String.valueOf(month);
		String dayString = String.valueOf(day);
		String yearString = String.valueOf(year);
		result = monthString + "/" + dayString + "/" + yearString;
		return result;
	}

	/**
	 * Returns the number of days between this Date and other. Returns a negative
	 * number if other is before this.
	 * 
	 * @param other the date to compare
	 * @return the number of days
	 * @throws NullPointerException if date is null
	 */
	public int daysTo(Date other) {
		if (other == null) {
			throw new NullPointerException();
		}
		int y1 = 0;
		int m1 = 0;
		int d1 = 0;
		int y2 = 0;
		int m2 = 0;
		int d2 = 0;
		int totalDays = 0;

		if (this.compareTo(other) == 0) {
			return 0;
		}
		if (this.compareTo(other) < 0) {
			y1 = this.year;
			m1 = this.month;
			d1 = this.day;
			y2 = other.year;
			m2 = other.month;
			d2 = other.day;
		} else {
			y2 = this.year;
			m2 = this.month;
			d2 = this.day;
			y1 = other.year;
			m1 = other.month;
			d1 = other.day;
		}

		while (m1 != m2 || d1 != d2 || y1 != y2) {
			if (endOfMonth(m1, d1, y1)) {
				if (m1 == 12) {
					m1 = 1;
					d1 = 1;
					y1++;
				} else {
					d1 = 1;
					m1++;
				}
			} else {
				d1++;
			}
			totalDays++;
		}
		if (this.compareTo(other) > 0) {
			return -totalDays;
		}
		return totalDays;
	}

	/**
	 * Private helper method to check for end of month and year.
	 * 
	 * @param month the month;
	 * @param day   the day;
	 * @param year  the year;
	 * @return true if end of month or year;
	 */
	private boolean endOfMonth(int month, int day, int year) {
		boolean leapYear = false;
		int[] leapYears = new int[] { 2000, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, 2048 };
		for (int x : leapYears) {
			if (year == x)
				leapYear = true;
		}
		if (month == 1 && day == 31) {
			return true;
		}
		if (month == 2 && day == 29 && leapYear) {
			return true;
		}
		if (month == 2 && day == 28 && !leapYear) {
			return true;
		}
		if (month == 3 && day == 31) {
			return true;
		}
		if (month == 4 && day == 30) {
			return true;
		}
		if (month == 5 && day == 31) {
			return true;
		}
		if (month == 6 && day == 30) {
			return true;
		}
		if (month == 7 && day == 31) {
			return true;
		}
		if (month == 8 && day == 31) {
			return true;
		}
		if (month == 9 && day == 30) {
			return true;
		}
		if (month == 10 && day == 31) {
			return true;
		}
		if (month == 11 && day == 30) {
			return true;
		}
		if (month == 12 && day == 31) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of years between this Date and other. Only counts full
	 * years. Returns a negative number if other is before this.
	 * 
	 * @param other the date to compare
	 * @return the number of full years
	 * @throws NullPointerException if other is null
	 */
	public int yearsTo(Date other) {
		if (other == null) {
			throw new NullPointerException();
		}
		int y1 = 0;
		int y2 = 0;
		if (this.compareTo(other) == 0) {
			return 0;
		}
		int totalDays = this.daysTo(other);
		if (this.compareTo(other) < 0) {
			y1 = this.year;
			y2 = other.year;
		} else {
			y1 = other.year;
			y2 = this.year;
		}
		int totalYears = 0;
		int numberOfLeapYears = 0;

		int[] leapYears = new int[] { 2000, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, 2048 };
		for (int x : leapYears) {
			if (y1 == x)
				numberOfLeapYears++;
		}

		if (y2 > y1) {
			numberOfLeapYears += (y2 - y1) / 4;
		}

		totalYears = (totalDays - numberOfLeapYears) / 365;
//		if (this.compareTo(other) > 0) {
//			return -totalYears;
//		}
		return totalYears;
	}

	/**
	 * Compares this Date to a given date based on year, month and day (in that
	 * order).
	 * 
	 * @return a negative integer if this Date is less than other, 0 if equals and a
	 *         positive integer if this Date is greater than other
	 * 
	 * @throws NullPointerException if o is null
	 * @throws ClassCastException   if o is not a Date object
	 */
	@Override
	public int compareTo(Date o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (!(o instanceof Date)) {
			throw new ClassCastException();
		}
		if (o.equals(this)) {
			return 0;
		}
		if (this.year < o.getYear()) {
			return -1;
		}
		if (this.year > o.getYear()) {
			return 1;
		}
		if (this.month < o.getMonth()) {
			return -1;
		}
		if (this.month > o.getMonth()) {
			return 1;
		}
		if (this.day < o.getDay()) {
			return -1;
		}
		if (this.day > o.getDay()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Compares this Date to another object and returns true if equal.
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
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	/**
	 * Returns the hash code based on month, day and year.
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

}
