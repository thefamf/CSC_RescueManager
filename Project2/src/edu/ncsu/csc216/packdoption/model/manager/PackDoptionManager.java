package edu.ncsu.csc216.packdoption.model.manager;

import edu.ncsu.csc216.packdoption.model.io.PackDoptionReader;
import edu.ncsu.csc216.packdoption.model.io.PackDoptionWriter;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * Maintains the data structures for the program. Implements the Singleton
 * design pattern.
 * 
 * @author bmrowan
 * @author mlee25
 *
 */
public class PackDoptionManager {

	/** the singleton instance */
	private static PackDoptionManager instance;
	/** the RescueList of rescues */
	private RescueList rescues;
	/** the filename */
	private String filename;
	/** boolean variable to track if changed */
	private boolean changed;

	/**
	 * Private constructor to construct the singleton instance and sets changed to
	 * false.
	 */
	private PackDoptionManager() {
		rescues = new RescueList();
		changed = false;
	}

	/**
	 * Returns the singleton instance of PackDoptionManager.
	 * 
	 * @return the singleton instance
	 */
	public static PackDoptionManager getInstance() {
		if (instance == null) {
			instance = new PackDoptionManager();
		}
		return instance;
	}

	/**
	 * Sets rescues to a new RescueList.
	 */
	public void newList() {
		rescues = new RescueList();
		changed = false;
	}

	/**
	 * Returns changed (whether data has changed from last save)
	 * 
	 * @return true if changed
	 */
	public boolean isChanged() {
		return changed;
	}


	/**
	 * Returns the filename.
	 * 
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Setter for filename.
	 * 
	 * @param filename the filename
	 * @throws IllegalArgumentException if filename is null or only whitespace
	 */
	public void setFilename(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException();
		}
		if (filename.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		
		this.filename = filename;
	}

	/**
	 * Loads a RescueList from the given filename and sets changed to false.
	 * 
	 * @param filename the filename
	 * @throws IllegalArgumentException if any errors reading the file
	 */
	public void loadFile(String filename) {
		try {
			rescues = PackDoptionReader.readRescueListFile(filename);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		changed = false;
		setFilename(filename);
	}

	/**
	 * Writes the RescueList to the given filename and sets changed to false.
	 * 
	 * @param filename the filename
	 * @throws IllegalArgumentException if any errors writing to the file
	 */
	public void saveFile(String filename) {
		if (filename == null || filename.trim().equals("")) {
			throw new IllegalArgumentException();
		}
		try {
			PackDoptionWriter.writeRescueFile(filename, rescues);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		//changed = true;
		changed = false;
	}

	/**
	 * Returns the RescueList.
	 * 
	 * @return the RescueList
	 */
	public RescueList getRescueList() {
		return rescues;
	}

	/**
	 * Setter for changed.
	 * 
	 * @param changed the changed to set
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
}
