///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           P04 - Carrot Patch
// Course:          CS 200, Fall 2020
//
// Author:          Matthew Smith
// Email:           mjsmith44@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Description
// Jane Doe; helped me with for loops in main method
// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html; 
//         counting for loop
// John Doe; I helped with using constants
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;
import java.io.File;

/**
 * Wolf class, extends Animal class.
 *
 * @author Matthew Smith
 */
public class Wolf extends Animal {

	// path to the wolf image file
	private static final String WOLF = "images" + File.separator + "wolf.png";
	private static int scanRange = 120; // scanning range to look for a rabbit
	// in the neighborhood
	private static int nextID = 1; // identifier of the next Wolf to be created
	private static final String TYPE = "W"; // A String that represents the Wolf type
	private final int ID; // positive number that represents the order of this Wolf
	private int rabbitEatenCount; // Number of rabbits that the current Wolf has eaten so far

	/**
	 * Creates a new Wolf object at a random position of the display window
	 */
	public Wolf() {
		// Set wolf drawing parameters
		super(WOLF);
		// Set wolf identification fields
		ID = nextID;
		this.label = TYPE + ID; // String that identifies the current rabbit
		nextID++;
	}

	/**
	 * Return's the amount of rabbits this wolf has eaten.
	 *
	 * @return amount of rabbits ate
	 */
	public int getRabbitEatenCount(){
		return rabbitEatenCount;
	} // gets rabbitEatenCount instance field

	/**
	 * Return's the this wolfs' scan range.
	 *
	 * @return scan range of this wolf
	 */
	public static int getScanRange(){
		return scanRange;
	} // gets the scanRange of a Wolf

}
