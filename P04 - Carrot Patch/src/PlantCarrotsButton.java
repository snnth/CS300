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

/**
 * Button for planting carrots, extends Button class
 *
 * @author Matthew Smith
 */
public class PlantCarrotsButton extends Button {

	public PlantCarrotsButton(float x, float y) {
		super("Plant Carrots", x, y);
	}

	@Override
	public void mousePressed() {
		if (isMouseOver()) {
			Carrots.plant();
		}
	}
}
