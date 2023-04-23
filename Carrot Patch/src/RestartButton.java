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
 * Button for restarting game, extends Button class
 *
 * @author Matthew Smith
 */
public class RestartButton extends Button {

	public RestartButton(float x, float y) {
		super("Restart", x, y);
	}

	@Override
	public void mousePressed() {
		if (isMouseOver()) {
			Carrots.removeAll();

			for (int i = 0; i < Button.processing.objects.size(); i++) {
				if (Button.processing.objects.get(i) instanceof Animal) {
					Button.processing.objects.remove(i);
					i--;
				}
			}
		}
	}

}
