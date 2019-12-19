package edu.ncsu.csc216.packdoption.util;

/**
 * Custom unchecked exception to be thrown when there’s no such list element.
 * The default message should be “No such element in list.”
 * 
 * @author mlee25
 * @author bmrowan
 *
 */
public class NoSuchListElementException extends RuntimeException {

	/** serial version ID for custom exception */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterless constructor
	 */
	public NoSuchListElementException() {
		super("No such element in list.");
	}

	/**
	 * Constructor with parameter for exception message
	 * 
	 * @param message the exception message
	 */
	public NoSuchListElementException(String message) {
		super(message);
	}
}
