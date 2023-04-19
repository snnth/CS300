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

import java.io.File;

/**
 * Rabbit class, extends Animal class.
 *
 * @author Matthew Smith
 */
public class Rabbit extends Animal {

	private static final String RABBIT = "images"+ File.separator + "rabbit.png";
	private static final String TYPE = "R"; // A String that represents the rabbit type
	private static int hopStep = 70; // one hop step
	private static int scanRange = 175; // scan range to watch out for threats
	private static int nextID = 1; // class variable that represents the identifier
	// of the next rabbit to be created
	private final int ID; // positive number that represents the order of this rabbit

	/**
	 * Creates a new rabbit object located at a random position of the display window
	 */
	public Rabbit() {
		// Set rabbit drawing parameters
		super(RABBIT);
		// Set rabbit identification fields
		ID = nextID;
		this.label = TYPE + ID; // String that identifies the current rabbit
		nextID++;
	}

	/**
	 * Returns rabbit's scan range.
	 *
	 * @return rabbits scan range
	 */
	public static int getScanRange() {
		return scanRange;
	}

	/**
	 * Returns rabbit's hop step.
	 *
	 * @return rabbits hop step
	 */
	public static int getHopStep() {
		return hopStep;
	}
}
